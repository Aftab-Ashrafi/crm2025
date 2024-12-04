package com.crm.controller;

import com.crm.entity.Post;
import com.crm.repository.CommentsRepository;
import com.crm.repository.PostRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private PostRepository postRepository;
    private CommentsRepository  commentsRep;

    public PostController(PostRepository postRepository, CommentsRepository commentsRep) {
        this.postRepository = postRepository;
        this.commentsRep = commentsRep;
    }
    @PostMapping
    public String createPost(
            @RequestBody Post post
    ){
        postRepository.save(post);
        return "Post saved successfully";
    }
    @DeleteMapping
    public void deletePost(){
        postRepository.deleteById(1L);
    }

}
