package com.sparta.codeplanet.product.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.codeplanet.product.dto.ReplyResponseDto;
import com.sparta.codeplanet.product.entity.QReply;
import com.sparta.codeplanet.product.entity.Reply;
import com.sparta.codeplanet.product.entity.likes.QReplyLikes;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class CustomLikedReplyRepositoryImpl implements CustomLikedReplyRepository{

    private final EntityManager em;

    private final JPAQueryFactory queryFactory;

    public CustomLikedReplyRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<ReplyResponseDto> findLikedReplysByUser(Long userId, Pageable pageable) {
        QReply qReply = QReply.reply;
        QReplyLikes qReplyLikes = QReplyLikes.replyLikes;

        List<ReplyResponseDto> results = queryFactory.select( Projections.constructor(ReplyResponseDto.class,
                qReply.id,
                qReply.content,
                qReply.feed.id,
                qReply.user.id,
                qReply.createdAt,
                qReply.updatedAt,
                qReply.likesCount

            ))
            .from(qReply)
            .join(qReply.likesList, qReplyLikes)
            .on(qReplyLikes.reply.id.eq(qReply.id))
            .where(qReplyLikes.user.id.eq(userId))
            .orderBy(qReply.createdAt.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        long total = queryFactory.selectFrom(qReplyLikes)
            .where(qReplyLikes.user.id.eq(userId))
            .fetchCount();

        return new PageImpl<>(results, pageable, total);
    }
}
