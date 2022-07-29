package com.zavialov.repository;

import com.zavialov.model.Post;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

public class PostRepositoryImpl implements PostRepository {
    List<Post> repository = new CopyOnWriteArrayList<>();
    volatile long postCount = 0;

    public List<Post> all() {
        return repository;
    }

    public Optional<Post> getById(long id) {
        Optional<Post> result = Optional.empty();
        for (Post value : repository) {
            if (value.getId() == id) {
                result = Optional.of(value);
            }
        }
        return result;
    }

    public Post save(Post post) {
        Post result = null;
        if (post.getId() == 0) {
            postCount++;
            repository.add(new Post(postCount, post.getContent()));
            result = post;
        } else {
            for (Post value : repository) {
                if (value.getId() == post.getId()) {
                    value.setContent(post.getContent());
                    result = value;
                }
            }
        }
        return result;
    }

    public void removeById(long id) {
        repository.removeIf(value -> value.getId() == id);
    }
}
