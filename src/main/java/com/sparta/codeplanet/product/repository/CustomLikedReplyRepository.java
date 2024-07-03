package com.sparta.codeplanet.product.repository;

import com.sparta.codeplanet.product.dto.ReplyResponseDto;
import com.sparta.codeplanet.product.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomLikedReplyRepository {
    Page<ReplyResponseDto> findLikedReplysByUser(Long userId, Pageable pageable);
}
