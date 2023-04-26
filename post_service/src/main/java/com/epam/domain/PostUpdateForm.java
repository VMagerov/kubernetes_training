package com.epam.domain;

public class PostUpdateForm {

    private String text;

    public PostUpdateForm() {
    }

    public PostUpdateForm(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
