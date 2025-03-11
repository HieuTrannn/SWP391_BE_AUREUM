package com.example.SkincareProductSales.service;

import com.example.SkincareProductSales.entity.Account;
import com.example.SkincareProductSales.entity.request.AccountRequest;
import com.example.SkincareProductSales.entity.request.AuthenticationRequest;
import com.example.SkincareProductSales.entity.response.AuthenticationResponse;
import com.example.SkincareProductSales.enums.RoleEnum;
import com.example.SkincareProductSales.enums.SkinTypeEnum;
import com.example.SkincareProductSales.repository.AuthenticationRepository;
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

    public Account register(AccountRequest accountRequest){
        // xử lý logic
        //        String currentPassword = account.getPassword();
//        String newPassword = passwordEncoder.encode(currentPassword);
//        account.setPassword(newPassword);


        Account account = new Account();


        account.setRoleEnum(RoleEnum.CUSTOMER);
        account.setSkinTypeEnum(SkinTypeEnum.DACHUAKIEMTRA);
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
            authenticationResponse.setToken(tokenService.GenerateToken(account));
            return authenticationResponse;
        } catch (Exception e) {
            throw new RuntimeException("invalid Login");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return authenticationRepository.findByEmail(email).orElseThrow();
    }
}
