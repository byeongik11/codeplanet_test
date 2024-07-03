package com.sparta.codeplanet.product.repository;

import com.sparta.codeplanet.product.dto.LikedResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomLikedFeedRepository {
    Page<LikedResponseDto> findLikedFeedsByUser(Long userId, Pageable pageable);
}
