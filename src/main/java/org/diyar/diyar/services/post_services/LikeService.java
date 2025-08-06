package org.diyar.diyar.services.post_services;

import org.diyar.diyar.entities.Like;
import org.diyar.diyar.entities.Post;
import org.diyar.diyar.entities.User;
import org.diyar.diyar.repositories.LikeRepository;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    private final LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public Like likePost(Like like) {
        return likeRepository.save(like);
    }

    public boolean isLikeExistsByAuthorAndPost(User author, Post post) {
        return likeRepository.existsByAuthorAndPost(author, post);
    }
}
