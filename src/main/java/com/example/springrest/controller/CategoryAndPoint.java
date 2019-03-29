package com.example.springrest.controller;

import com.example.springrest.model.Category;
import com.example.springrest.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CategoryAndPoint {

    @Autowired
    private CategoryRepository categoryRepository;


    @PostMapping("/category/add")
    public ResponseEntity add(@RequestBody Category category) {
        if (categoryRepository.findByName(category.getName()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        categoryRepository.save(category);
        return ResponseEntity.ok(category);
    }

    @GetMapping("/categories")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(categoryRepository.findAll());
    }

    @GetMapping("/category/{id}")
    public ResponseEntity getById(@PathVariable("id") int id) {
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok(byId.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/category/update")
    public ResponseEntity update(@RequestBody Category category) {
        Optional<Category> byId = categoryRepository.findById(category.getId());
        if (byId.isPresent()) {
            categoryRepository.save(category);
            return ResponseEntity.ok(category);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isPresent()) {
            categoryRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
