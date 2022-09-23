package com.example.news.controller;

import com.example.news.aop.CheckPermission;
import com.example.news.dto.CommentDTO;
import com.example.news.entity.Comment;
import com.example.news.response.ApiResponse;
import com.example.news.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    CommentService commentService;
    @CheckPermission(permission = "ADD_COMMENT")
    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody CommentDTO commentDTO){
        ApiResponse apiResponse=commentService.addComment(commentDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission(permission = "EDIT_COMMENT")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateComment(@PathVariable Long id,@RequestBody CommentDTO commentDTO){
        ApiResponse apiResponse=commentService.updateComment(id,commentDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission(permission = "DELETE_MY_COMMENT")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id){
        ApiResponse apiResponse=commentService.deleteComment(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/view")
    public List<Comment> viewComments(){
        return commentService.viewComments();
    }

    @GetMapping("/view/{id}")
    public Comment viewCommentOne(@PathVariable Long id){
        return commentService.viewCommentOne(id);
    }
}
