package com.crm.controller;

import com.crm.entity.Post;
import com.crm.repository.CommentsRepository;
import com.crm.repository.PostRepository;
import org.hibernate.annotations.Comment;
import org.springframework.web.bind.annotation.*;
import com.crm.entity.Comments;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {
    private PostRepository postRepository;
    private CommentsRepository commentsRepository;
    
    public CommentController(PostRepository postRepository, CommentsRepository commentsRepository) {
        this.postRepository = postRepository;
        this.commentsRepository = commentsRepository;
    }
    @PostMapping
    public String CreateComment(
        @RequestBody Comments comment,
        @RequestParam long postId
    ) {
        Post post = postRepository.findById(postId).get();
        comment.setPost(post);
        commentsRepository.save(comment);
        return "Comment created successfully";
    }
}
