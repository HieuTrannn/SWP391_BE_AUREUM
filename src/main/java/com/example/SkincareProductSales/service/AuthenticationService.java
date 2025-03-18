package com.example.SkincareProductSales.service;

import com.example.SkincareProductSales.entity.Account;
import com.example.SkincareProductSales.entity.request.*;
import com.example.SkincareProductSales.entity.response.AuthenticationResponse;
import com.example.SkincareProductSales.enums.RoleEnum;
import com.example.SkincareProductSales.model.EmailDetail;
import com.example.SkincareProductSales.repository.AuthenticationRepository;
import com.example.SkincareProductSales.utils.AccountUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.persistence.EntityNotFoundException;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    AuthenticationRepository authenticationRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @Autowired
    EmailService emailService;

    @Autowired
    AccountUtils accountUtils;

    public Account register(AccountRequest accountRequest){
        // xử lý logic
        //        String currentPassword = account.getPassword();
//        String newPassword = passwordEncoder.encode(currentPassword);
//        account.setPassword(newPassword);


        Account account = new Account();


        account.setRoleEnum(RoleEnum.CUSTOMER);
        account.setPhone(accountRequest.getPhone());
        account.setPassword(passwordEncoder.encode(accountRequest.getPassword()));
        account.setFullName(accountRequest.getFullName());
        account.setEmail(accountRequest.getEmail());

        Account newAccount = authenticationRepository.save(account);
        return newAccount;
    }



    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getEmail(),
                    authenticationRequest.getPassword()
            ));
            Account account = (Account) authenticate.getPrincipal();
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            authenticationResponse.setId(account.getId());
            authenticationResponse.setFullName(account.getFullName());
            authenticationResponse.setEmail(account.getEmail());
            authenticationResponse.setPhone(account.getPhone());
            authenticationResponse.setRoleEnum(account.getRoleEnum());
            authenticationResponse.setActive(account.isActive());
            authenticationResponse.setToken(tokenService.GenerateToken(account));
            authenticationResponse.setSkin(account.getSkin());
            return authenticationResponse;
        } catch (Exception e) {
            throw new RuntimeException("invalid Login");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return authenticationRepository.findByEmail(email);
    }


    public AuthenticationResponse authenticateWithGoogle(LoginGoogleRequest loginGoogleRequest){
        try {

            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(loginGoogleRequest.getToken());
            String email = decodedToken.getEmail();
            String fullName = decodedToken.getName();

            Account account = authenticationRepository.findByEmail(email);
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();

            if (account == null) {
                Account newAccount = new Account();
                newAccount.setEmail(email);
                newAccount.setFullName(fullName);
                newAccount.setRoleEnum(RoleEnum.CUSTOMER);
                newAccount.setActive(true);
                account = authenticationRepository.save(newAccount);
            }
            authenticationResponse.setId(account.getId());
            authenticationResponse.setFullName(account.getFullName());
            authenticationResponse.setEmail(account.getEmail());
            authenticationResponse.setPhone(account.getPhone());
            authenticationResponse.setRoleEnum(account.getRoleEnum());
            authenticationResponse.setActive(account.isActive());
            authenticationResponse.setToken(tokenService.GenerateToken(account));
            authenticationResponse.setSkin(account.getSkin());
            return authenticationResponse;

        } catch (FirebaseAuthException e) {
            throw new RuntimeException("Xác thực Firebase thất bại: " + e.getMessage());
        }
    }

    public void forgotPassword(ForgotPasswordRequest forgotPasswordRequest) {
        Account account = authenticationRepository.findByEmail(forgotPasswordRequest.getEmail());
        if (account == null) {
            throw new EntityNotFoundException("User not Found!");
        }
        EmailDetail emailDetail = new EmailDetail();
        emailDetail.setRecipient(account.getEmail()); // set email được gửi tới ai
        emailDetail.setSubject("Reset password for account " + account.getEmail() + "!"); // tên tiêu đề email
        emailDetail.setMsgBody("aaa");
        emailDetail.setButtonValue("Reset Password"); // giá trị của button trong form email
        emailDetail.setFullName(account.getFullName());
        emailDetail.setLink("http://localhost:5173/resetPassword?token=" + tokenService.GenerateToken(account));

        // successfully trước rồi gửi mail sau
        Runnable r = new Runnable() {

            @Override
            public void run() {
                emailService.sendEmailTemplate(emailDetail);
            }
        };
        new Thread(r).start();
    }

    public void resetPassword(ResetPasswordRequest resetPasswordRequest) {
        Account account = accountUtils.getCurrentAccount();
        account.setPassword(passwordEncoder.encode(resetPasswordRequest.getPassword()));
        authenticationRepository.save(account);

    }
}
