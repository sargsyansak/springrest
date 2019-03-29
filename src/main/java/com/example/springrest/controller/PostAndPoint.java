package com.example.springrest.controller;

import com.example.springrest.model.Post;
import com.example.springrest.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PostAndPoint {

    @Autowired
    private PostRepository postRepository;


    @PostMapping("/post/add")
    public ResponseEntity add(@RequestBody Post post) {
        return ResponseEntity.ok(postRepository.save(post));
    }

    @GetMapping("/posts")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(postRepository.findAll());
    }

    @GetMapping("/post/{id}")
    public ResponseEntity getById(@PathVariable("id") int id) {
        Optional<Post> byId = postRepository.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok(byId.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/post/update")
    public ResponseEntity update(@RequestBody Post post) {
        Optional<Post> byId = postRepository.findById(post.getId());
        if (byId.isPresent()) {
            postRepository.save(post);
            return ResponseEntity.ok(post);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity delete(@PathVariable("id") int id) {
        Optional<Post> byId = postRepository.findById(id);
        if (byId.isPresent()) {
            postRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
