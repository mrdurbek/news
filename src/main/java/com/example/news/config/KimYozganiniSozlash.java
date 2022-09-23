package com.example.news.config;

import com.example.news.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;



@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class KimYozganiniSozlash {
    @Bean
    AuditorAware<User> auditorAware(){
        return new KimYozganiniBilish();
    }
}

