package org.diyar.diyar.services.post_services;

import org.diyar.diyar.entities.Comment;
import org.diyar.diyar.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment commentPost(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsForPost(Long postId) {
        return commentRepository.findByPostIdOrderByCommentedAtDesc(postId);
    }
}
