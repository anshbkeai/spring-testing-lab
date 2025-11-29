package com.sql.dbms.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sql.dbms.pojo.Posts;
import java.util.List;
import java.util.Optional;
import java.time.Instant;
import com.sql.dbms.pojo.User;



@Repository
public interface PostRepository extends JpaRepository<Posts,Integer> {

    List<Posts> findByCreatedAt(Instant createdAt);

    List<Posts> findByUser(User user);

    Optional<Posts> findTopByUserOrderByCreatedAtDesc(Object user);

    
    int countByUser(User user);

}
