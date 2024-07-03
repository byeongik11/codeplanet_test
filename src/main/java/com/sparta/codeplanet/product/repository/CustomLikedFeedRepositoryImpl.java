package com.sparta.codeplanet.product.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.codeplanet.product.dto.LikedResponseDto;
import com.sparta.codeplanet.product.entity.QFeed;
import com.sparta.codeplanet.product.entity.likes.QFeedLikes;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class CustomLikedFeedRepositoryImpl implements CustomLikedFeedRepository{

    private final EntityManager em;

    private final JPAQueryFactory queryFactory;

    public CustomLikedFeedRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<LikedResponseDto> findLikedFeedsByUser(Long userId, Pageable pageable) {
        QFeed qFeed = QFeed.feed;
        QFeedLikes qFeedLikes = QFeedLikes.feedLikes;

        List<LikedResponseDto> feeds = queryFactory.select(   Projections.constructor(LikedResponseDto.class,
                qFeed.id,
                qFeed.title,
                qFeed.content,
                qFeed.user.username,
                qFeed.status,
                qFeed.likesCount

            ))
            .from(qFeed)
            .join(qFeed.likesList, qFeedLikes)
            .where(qFeedLikes.user.id.eq(userId))
            .orderBy(qFeed.createdAt.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        long total = queryFactory.selectFrom(qFeed)
            .join(qFeed.likesList, qFeedLikes)
            .where(qFeedLikes.user.id.eq(userId))
            .fetchCount();

        return new PageImpl<>(feeds, pageable, total);
    }
}
