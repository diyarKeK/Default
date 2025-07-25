package org.diyar.diyar.repositories;

import org.diyar.diyar.entities.Post;
import org.diyar.diyar.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findByAuthor(User author);
}
