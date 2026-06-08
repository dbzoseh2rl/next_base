package com.example.nextbase.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Board {
    private Long id;
    private String title;
    private String content;
    private String author;

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
