package com.example.news.config;

import com.example.news.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;


@Configuration
public class KimYozganiniBilish implements AuditorAware<User> {
    @Bean
    @Override
    public Optional<User> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymouse")) {
            User user = (User) authentication.getPrincipal();
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }
}