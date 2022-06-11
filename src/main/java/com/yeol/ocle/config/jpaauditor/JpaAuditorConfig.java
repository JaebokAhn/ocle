package com.yeol.ocle.config.jpaauditor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaAuditorConfig {
    @Bean
    public AuditorAware<String> auditorProvider() {
        return new LoginUserAuditorAware();
    }
}
