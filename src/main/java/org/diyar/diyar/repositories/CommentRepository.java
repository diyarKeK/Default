package org.diyar.diyar.repositories;

import org.diyar.diyar.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostIdOrderByCommentedAtDesc(Long postId);
}
