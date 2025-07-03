package org.diyar.diyar.entities;

import jakarta.persistence.*;
import org.springframework.web.filter.OncePerRequestFilter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 256)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT", length = 1024, nullable = false)
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @Column(name = "views", nullable = false)
    private int views = 0;

    @Column(name = "uploaded_at", nullable = false, updatable = false)
    private LocalDateTime uploaded_at = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    public Post() {}

    public Post(User author, LocalDateTime uploaded_at, int views, List<Like> likes, List<Comment> comments, String title, String content) {
        this.author = author;
        this.uploaded_at = uploaded_at;
        this.views = views;
        this.likes = likes;
        this.comments = comments;
        this.title = title;
        this.content = content;
    }

    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setContent(String content) { this.content = content; }
    public void setLikes(List<Like> likes) { this.likes = likes; }
    public void setComments(List<Comment> comments) { this.comments = comments; }
    public void setViews(int views) { this.views = views; }
    public void setUploaded_at(LocalDateTime uploaded_at) { this.uploaded_at = uploaded_at; }
    public void setAuthor(User author) { this.author = author; }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public List<Like> getLikes() { return likes; }
    public List<Comment> getComments() { return comments; }
    public int getViews() { return views; }
    public LocalDateTime getUploaded_at() { return uploaded_at; }
    public User getAuthor() { return author; }
}
