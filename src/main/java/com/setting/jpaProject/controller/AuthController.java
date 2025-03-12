package com.setting.jpaProject.controller;

import com.setting.jpaProject.cookie.CookieUtil;
import com.setting.jpaProject.dto.LoginResponse;
import com.setting.jpaProject.jwt.TokenProvider;
import com.setting.jpaProject.jwt.TokenService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final TokenService tokenService;
    private final TokenProvider tokenProvider;

//    @GetMapping("/auth/success")
//    public ResponseEntity<LoginResponse> loginSuccess(@Valid LoginResponse loginResponse) {
//        return ResponseEntity.ok(loginResponse);
//    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(@CookieValue(value = "refreshToken", required = false) String refreshToken, HttpServletResponse response) {
        tokenService.deleteRefreshToken(tokenService.getMemberKey(refreshToken));
        CookieUtil.removeRefreshTokenCookie(response);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshAccessToken(@CookieValue(value = "refreshToken", required = false) String refreshToken, HttpServletResponse response) {
        if (refreshToken == null || refreshToken.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Refresh token is required");
        }

        // Refresh Token 검증 후 새로운 Access Token 발급
        String newAccessToken = tokenProvider.reissueRefreshToken(refreshToken, response);

        if (newAccessToken != null) {
            return ResponseEntity.ok(new LoginResponse(newAccessToken));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired refresh token");
        }
    }
}
