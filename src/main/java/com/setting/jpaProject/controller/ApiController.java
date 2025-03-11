package com.setting.jpaProject.controller;

import com.setting.jpaProject.dto.MemberDto;
import com.setting.jpaProject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ApiController {

    private final MemberService memberService;

    @GetMapping("/users")
    public ResponseEntity<List<MemberDto>> loginSuccess() {
        return ResponseEntity.ok(memberService.findAll());
    }

}
