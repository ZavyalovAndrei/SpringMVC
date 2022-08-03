package com.zavialov.controller;

import com.zavialov.model.Post;
import com.zavialov.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
  protected final PostService service;

  public PostController(PostService service) {
    this.service = service;
  }

  @GetMapping
  public List<Post> all() {
    return service.all();
  }

  @GetMapping("/{id}")
  public Post getById(@PathVariable long id) {
    return service.getById(id);
  }

  @PostMapping
  public Post save(@RequestBody Post post) {
    return service.save(post);
  }

  @DeleteMapping("/{id}")
  public String removeById(@PathVariable long id) {
    return service.removeById(id);
  }

  //    Shows all removed posts.
//  @GetMapping("/removed")
//  public List<Post> allRemoved() {
//    return service.allRemoved();
//  }
}