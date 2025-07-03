package org.diyar.diyar.controllers;

import jakarta.transaction.Transactional;
import org.diyar.diyar.entities.Post;
import org.diyar.diyar.services.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public String posts(Model model) {
        return "posts";
    }

    @Transactional
    @GetMapping("/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        Optional<Post> optionalPost = postService.findById(id);

        if (optionalPost.isEmpty()) {
            return "not_found-404";
        }

        model.addAttribute("post", optionalPost.get());
        return "post";
    }
}
