package org.diyar.diyar.repositories;

import org.diyar.diyar.entities.Like;
import org.diyar.diyar.entities.Post;
import org.diyar.diyar.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByUserAndPost(User user, Post post);
}
