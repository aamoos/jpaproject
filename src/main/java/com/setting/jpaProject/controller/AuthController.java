package com.setting.jpaProject.controller;

import com.setting.jpaProject.dto.LoginResponse;
import com.setting.jpaProject.jwt.TokenProvider;
import com.setting.jpaProject.jwt.TokenService;
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
public class AuthController {

    private final TokenService tokenService;
    private final TokenProvider tokenProvider;

//    @GetMapping("/auth/success")
//    public ResponseEntity<LoginResponse> loginSuccess(@Valid LoginResponse loginResponse) {
//        return ResponseEntity.ok(loginResponse);
//    }

    @DeleteMapping("/auth/logout")
    public ResponseEntity<Void> logout(@AuthenticationPrincipal UserDetails userDetails) {
        tokenService.deleteRefreshToken(userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshAccessToken(@RequestBody Map<String, String> requestBody) {
        String refreshToken = requestBody.get("refreshToken");

        if (StringUtils.isEmpty(refreshToken)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Refresh token is required");
        }

        // refreshToken을 검증하고 새로운 accessToken을 발급
        String newAccessToken = tokenProvider.reissueAccessToken(refreshToken);

        if (newAccessToken != null) {
            return ResponseEntity.ok(new LoginResponse(newAccessToken));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired refresh token");
        }
    }
}
