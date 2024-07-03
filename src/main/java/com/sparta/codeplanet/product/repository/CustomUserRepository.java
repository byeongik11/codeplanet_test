package com.sparta.codeplanet.product.repository;

import com.sparta.codeplanet.product.dto.UserInfoDto;

public interface CustomUserRepository {
    UserInfoDto findUserInfoById(Long userId);
}
