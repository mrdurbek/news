package com.example.news.dto;

import com.example.news.entity.enums.Huquq;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LavozimDTO {
    private String name;
    private List<Huquq> huquqList;
    private String description;

}