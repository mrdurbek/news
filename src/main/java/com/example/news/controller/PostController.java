package com.example.news.controller;

import com.example.news.aop.CheckPermission;
import com.example.news.dto.PostDTO;
import com.example.news.entity.Post;
import com.example.news.response.ApiResponse;
import com.example.news.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    PostService postService;

    @CheckPermission(permission = "ADD_POST")
    @PostMapping
    public ResponseEntity<?> creatPost(@RequestBody PostDTO postDTO) {
        ApiResponse apiResponse = postService.addPost(postDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
    @CheckPermission(permission = "EDIT_POST")
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, PostDTO postDTO){
        ApiResponse apiResponse=postService.updatePost(postDTO,id);
      return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
    @CheckPermission(permission = "DELETE_POST")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id){
        ApiResponse apiResponse=postService.deletePost(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/view")
    public List<Post> viewPost(){
      return   postService.viewPosts();
    }
    @GetMapping("/view/{id}")
    public Post viewPostOne(@PathVariable Long id){
        return   postService.viewPostsOne(id);
    }
}
