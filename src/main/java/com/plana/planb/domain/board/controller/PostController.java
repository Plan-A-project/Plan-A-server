package com.plana.planb.domain.board.controller;

import com.plana.planb.domain.board.controller.dto.BoardResponseDto;
import com.plana.planb.domain.board.controller.dto.PostResponseDto;
import com.plana.planb.domain.board.controller.dto.postRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Operation(
            summary = "게시글 조회 메소드",
            description = "게시글의 키를 담아 요청하면 게시글(제목, 본문, 이미지, 좋아요)를 반환합니다. "
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "게시글 조회 성공"
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
    @GetMapping("/{postSeq}")
    public PostResponseDto getPost(@PathVariable int postSeq) {
        PostResponseDto postResponseDto = null;
        return postResponseDto;
    }

    @Operation(
            summary = "게시글 생성 메소드",
            description = "게시글 내용을 담아 게시글을 등록합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "게시글 등록 성공"
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
    @PostMapping("/")
    public void enterPost(@RequestPart postRequestDto postRequestDto) {
        //service
    }

    @Operation(
            summary = "게시글 삭제 소드",
            description = "게시글의 키를 담아 요청하면 게시글을 삭제 합니다. "
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "게시글 삭제 성공"
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
    @DeleteMapping("/{postSeq}")
    public void deletePost(@PathVariable int postSeq) {
    }

    @Operation(
            summary = "게시글 수정 메소드",
            description = "게시글의 키와 변경 내용을 담아 요청하면 게시글을 수정합니다. "
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "게시글 수정 성공"
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
    @PutMapping("/{postSeq}")
    public void updatePost(@PathVariable int postSeq, @RequestPart postRequestDto postRequestDto ) {

    }

}
