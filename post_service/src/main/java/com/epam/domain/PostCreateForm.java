package com.epam.domain;

public class PostCreateForm {

    private Long authorId;

    private String text;

    public PostCreateForm() {
    }

    public PostCreateForm(Long authorId, String text) {
        this.authorId = authorId;
        this.text = text;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
