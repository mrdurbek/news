package com.example.news.service;

import com.example.news.dto.CommentDTO;
import com.example.news.entity.Comment;
import com.example.news.entity.Post;
import com.example.news.entity.User;
import com.example.news.entity.enums.Huquq;
import com.example.news.repositry.CommentRepository;
import com.example.news.repositry.PostRepository;
import com.example.news.response.ApiResponse;
import com.example.news.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;

    public ApiResponse deleteComment(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()) {

            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (comment.get().getCreatedBy().getId().equals(user.getId()) || user.getLavozim().getHuquqList().contains(Huquq.DELETE_COMMENT)) {
                commentRepository.deleteById(id);
                return new ApiResponse("Comment o'chirildi", true);
            } else {
                return new ApiResponse("Sizda bunday huquq yo'q", false);
            }

        } else {
            return new ApiResponse("Bunday comment topilmadi", false);
        }
    }

    public ApiResponse addComment(CommentDTO commentDTO) {
        Optional<Post> post = postRepository.findById(commentDTO.getPost_id());
        if (post.isPresent()) {
            Comment comment = new Comment(commentDTO.getText(), post.get());
            commentRepository.save(comment);
            return new ApiResponse("Comment qo'shildi", true);
        } else {
            return new ApiResponse("Bunday post topilmadi", false);
        }
    }

    public ApiResponse updateComment(Long id, CommentDTO commentDTO) {
        Optional<Comment> optional = commentRepository.findById(id);
        if (optional.isPresent()){
            Optional<Post> optional1 = postRepository.findById(commentDTO.getPost_id());
            if (optional1.isPresent()){
                Comment comment= optional.get();
                comment.setPost(optional1.get());
                comment.setText(commentDTO.getText());
                commentRepository.save(comment);
                return new ApiResponse("Yangilandi",true);
            }else {
                return new ApiResponse("Bunday post topilmadi",false);
            }
        }else {
            return new ApiResponse("Bunday comment topilmadi",false);
        }
    }

    public List<Comment> viewComments() {
        return commentRepository.findAll();
    }

    public Comment viewCommentOne(Long id) {
       return commentRepository.findById(id).orElse(new Comment());
    }
}
