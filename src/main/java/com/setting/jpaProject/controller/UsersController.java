package com.setting.jpaProject.controller;

import com.setting.jpaProject.dto.UsersDTO;
import com.setting.jpaProject.entity.Users;
import com.setting.jpaProject.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    // 사용자 생성
    @PostMapping
    public ResponseEntity<UsersDTO.response> createUser(@RequestBody UsersDTO.request request) {
        return new ResponseEntity<>(usersService.createUser(request), HttpStatus.CREATED);
    }

    // 모든 사용자 조회
    @GetMapping
    public ResponseEntity<List<UsersDTO.response>> getAllUsers() {
        return ResponseEntity.ok(usersService.getAllUsers());
    }

    // 사용자 단일 조회
    @GetMapping("/{id}")
    public ResponseEntity<UsersDTO.response> getUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(usersService.getUserById(id));
    }

    // 사용자 수정
    @PutMapping("/{id}")
    public ResponseEntity<UsersDTO.response> updateUser(@PathVariable("id") Long id, @RequestBody UsersDTO.request request) {
        return ResponseEntity.ok(usersService.updateUser(id, request));
    }

    // 사용자 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        usersService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
