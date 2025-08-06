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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(name = "uploaded_at", nullable = false)
    private LocalDateTime commentedAt = LocalDateTime.now();

    public Comment() {}

    public Comment(String content, Post post, User author) {
        this.content = content;
        this.post = post;
        this.author = author;
    }

    public void setId(Long id) { this.id = id; }
    public void setContent(String content) { this.content = content; }
    public void setPost(Post post) { this.post = post; }
    public void setAuthor(User author) { this.author = author; }
    public void setCommentedAt(LocalDateTime commentedAt) { this.commentedAt = commentedAt; }

    public Long getId() { return id; }
    public String getContent() { return content; }
    public Post getPost() { return post; }
    public User getAuthor() { return author; }
    public LocalDateTime getCommentedAt() { return commentedAt; }
}
