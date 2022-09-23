package com.example.news.entity;

import com.example.news.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Post extends AbsEntity {
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String text;
    private String url;

}
