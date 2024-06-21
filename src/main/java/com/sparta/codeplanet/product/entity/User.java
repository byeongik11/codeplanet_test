package com.sparta.codeplanet.product.entity;

import com.sparta.codeplanet.global.enums.ErrorType;
import com.sparta.codeplanet.global.enums.Status;
import com.sparta.codeplanet.global.enums.UserRole;
import com.sparta.codeplanet.global.exception.CustomException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Table(name="User")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @OneToOne
    @JoinColumn(name = "companyId", nullable = false)
    private Company company;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column
    private String intro;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "fromUser", fetch = FetchType.LAZY)
    private List<Follow> followingList;

    @OneToMany(mappedBy = "toUser", fetch = FetchType.LAZY)
    private List<Follow> followerList;

    /**
     * 회원 상태 검증 (이메일 인증)
     */
    public void verifyStatusWhenEmailAuth() {
        // 이미 승인된 회원
        if (Status.ACTIVE.equals(this.status)) {
            throw new CustomException(ErrorType.APPROVED_USER);
        }
        // 탈퇴한 회원
        if (Status.DEACTIVATE.equals(this.status)) {
            throw new CustomException(ErrorType.DEACTIVATED_USER);
        }
    }

    /**
     * 회원 상태 검증 (팔로우)
     */
    public void verifyStatusWhenFollow() {
        // 승인되지 않은 회원
        if (Status.BEFORE_APPROVE.equals(this.status)) {
            throw new CustomException(ErrorType.UNAPPROVED_USER);
        }
        // 탈퇴한 회원
        if (Status.DEACTIVATE.equals(this.status)) {
            throw new CustomException(ErrorType.DEACTIVATED_USER);
        }
    }

    /**
     * 회원 상태 변경 (ACTIVE)
     */
    public void active() {
        this.status = Status.ACTIVE;
    }

    /**
     * 회원 상태 변경 (DEACTIVATE)
     */
    public void deactivate() {
        this.status = Status.DEACTIVATE;
    }
}
