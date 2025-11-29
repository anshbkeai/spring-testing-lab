package com.sql.dbms.pojo;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.RequiredArgsConstructor;

@Data

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class UserSummaryPostDto {

    private int userId;
    private String name;

    private int postCount;

    private String latestPostTitle;
    private Instant createdAt;

}
