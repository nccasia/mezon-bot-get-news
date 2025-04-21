package com.mezon.bot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String source;
    private LocalDateTime publishDate;
    private String url;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "page_url_id")
    @JsonIgnore
    private PageUrl pageUrl;

}
