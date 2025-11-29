package com.sql.dbms.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sql.dbms.pojo.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    
} 
