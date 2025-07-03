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
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(name = "uploaded_at", nullable = false)
    private LocalDateTime likedAt = LocalDateTime.now();

    public Like() {}

    public Like(User author, Post post, LocalDateTime likedAt) {
        this.author = author;
        this.post = post;
        this.likedAt = likedAt;
    }

    public void setId(Long id) { this.id = id; }
    public void setAuthor(User author) { this.author = author; }
    public void setPost(Post post) { this.post = post; }
    public void setLikedAt(LocalDateTime likedAt) { this.likedAt = likedAt; }

    public Long getId() { return id; }
    public User getAuthor() { return author; }
    public Post getPost() { return post; }
    public LocalDateTime getLikedAt() { return likedAt; }
}
