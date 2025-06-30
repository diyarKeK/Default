package org.diyar.diyar.services;

import org.diyar.diyar.entities.Post;
import org.diyar.diyar.repositories.PostRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getPopularPosts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("views").descending());
        return postRepository.findAllByPopularity(pageable).getContent();
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }
}
