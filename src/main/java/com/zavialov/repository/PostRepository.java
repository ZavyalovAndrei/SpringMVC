package com.zavialov.repository;
import com.zavialov.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    List<Post> all();

    Optional<Post> getById(long id);

    Post save(Post post);

    String removeById(long id);

    List<Post> allRemoved();
}