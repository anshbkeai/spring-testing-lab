package com.sql.dbms.repositry.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sql.dbms.pojo.book.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member,Integer>{

}
