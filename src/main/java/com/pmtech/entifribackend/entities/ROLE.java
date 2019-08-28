package com.pmtech.entifribackend.entities;

public enum ROLE {

    ADMIN(null, "ADMIN"),
    STUDENT(null, "STUDENT"),
    TEACHER(null, "TEACHER");


    private final Long id;
    private final String name;

    ROLE(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
