package com.sparta.codeplanet.product.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.codeplanet.product.dto.UserInfoDto;
import com.sparta.codeplanet.product.entity.QUser;
import com.sparta.codeplanet.product.entity.User;
import com.sparta.codeplanet.product.entity.likes.QFeedLikes;
import com.sparta.codeplanet.product.entity.likes.QReplyLikes;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class CustomUserRepositoryImpl implements CustomUserRepository{

    private final EntityManager em;

    private final JPAQueryFactory queryFactory;

    public CustomUserRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public UserInfoDto findUserInfoById(Long userId) {
        QUser qUser = QUser.user;
        QFeedLikes qFeedLikes = QFeedLikes.feedLikes;
        QReplyLikes qReplyLikes = QReplyLikes.replyLikes;

        long likedFeedsCount = queryFactory.selectFrom(qFeedLikes)
            .where(qFeedLikes.user.id.eq(userId))
            .fetchCount();

        long likedReplysCount = queryFactory.selectFrom(qReplyLikes)
            .where(qReplyLikes.user.id.eq(userId))
            .fetchCount();

        User user = queryFactory.selectFrom(qUser)
            .where(qUser.id.eq(userId))
            .fetchOne();

        if (user == null) {
            return null;
        }

        return new UserInfoDto(
            user.getId(),
            user.getCompany().getName(),
            user.getEmail(),
            user.getNickname(),
            user.getIntro(),
            user.getUserRole(),
            user.getStatus(),
            (int) likedFeedsCount,
            (int) likedReplysCount
        );
    }
}
