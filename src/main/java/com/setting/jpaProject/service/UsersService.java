package com.setting.jpaProject.service;

import com.setting.jpaProject.dto.UsersDTO;
import com.setting.jpaProject.entity.Users;
import com.setting.jpaProject.error.CustomException;
import com.setting.jpaProject.error.ErrorCode;
import com.setting.jpaProject.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    // 사용자 생성
    @Transactional
    public UsersDTO.response createUser(UsersDTO.request request) {

        //동일한 이메일이 있을경우 (중복)
        if (usersRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new CustomException(ErrorCode.USERS_DUPLICATE);
        }

        Users users = Users.fromDTO(request, passwordEncoder);
        return UsersDTO.response.fromEntity(usersRepository.save(users));
    }

    // 모든 사용자 조회
    public List<UsersDTO.response> getAllUsers() {
        // Retrieve all users from the repository
        List<Users> users = usersRepository.findAll();

        // Convert the list of Users entities to a list of Response DTOs
        return users.stream()
                .map(UsersDTO.response::fromEntity)  // Correct usage of fromEntity method
                .collect(Collectors.toList());  // Collect the results into a List
    }


    // 사용자 단일 조회
    public UsersDTO.response getUserById(Long id) {
        // Retrieve the user entity by ID
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.USERS_NOT_FOUND));

        // Convert the Users entity to UsersDTO.response and return it
        return UsersDTO.response.fromEntity(user);
    }

    // 사용자 수정
    @Transactional
    public UsersDTO.response updateUser(Long id, UsersDTO.request request) {
        // Retrieve the user entity by ID
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.USERS_NOT_FOUND));

        // Update the user details
        user.updateUser(request, passwordEncoder);

        // Convert the updated user entity to UsersDTO.response and return it
        return UsersDTO.response.fromEntity(usersRepository.save(user));
    }

    // 사용자 삭제
    @Transactional
    public void deleteUser(Long id) {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.USERS_NOT_FOUND));
        usersRepository.delete(user);
    }
}
