package com.zavialov.repository;

import com.zavialov.exception.NotFoundException;
import com.zavialov.model.Post;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository

public class PostRepositoryImpl implements PostRepository {
    List<Post> repository = new CopyOnWriteArrayList<>();
    List<Post> removedDataRepository = new CopyOnWriteArrayList<>();
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
            post.setId(postCount);
            repository.add(post);
            result = post;
        } else {
            for (Post value : repository) {
                if (value.getId() == post.getId()) {
                    value.setContent(post.getContent());
                    result = value;
                }
            }
        }
        if (result == null) {
            throw new NotFoundException();
        }
        return result;
    }

    public String removeById(long id) {
        if (getById(id).isEmpty()) {
            throw new NotFoundException();
        } else {
            Post removedData = null;
            for (Post data : repository) {
                if (data.getId() == id)
                    removedData = data;
            }
            removedDataRepository.add(removedData);
            repository.removeIf(value -> value.getId() == id);
            return "Post #" + id + " was successfully deleted.";
        }
    }

    public List<Post> allRemoved() {
        return removedDataRepository;
    }
}