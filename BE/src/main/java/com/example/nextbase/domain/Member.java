package com.example.nextbase.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Member {
    private Long id;
    private String name;
    private String email;

    public void update(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
