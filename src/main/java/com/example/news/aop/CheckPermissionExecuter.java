package com.example.news.aop;

import com.example.news.entity.User;

import com.example.news.exceptions.ResourceNdException;
import com.example.news.repositry.CommentRepository;
import com.example.news.repositry.LavozimRepository;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Component;


@Component
@Aspect
public class CheckPermissionExecuter {
    @Autowired
    LavozimRepository lavozimRepository;
    @Autowired
    CommentRepository commentRepository;
    
    @Before(value = "@annotation(checkPermission)")
    public void checkUserPermission(CheckPermission checkPermission){
        String permission = checkPermission.permission();
        User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean checkker = false;
        for (GrantedAuthority authority : user.getAuthorities()) {
            if (authority.getAuthority().equals(permission)){
                checkker=true;
            break;
            }
        }
        if (!checkker){
            throw new ResourceNdException("Sizda bunday huquq yo'q");
        }
    }



}
