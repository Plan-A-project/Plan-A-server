package com.plana.planb.domain.board.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
public class PostResponseDto {
    private String title;
    private String mainText;
    private String imgURL;
    private int likeCount;
    private int commentCount;
    private Timestamp day;
}
