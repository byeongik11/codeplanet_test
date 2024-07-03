package com.sparta.codeplanet.product.dto;

import com.sparta.codeplanet.global.enums.Status;
import com.sparta.codeplanet.global.enums.UserRole;
import com.sparta.codeplanet.product.entity.User;
import lombok.Getter;

@Getter
public class UserInfoDto {

    private Long userId;
    private String companyName;
    private String email;
    private String nickname;
    private String intro;
    private UserRole role;
    private Status status;
    private int likedFeedsCount;
    private int likedReplysCount;

    public UserInfoDto(User user) {
        this.userId = user.getId();
        this.companyName = user.getCompany().getName();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.intro = user.getIntro();
        this.role = user.getUserRole();
        this.status = user.getStatus();
    }

    public UserInfoDto(Long userId, String companyName, String email, String nickname, String intro,
        UserRole role, Status status, int likedFeedsCount, int likedReplysCount) {
        this.userId = userId;
        this.companyName = companyName;
        this.email = email;
        this.nickname = nickname;
        this.intro = intro;
        this.role = role;
        this.status = status;
        this.likedFeedsCount = likedFeedsCount;
        this.likedReplysCount = likedReplysCount;
    }
}
