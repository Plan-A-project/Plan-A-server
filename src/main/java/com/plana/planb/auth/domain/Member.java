package com.plana.planb.auth.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;

    private String name;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String nickname;

    private String certificate;

    private String profileImage;

    public void changeProfileImage(String profileImage){
        this.profileImage = profileImage;
    }

    public void changeMemberId(Long memberId) {
        this.id = memberId;
    }

    public void profileModify(String profileImage, String nickname) {
        this.profileImage = profileImage;
        this.nickname = nickname;
    }

}
