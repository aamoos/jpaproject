package com.setting.jpaProject.entity;

import com.setting.jpaProject.audit.BaseEntity;
import com.setting.jpaProject.dto.UsersDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //기본 생성자 -> jpa는 리플렉션 기술을 사용해서 기본생성자를 생성해야함
@Getter
public class Users extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    private String name;
    private String email;
    private int age;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles; // ROLE_USER, ROLE_ADMIN 저장

    public void updateUser(UsersDTO.request request, PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
        this.name = request.getName();
        this.email = request.getEmail();
        this.age = request.getAge();
    }

    // DTO -> Entity 변환
    public static Users fromDTO(UsersDTO.request request, PasswordEncoder passwordEncoder) {
        Users user = new Users();
        user.password = passwordEncoder.encode(request.getPassword());
        user.name = request.getName();
        user.email = request.getEmail();
        user.age = request.getAge();
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> (GrantedAuthority) () -> role).toList();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
