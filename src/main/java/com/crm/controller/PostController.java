package com.crm.controller;

import com.crm.entity.Post;
import com.crm.repository.CommentsRepository;
import com.crm.repository.PostRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostRepository postRepository;

    public PostController(PostRepository postRepository, CommentsRepository ignoredCommentsRep) {
        this.postRepository = postRepository;
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
