package com.example.news.component;

import com.example.news.entity.Lavozim;
import com.example.news.entity.User;
import com.example.news.entity.enums.Huquq;
import com.example.news.repositry.LavozimRepository;
import com.example.news.repositry.UserRepository;
import com.example.news.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;
    @Autowired
    LavozimRepository lavozimRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.sql.init.mode}")
    private String modeType;

    @Override
    public void run(String... args) {
        if (modeType.equals("always")) {
            Lavozim admin = lavozimRepository.save(new Lavozim(AppConstants.ADMIN, Arrays.asList(Huquq.values()),"sistemani boshqaradi"));

           Lavozim user = lavozimRepository.save(new Lavozim(AppConstants.USER, Arrays.asList(Huquq.ADD_COMMENT, Huquq.EDIT_COMMENT, Huquq.DELETE_COMMENT),"foydalanuvchi"));

            userRepository.save(new User("Admin", "admin", passwordEncoder.encode("admin123"), admin, true));
            userRepository.save(new User("User", "user", passwordEncoder.encode("user123"), user, true));
        }
    }
}
