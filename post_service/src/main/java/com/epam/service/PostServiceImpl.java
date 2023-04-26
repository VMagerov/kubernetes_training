package com.epam.service;

import com.epam.domain.Post;
import com.epam.domain.PostCreateForm;
import com.epam.domain.PostUpdateForm;
import com.epam.domain.User;
import com.epam.exceptions.BadRequestException;
import com.epam.exceptions.NotFoundException;
import com.epam.properties.UserServiceProperties;
import com.epam.repo.PostRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private final PostRepository postRepository;

    @Autowired
    private final RestTemplate restTemplate;

    @Autowired
    private final UserServiceProperties userServiceProperties;

    public PostServiceImpl(PostRepository postRepository, RestTemplate restTemplate, UserServiceProperties userServiceProperties) {
        this.postRepository = postRepository;
        this.restTemplate = restTemplate;
        this.userServiceProperties = userServiceProperties;
    }

    @Override
    public Post get(Long id) {

        Optional<Post> optional = postRepository.findById(id);
        return optional.orElseThrow(() -> new NotFoundException());
    }

    @Override
    public List<Post> getAll() {

        return postRepository.findAll();
    }

    @Override
    public void delete(Long id) {

        Post post = get(id);
        Long authorId = post.getAuthorId();

        User userToUpdate = restTemplate.getForObject("http://" + userServiceProperties.getHostname() + ":" + userServiceProperties.getPort() + "/users/" + authorId, User.class);
        userToUpdate.setAmountOfPosts(userToUpdate.getAmountOfPosts() - 1);

        HttpEntity<User> entity = new HttpEntity<>(userToUpdate);
        restTemplate.exchange("http://" + userServiceProperties.getHostname() + ":" + userServiceProperties.getPort() + "/users/" + authorId, HttpMethod.PUT, entity, User.class);

        postRepository.delete(post);
    }

    @Override
    public Post update(Long id, PostUpdateForm postUpdateForm){

        validate(postUpdateForm);
        Post post = get(id);
        post.setText(postUpdateForm.getText());
        post.setPostedAt(LocalDate.now());
        return postRepository.save(post);
    }

    @Override
    public Post create(PostCreateForm postCreateForm) {

        validate(postCreateForm);
        Long authorId = postCreateForm.getAuthorId();
        String text = postCreateForm.getText();
        Post post = new Post();
        post.setAuthorId(authorId);
        post.setText(text);
        post.setPostedAt(LocalDate.now());

        User userToUpdate = restTemplate.getForObject("http://" + userServiceProperties.getHostname() + ":" + userServiceProperties.getPort() + "/users/" + authorId, User.class);
        userToUpdate.setAmountOfPosts(userToUpdate.getAmountOfPosts() + 1);

        HttpEntity<User> entity = new HttpEntity<>(userToUpdate);
        restTemplate.exchange("http://" + userServiceProperties.getHostname() + ":" + userServiceProperties.getPort() + "/users/" + authorId, HttpMethod.PUT, entity, User.class);
        return postRepository.save(post);
    }

    private void validate(PostUpdateForm postUpdateForm) {

        String text = postUpdateForm.getText();

        if (StringUtils.isBlank(text)) {
            throw new BadRequestException();
        }
    }

    private void validate(PostCreateForm postCreateForm) {

        String text = postCreateForm.getText();

        if (StringUtils.isBlank(text)) {
            throw new BadRequestException();
        }

        Long authorId = postCreateForm.getAuthorId();

        if ((authorId == null) || (authorId < 1)) {
            throw new BadRequestException();
        }
    }
}
