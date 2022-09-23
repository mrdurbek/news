package com.example.news.entity;

import com.example.news.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Comment extends AbsEntity {
    @Column(nullable = false,columnDefinition = "text")
    private String text;
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;
}
