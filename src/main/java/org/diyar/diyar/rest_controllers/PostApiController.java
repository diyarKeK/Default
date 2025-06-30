package org.diyar.diyar.rest_controllers;

import org.diyar.diyar.entities.Post;
import org.diyar.diyar.entities.User;
import org.diyar.diyar.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostApiController {

    private final PostService postService;

    public PostApiController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<Post>> getPopularPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(postService.getPopularPosts(page, size));
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        post.setAuthor(user);
        return ResponseEntity.ok(postService.createPost(post));
    }
}

