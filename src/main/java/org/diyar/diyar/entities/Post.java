package org.diyar.diyar.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 256)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "likes", nullable = false)
    private int likes = 0;

    @Column(name = "views", nullable = false)
    private int views = 0;

    @Column(name = "uploaded_at", nullable = false, updatable = false)
    private LocalDateTime uploaded_at = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    public Post() {}

    public Post(User author, LocalDateTime uploaded_at, int views, int likes, String title, String content) {
        this.author = author;
        this.uploaded_at = uploaded_at;
        this.views = views;
        this.likes = likes;
        this.title = title;
        this.content = content;
    }

    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setContent(String content) { this.content = content; }
    public void setLikes(int likes) { this.likes = likes; }
    public void setViews(int views) { this.views = views; }
    public void setUploaded_at(LocalDateTime uploaded_at) { this.uploaded_at = uploaded_at; }
    public void setAuthor(User author) { this.author = author; }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public int getLikes() { return likes; }
    public int getViews() { return views; }
    public LocalDateTime getUploaded_at() { return uploaded_at; }
    public User getAuthor() { return author; }
}
