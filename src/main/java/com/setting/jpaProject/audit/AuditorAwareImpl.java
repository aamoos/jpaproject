package com.setting.jpaProject.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // 현재 로그인한 사용자 반환 (Spring Security 사용)
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
