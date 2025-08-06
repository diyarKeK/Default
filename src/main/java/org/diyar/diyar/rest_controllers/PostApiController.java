package org.diyar.diyar.rest_controllers;

import org.diyar.diyar.entities.Comment;
import org.diyar.diyar.entities.Like;
import org.diyar.diyar.entities.Post;
import org.diyar.diyar.entities.User;
import org.diyar.diyar.services.post_services.CommentService;
import org.diyar.diyar.services.post_services.LikeService;
import org.diyar.diyar.services.post_services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/post")
public class PostApiController {

    private final PostService postService;
    private final LikeService likeService;
    private final CommentService commentService;

    public PostApiController(PostService postService, LikeService likeService, CommentService commentService) {
        this.postService = postService;
        this.likeService = likeService;
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        post.setAuthor(user);
        return ResponseEntity.ok(postService.createPost(post));
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<?> likePost(@PathVariable Long id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Optional<Post> optionalPost = postService.findById(id);

        if (optionalPost.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404 - Post is not found");
        }

        Post post = optionalPost.get();

        if (likeService.isLikeExistsByAuthorAndPost(user, post)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Already Liked By You");
        }

        Like like = new Like();
        like.setAuthor(user);
        like.setPost(post);

        return ResponseEntity.ok(likeService.likePost(like));
    }

    @PostMapping("/{id}/comment")
    public ResponseEntity<?> commentPost(@PathVariable Long id, @RequestBody Comment comment, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Optional<Post> optionalPost = postService.findById(id);

        if (optionalPost.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404 - Post is not found");
        }

        Post post = optionalPost.get();

        comment.setAuthor(user);
        comment.setPost(post);

        return ResponseEntity.ok(commentService.commentPost(comment));
    }
}

