package com.epam.service;

import com.epam.domain.Post;
import com.epam.domain.PostCreateForm;
import com.epam.domain.PostUpdateForm;

import java.util.List;

public interface PostService {

    Post get(Long id);

    List<Post> getAll();

    void delete(Long id);

    Post update(Long id, PostUpdateForm postUpdateForm);

    Post create(PostCreateForm postCreateForm);

}
