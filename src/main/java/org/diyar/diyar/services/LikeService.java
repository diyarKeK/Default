package org.diyar.diyar.services;

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

    public void likePost(Like like) {
        likeRepository.save(like);
    }

    public boolean isLikeExistsByUserAndPost(User user, Post post) {
        return likeRepository.existsByUserAndPost(user, post);
    }
}
