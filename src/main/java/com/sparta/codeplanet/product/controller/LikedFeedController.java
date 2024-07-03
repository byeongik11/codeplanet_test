package com.sparta.codeplanet.product.controller;

import com.sparta.codeplanet.global.security.UserDetailsImpl;
import com.sparta.codeplanet.product.dto.LikedResponseDto;
import com.sparta.codeplanet.product.dto.PageableResponse;
import com.sparta.codeplanet.product.service.LikesService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feed")
public class LikedFeedController {

    private  final LikesService likesService;

    public LikedFeedController(LikesService likesService) {
        this.likesService = likesService;
    }

    @GetMapping("/liked")
    public ResponseEntity<PageableResponse<LikedResponseDto>> getLikedFeeds(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size) {
        Page<LikedResponseDto> responseDto = likesService.getLikedFeedsByUser(userDetails.getUser().getId(), page, size);
        PageableResponse<LikedResponseDto> responseEntity = new PageableResponse<>(responseDto);
        return ResponseEntity.ok(responseEntity);
    }


}
