package com.example.news.controller;

import com.example.news.aop.CheckPermission;
import com.example.news.dto.LavozimDTO;
import com.example.news.repositry.LavozimRepository;
import com.example.news.response.ApiResponse;
import com.example.news.service.LavozimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lavozim")
public class LavozimController {
    @Autowired
    LavozimService lavozimService;


    @CheckPermission(permission ="ADD_LAVOZIM")
    @PostMapping()
    public ResponseEntity<?> addLavozim(@RequestBody LavozimDTO lavozimDTO){
     /*sd*/   ApiResponse apiResponse=lavozimService.addLavozim(lavozimDTO);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);

    }

}
