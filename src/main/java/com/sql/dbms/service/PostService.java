package com.sql.dbms.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sql.dbms.pojo.Posts;
import com.sql.dbms.pojo.User;
import com.sql.dbms.pojo.UserSummaryPostDto;
import com.sql.dbms.repositry.PostRepository;
import com.sql.dbms.repositry.UserRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    

    public List<UserSummaryPostDto>  summary() {
        // need to fetch the Latest post // For that User 
        // need to go in all the users 
       return userRepository.findAll().stream()
        .map(x -> {
            Optional<Posts> p = postRepository.findTopByUserOrderByCreatedAtDesc(x);
            int postCount = postRepository.countByUser(x);
            String latestPostTitle = (!p.isPresent())? null : p.get().getTitle();
            Instant postCreated = (!p.isPresent())? null : p.get().getCreatedAt();
            
            return  new UserSummaryPostDto(x.getId(), x.getName(), postCount, latestPostTitle,postCreated);        
        }).toList();

        
    }

    public List<Posts> getPostByUser(int user) {
        return postRepository.findByUser(userRepository.findById(user).get());
    }
}
