package com.plana.planb.domain.board.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private String title;
    private String shortText;
    private String imgURL;
    private int likeCount;
    private int commentCount;
    private Timestamp day;
}
