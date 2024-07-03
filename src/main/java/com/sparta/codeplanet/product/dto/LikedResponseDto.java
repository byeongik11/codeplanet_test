package com.sparta.codeplanet.product.dto;

import com.sparta.codeplanet.global.enums.Status;
import com.sparta.codeplanet.product.entity.Feed;
import lombok.Getter;

@Getter
public class LikedResponseDto {
    private Long id;
    private String title;
    private String content;
    private String user;
    private Status status;
    private int likesCount;

    public LikedResponseDto(Feed feed) {
        this.id = feed.getId();
        this.title = feed.getTitle();
        this.content = feed.getContent();
        this.user = feed.getUser().getUsername();
        this.status = feed.getStatus();
        this.likesCount = feed.getLikesCount();
    }

    public LikedResponseDto(Long id, String title, String content, String user, Status status,
        int likesCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.status = status;
        this.likesCount = likesCount;
    }
}
