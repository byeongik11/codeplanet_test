package com.sparta.codeplanet.product.repository;

import com.sparta.codeplanet.product.entity.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikedFeedRepository extends JpaRepository<Feed, Long>, CustomLikedFeedRepository{

}
