package com.setting.jpaProject.repository;

import com.setting.jpaProject.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    // 기본적인 CRUD 메서드는 JpaRepository에서 제공됩니다.
    // 예를 들어, findById(), save(), delete() 등을 사용할 수 있습니다.

    // 추가적으로 필요한 커스텀 쿼리가 있다면 여기서 작성할 수 있습니다.
    // 예: Optional<Users> findByEmail(String email);
    Optional<Users> findByEmail(String email);
}
