package com.example.news.entity;

import com.example.news.entity.enums.Huquq;
import com.example.news.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Lavozim extends AbsEntity{

    @Column(nullable = false,unique = true)
    private String name;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Huquq> huquqList;
    @Column(nullable = false,columnDefinition = "text")
    private String description;



}
