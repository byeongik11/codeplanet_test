package com.sparta.codeplanet.product.controller;

import com.sparta.codeplanet.global.security.UserDetailsImpl;
import com.sparta.codeplanet.product.dto.LikedResponseDto;
import com.sparta.codeplanet.product.dto.PageableResponse;
import com.sparta.codeplanet.product.dto.ReplyResponseDto;
import com.sparta.codeplanet.product.entity.Reply;
import com.sparta.codeplanet.product.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reply")
public class LikedReplyController {

    private final ReplyService replyService;

    public LikedReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @GetMapping("/liked")
    public ResponseEntity<PageableResponse<ReplyResponseDto>> getLikedReply(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size) {
        Page<ReplyResponseDto> responseDto = replyService.getLikedReplysByUser(userDetails.getUser().getId(), page, size);
        PageableResponse<ReplyResponseDto> responseEntity = new PageableResponse<>(responseDto);
        return ResponseEntity.ok(responseEntity);
    }
}
