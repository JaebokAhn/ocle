package com.yeol.ocle.config.jpaauditor;

import com.yeol.ocle.config.security.auth.PrincipalDetails;
import com.yeol.ocle.model.userinfo.User;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @CreatedBy, @LastModifiedBy 어노테이션 사용한 필드에 로그인 사용자ID를 자동으로 세팅해준다.
 */
@Primary
@Component
public class LoginUserAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null == authentication || !authentication.isAuthenticated()) {
            return null;
        }
        PrincipalDetails user = (PrincipalDetails) authentication.getPrincipal();
        return Optional.of(user.getUsername());
    }
}
