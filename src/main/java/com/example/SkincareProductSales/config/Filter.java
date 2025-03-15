package com.example.SkincareProductSales.config;

import com.example.SkincareProductSales.entity.Account;
import com.example.SkincareProductSales.exception.exceptions.AuthorizeException;
import com.example.SkincareProductSales.service.TokenService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.security.SignatureException;
import java.util.List;

@Component
public class Filter extends OncePerRequestFilter {
    @Autowired
    TokenService tokenService;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    HandlerExceptionResolver resolver;

    private final List<String> AUTH_PERMISSION = List.of(
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/api/login",
            "/api/register",
            "/api/loginGoogle",
            "/api/forgot-password"
    );

    public boolean checkIsPublicAPI(HttpServletRequest request) {
        // uri: /api/register
        // nếu gặp những cái api trong list ở trên => cho phép truy cập lun => true
        AntPathMatcher patchMatch = new AntPathMatcher();
        String uri = request.getRequestURI();
        String method = request.getMethod();

        // check token => false
        if(method.equals("GET") && patchMatch.match("/api/product/**", uri)){
            return true; // public api
        }
        if(method.equals("GET") && patchMatch.match("/api/brand/**", uri)){
            return true; // public api
        }
        if(method.equals("GET") && patchMatch.match("/api/ingredient/**", uri)){
            return true; // public api
        }
        if(method.equals("GET") && patchMatch.match("/api/category/**", uri)){
            return true; // public api
        }

        return AUTH_PERMISSION.stream().anyMatch(item -> patchMatch.match(item, uri));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // check xem cái api mà người dùng yêu cầu có phải là 1 public api?

//        boolean isPublicAPI = checkIsPublicAPI(request.getRequestURI());

        if (checkIsPublicAPI(request)) {
            filterChain.doFilter(request, response);
        } else {
            String token = getToken(request);
            if (token == null) {
                // ko được phép truy cập
                resolver.resolveException(request, response, null, new RuntimeException("empty token"));
                return;
            }

            // => có token
            // check xem token có đúng hay ko => lấy thông tin account từ token
            Account account;
            try {
                account = tokenService.getAccountByToken(token);
            } catch (ExpiredJwtException e) {
                // response token hết hạn
                resolver.resolveException(request, response, null, new RuntimeException("Ivalid token"));
                return;
            } catch (MalformedJwtException malformedJwtException) {
                // response token sai
                resolver.resolveException(request, response, null, new RuntimeException("Ivalid token"));
                return;
            }
            // => token chuẩn
            // => cho phép truy cập
            // => lưu lại thông tin account
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    account,
                    token,
                    account.getAuthorities()
            );
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            // token ok, cho vao`
            filterChain.doFilter(request, response);
        }

    }

    public String getToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.substring(7);
    }
}
