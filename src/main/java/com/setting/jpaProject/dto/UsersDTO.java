package com.setting.jpaProject.dto;

import com.setting.jpaProject.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UsersDTO {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class request {
        private String name;
        private String email;
        private String password;
        private int age;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class response {
        private String name;
        private String email;
        private int age;

        // Entity -> DTO 변환
        public static response fromEntity(Users users) {
            return response.builder()
                    .name(users.getName())
                    .email(users.getEmail())
                    .age(users.getAge())
                    .build();
        }

    }
}