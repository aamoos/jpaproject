package com.setting.jpaProject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class LoginDTO {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class request {
        private String email;
        private String password;
    }

    @Getter
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class response {
        private String message;
        private String token;
    }

}

