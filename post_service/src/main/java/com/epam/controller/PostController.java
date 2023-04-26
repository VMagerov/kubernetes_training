package com.epam.controller;

import com.epam.domain.Post;
import com.epam.domain.PostCreateForm;
import com.epam.domain.PostUpdateForm;
import com.epam.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    private final String GREETING = "Hello, k8s!";

    @Autowired
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/greeting")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String getGreeting() {

        return GREETING;
    }

    @GetMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Post get(@PathVariable Long id) {

        return postService.get(id);
    }

    @GetMapping("/posts")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Post> getAll() {

        return postService.getAll();
    }

    @DeleteMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void delete(@PathVariable Long id) {

        postService.delete(id);
    }

    @PutMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Post update(@PathVariable Long id, @RequestBody PostUpdateForm postUpdateForm) {

        return postService.update(id, postUpdateForm);
    }

    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Post create(@RequestBody PostCreateForm postCreateForm) {

        return postService.create(postCreateForm);
    }
}
