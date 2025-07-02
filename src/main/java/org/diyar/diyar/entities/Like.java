package org.diyar.diyar.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    private LocalDateTime likedAt = LocalDateTime.now();

    public Like() {}

    public Like(User user, Post post, LocalDateTime likedAt) {
        this.user = user;
        this.post = post;
        this.likedAt = likedAt;
    }

    public void setId(Long id) { this.id = id; }
    public void setUser(User user) { this.user = user; }
    public void setPost(Post post) { this.post = post; }
    public void setLikedAt(LocalDateTime likedAt) { this.likedAt = likedAt; }

    public Long getId() { return id; }
    public User getUser() { return user; }
    public Post getPost() { return post; }
    public LocalDateTime getLikedAt() { return likedAt; }
}
