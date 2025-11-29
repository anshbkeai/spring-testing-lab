package com.sql.dbms.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sql.dbms.pojo.Posts;
import com.sql.dbms.pojo.UserSummaryPostDto;
import com.sql.dbms.service.PostService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/users/summary")
    public ResponseEntity<List<UserSummaryPostDto>> getSummary() {
        return ResponseEntity.ok().body(postService.summary());
    }
    
    @GetMapping("/post/{userId}")
    public ResponseEntity<List<Posts>>  getPost(@PathVariable int userId) {
        return ResponseEntity.ok().body(postService.getPostByUser(userId));
    }
    
}
