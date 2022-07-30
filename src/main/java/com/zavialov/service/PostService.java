package com.zavialov.service;

import com.zavialov.exception.NotFoundException;
import com.zavialov.model.Post;
import com.zavialov.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository repository;


    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<Post> all() {
        return repository.all();
    }

    public Post getById(long id) {
        return repository.getById(id).orElseThrow(NotFoundException::new);
    }

    public Post save(Post post) throws NotFoundException {
            return repository.save(post);
    }

    public String removeById(long id) {
        return repository.removeById(id);
    }
}