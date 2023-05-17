package com.plana.planb.auth.controller;

import com.plana.planb.auth.dto.MemberJoinDto;
import com.plana.planb.auth.dto.MemberLoginDto;
import com.plana.planb.auth.dto.jwt.TokenDto;
import com.plana.planb.auth.dto.jwt.TokenRequestDto;
import com.plana.planb.auth.dto.response.ResponseDto;
import com.plana.planb.auth.exception.ex.CustomException;
import com.plana.planb.auth.exception.ex.CustomValidationException;
import com.plana.planb.auth.exception.ex.ErrorCode;
import com.plana.planb.auth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid MemberJoinDto memberJoinDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }

            throw new CustomValidationException("유효성 검사 실패", errorMap);
        } else {
            if (!memberJoinDto.getPassword().equals(memberJoinDto.getPasswordConfirm())) {
                throw new CustomException(ErrorCode.NOT_EQUAL_PASSWORD );
            }
            return new ResponseEntity<ResponseDto>(new ResponseDto(200, "회원가입 성공",
                    authService.signup(memberJoinDto)), HttpStatus.OK);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberLoginDto memberLoginDto) {
        TokenDto token = authService.login(memberLoginDto);
        return new ResponseEntity<ResponseDto>(new ResponseDto<>(200, "로그인 성공",
                token), HttpStatus.OK);
    }

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return new ResponseEntity<ResponseDto>(new ResponseDto<>(200, "토큰 재발행",
                authService.reissue(tokenRequestDto)), HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request) {
        String accessToken = request.getHeader("Authorization").substring(7);
//        authService.logout(accessToken);
        return new ResponseEntity<>(new ResponseDto<>(200, "로그아웃 완료", null)
                , HttpStatus.OK);
    }
}
