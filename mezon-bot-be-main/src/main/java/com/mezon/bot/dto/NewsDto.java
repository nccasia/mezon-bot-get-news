package com.mezon.bot.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NewsDto {
    private Long id;
    private String title;
    private String source;
    private LocalDateTime publishDate;
    private String url;
    private String content;

    public NewsDto(Long id, String title, String source, LocalDateTime publishDate, String url, String content) {
        this.id = id;
        this.title = title;
        this.source = source;
        this.publishDate = publishDate;
        this.url = url;
        this.content = content;
    }

}