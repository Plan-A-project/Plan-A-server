package com.plana.planb.domain.board.controller;

import com.plana.planb.domain.board.controller.dto.BoardResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/boards")
public class BoardController {

    @Operation(
            summary = "익명 게시판 조회 메소드",
            description = "기본으로 최신 게시글 (제목, 요약된 본문, 이미지, 좋아요 수, 댓글 수, 날짜) 리스트를 반환합니다. "
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "게시판 조회 성공"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "토큰 검증 실패"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "권한 검증 실패"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "서버 에러"
            )
    })
    @GetMapping("/")
    public BoardResponseDto getBoard() {
        BoardResponseDto boardResponseDto = null;
        //파라미터로 최신순, 인기순 조회 구별해야함

        return boardResponseDto;
    }

    @Operation(
            summary = "익명 게시판 검색 조회 메소드",
            description = "최신 게시글(제목, 요약된 본문, 이미지, 좋아요 수, 댓글 수, 날짜) 리스트를 반환합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "게시판 조회 성공"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "토큰 검증 실패"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "권한 검증 실패"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "서버 에러"
            )
    })
    @GetMapping("/search")
    public BoardResponseDto getBoardSearch(@RequestParam(value = "keyword", required = false) String keyword) {
        BoardResponseDto boardResponseDto = null;

        return boardResponseDto;
    }

}
