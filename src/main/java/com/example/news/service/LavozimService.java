package com.example.news.service;

import com.example.news.dto.LavozimDTO;
import com.example.news.entity.Lavozim;
import com.example.news.repositry.LavozimRepository;
import com.example.news.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LavozimService {

    @Autowired
    LavozimRepository lavozimRepository;


    public ApiResponse addLavozim(LavozimDTO lavozimDTO) {
        if (!lavozimRepository.existsByName(lavozimDTO.getName())){
            Lavozim lavozim=new Lavozim(lavozimDTO.getName(), lavozimDTO.getHuquqList(),lavozimDTO.getDescription());
/*/ljk*/            lavozimRepository.save(lavozim);
            return new ApiResponse("Muvaffaqiyatli qo'shildi",true);
        }else {
            return new ApiResponse("Bunday lavozim bor",false);
        }
    }
}
