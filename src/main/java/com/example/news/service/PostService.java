package com.example.news.service;

import com.example.news.dto.PostDTO;
import com.example.news.entity.Post;
import com.example.news.repositry.PostRepository;
import com.example.news.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;


    public ApiResponse addPost(PostDTO postDTO) {
        try {
            Post post = new Post(postDTO.getTitle(), postDTO.getText(), postDTO.getUrl());
            postRepository.save(post);
            return new ApiResponse("Post qo'shildi", true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ApiResponse("Qo'shilmadi", false);
        }
    }

    public ApiResponse updatePost(PostDTO postDTO, Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setText(postDTO.getText());
            post.setTitle(postDTO.getTitle());
            post.setUrl(postDTO.getUrl());
            postRepository.save(post);
            return new ApiResponse("Yangilandi", true);
        } else {
            return new ApiResponse("Bunday post topilmadi", false);
        }
    }

    public ApiResponse deletePost(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            postRepository.deleteById(id);
            return new ApiResponse("O'chirildi", true);
        } else {
            return new ApiResponse("Bunday post topilmadi", false);
        }
    }

    public List<Post> viewPosts() {
        return postRepository.findAll();
    }

    public Post viewPostsOne(Long id) {
        return postRepository.findById(id).orElse(new Post());
    }
}
