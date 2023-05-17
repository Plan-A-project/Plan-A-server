package com.plana.planb.auth.service;

import com.plana.planb.auth.config.jwt.TokenProvider;
import com.plana.planb.auth.domain.Member;
import com.plana.planb.auth.dto.MemberJoinDto;
import com.plana.planb.auth.dto.MemberJoinResponseDto;
import com.plana.planb.auth.dto.MemberLoginDto;
import com.plana.planb.auth.dto.jwt.TokenDto;
import com.plana.planb.auth.dto.jwt.TokenRequestDto;
import com.plana.planb.auth.exception.ex.CustomException;
import com.plana.planb.auth.exception.ex.ErrorCode;
import com.plana.planb.auth.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
//    private final AwsS3Service awsS3Service;

//    private final RedisService redisService;

    /**
     * 회원가입
     */
    @Transactional
    public MemberJoinResponseDto signup(MemberJoinDto memberJoinDto) {
        if(memberRepository.existsByEmail(memberJoinDto.getEmail())){
            throw new CustomException(ErrorCode.ALREADY_SAVED_MEMBER);
        }

        Member member = memberJoinDto.toMember(passwordEncoder);
//        member.changeProfileImage(randomProfileImage());
        memberRepository.save(member);

//        redisService.setValues(String.valueOf(member.getId()) + ".", member.getNickname());
        return new MemberJoinResponseDto(member.getEmail());
    }

    /**
     * 로그인
     */
    @Transactional
    public TokenDto login(MemberLoginDto memberLoginDto){
        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = memberLoginDto.toAuthentication();

        Member member = memberRepository.findByEmail(memberLoginDto.getEmail()).orElseThrow(() ->
                new CustomException(ErrorCode.MEMBER_EMAIL_NOT_FOUND));

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);
        tokenDto.setNickname(member.getNickname());
        // 5. 토큰 발급
        return tokenDto;
    }

    /**
     * 토큰 재발행
     */
    @Transactional
    public TokenDto reissue(TokenRequestDto tokenRequestDto){
        // 1. Refresh Token 검증
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new CustomException(ErrorCode.INVALID_REFRESH_TOKEN);
        }


        // 2. Access Token 에서 Member ID 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getRefreshToken());

        // redis에 있는 refreshToken과 비교
//        tokenProvider.checkRefreshToken(authentication.getName(), tokenRequestDto.getRefreshToken());

        Member member = memberRepository.findById(Long.parseLong(authentication.getName())).orElseThrow(() ->
                new CustomException(ErrorCode.MEMBER_EMAIL_NOT_FOUND));

        // 5. 새로운 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);
        tokenDto.setNickname(member.getNickname());

        // 토큰 발급
        return tokenDto;
    }

    /**
     * 로그아웃
     */
//    @Transactional
//    public void logout(String accessToken) {
//        Authentication authentication = tokenProvider.getAuthentication(accessToken);
//        tokenProvider.logout(authentication.getName(), accessToken);
//    }

    /**
     * 랜덤 프로필 이미지
     */
//    public String randomProfileImage(){
//        int random = (int) (Math.random() * 6) + 1;
//        String path = "member/default/" + random + ".jpg";
//        String thumbnailPath = awsS3Service.getThumbnailPath(path);
//        return thumbnailPath;
//    }
}
