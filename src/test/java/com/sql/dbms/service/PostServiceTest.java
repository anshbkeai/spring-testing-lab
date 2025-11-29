package com.sql.dbms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sql.dbms.pojo.Posts;
import com.sql.dbms.pojo.User;
import com.sql.dbms.pojo.UserSummaryPostDto;
import com.sql.dbms.repositry.PostRepository;
import com.sql.dbms.repositry.UserRepository;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @Mock
    private PostRepository postRepository;   
    @Mock
    private  UserRepository userRepository;

    @InjectMocks
    private PostService postService; // this is for to test about to inject and we need about to create the object 

    private User user;
    private Posts posts;
    private UserSummaryPostDto userSummaryPostDto;

    @BeforeEach
    void setup() {
        user = User.builder()
                    .id(123).name("user-123").email("user@email.com").createdAt(Instant.now()).build();
        posts = Posts.builder().id(1).body("This is Test Post body")
                                .createdAt(Instant.now())
                                .title("Test post")
                                .user(user)
                                .build();
        userSummaryPostDto = UserSummaryPostDto.builder()
                                                .createdAt(Instant.now())
                                                .latestPostTitle("Test post")
                                                .postCount(1)
                                                .name("user-123")
                                                .userId(123)
                                                .build();
                            
        
    }
    @Test
    void testGetPostByUser() {
        when(userRepository.findById(123)).thenReturn(Optional.of(user));
        when(postRepository.findByUser(user)).thenReturn(List.of(posts));

        var results = postService.getPostByUser(123);

        System.out.println(results);

        assertNotNull(results);
        assertEquals(List.of(posts), results);
        verify(userRepository).findById(user.getId());
    }

    @Test
    void testSummary() {

    }
}
