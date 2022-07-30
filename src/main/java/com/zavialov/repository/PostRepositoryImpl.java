package com.zavialov.repository;

import com.zavialov.exception.NotFoundException;
import com.zavialov.model.Post;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository

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
            post.setId(postCount);
            repository.add(post);
            result = post;
        } else if ((getById(post.getId()).isEmpty())) {
            throw new NotFoundException();
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

    public String removeById(long id) {
        if (getById(id).isEmpty()) {
            throw new NotFoundException();
        } else {
            repository.removeIf(value -> value.getId() == id);
            return "Post #" + id + " was successfully deleted.";
        }
    }
}