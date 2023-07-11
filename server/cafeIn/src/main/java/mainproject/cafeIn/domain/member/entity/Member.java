package mainproject.cafeIn.domain.member.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mainproject.cafeIn.domain.member.entity.enums.MemberGrade;
import mainproject.cafeIn.domain.member.entity.enums.MemberStatus;
import mainproject.cafeIn.domain.post.entity.Post;
import mainproject.cafeIn.global.base.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;


    @Column(length = 100, unique = true, nullable = false)
    private String displayName;

    @Column(name = "MEMBER_EMAIL", length = 100, nullable = false)
    private String email;

    @Column(name = "MEMBER_PASSWORD", length = 100, nullable = false)
    private String password;

    @Column(name = "PROFILE_IMAGE")
    private String image;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "MEMBER_STATUS", length = 30, nullable = false)
    private MemberStatus status;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 30, nullable = false)
    private MemberGrade grade;

    @OneToMany(mappedBy = "followerId", cascade = CascadeType.REMOVE)
    private List<Follow> followers = new ArrayList<>();

    @OneToMany(mappedBy = "followingId", cascade = CascadeType.REMOVE)
    private List<Follow> followings = new ArrayList<>();

    @OneToMany(mappedBy = "postId", cascade = CascadeType.REMOVE)
    private List<Post> posts = new ArrayList<>();


    @Builder
    public Member(String displayName, String email, String password, String image, MemberStatus status, MemberGrade grade) {

        this.displayName = displayName;
        this.email = email;
        this.password = password;
        this.image = image;
        this.status = status;
        this.grade = grade;

    }

    public void updateDisplay(String displayName) {
        this.displayName = displayName;
    }

    public void updatePassword(String password) {

        this.password = password;

    }

    public void updateImage(String image) {

        this.image = image;
    }

    public void deleteStatus(MemberStatus status) {
        this.status = status;
    }
}
