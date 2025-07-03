package org.diyar.diyar.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 512)
    private String content;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User user;

    @Column(name = "uploaded_at", nullable = false)
    private LocalDateTime commentedAt = LocalDateTime.now();

    public Comment() {}

    public Comment(String content, Post post, User user) {
        this.content = content;
        this.post = post;
        this.user = user;
    }

    public void setId(Long id) { this.id = id; }
    public void setContent(String content) { this.content = content; }
    public void setPost(Post post) { this.post = post; }
    public void setUser(User user) { this.user = user; }
    public void setCommented_at(LocalDateTime commentedAt) { this.commentedAt = commentedAt; }

    public Long getId() { return id; }
    public String getContent() { return content; }
    public Post getPost() { return post; }
    public User getUser() { return user; }
    public LocalDateTime getCommented_at() { return commentedAt; }
}
