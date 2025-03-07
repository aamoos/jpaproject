package com.setting.jpaProject.entity;

import com.setting.jpaProject.audit.BaseEntity;
import com.setting.jpaProject.dto.UsersDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //기본 생성자 -> jpa는 리플렉션 기술을 사용해서 기본생성자를 생성해야함
@Getter
public class Users extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    private String name;
    private String email;
    private int age;

    public void updateUser(String name, String email, int age) {
        this.password = password;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    // DTO -> Entity 변환
    public static Users fromDTO(UsersDTO.request request) {
        Users user = new Users();
        user.password = request.getPassword();
        user.name = request.getName();
        user.email = request.getEmail();
        user.age = request.getAge();
        return user;
    }
}
