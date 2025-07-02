package org.diyar.diyar.repositories;

import org.diyar.diyar.entities.Post;
import org.diyar.diyar.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p ORDER BY p.views DESC LIMIT 10")
    List<Post> findTop10ByViews();

    @Query("SELECT p FROM Post p ORDER BY p.views DESC")
    Page<Post> findAllByPopularity(Pageable pageable);

    Optional<Post> findByAuthor(User author);
}
