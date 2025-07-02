package org.diyar.diyar.services;

import org.diyar.diyar.entities.Like;
import org.diyar.diyar.entities.Post;
import org.diyar.diyar.entities.User;
import org.diyar.diyar.repositories.LikeRepository;
import org.diyar.diyar.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }
}
