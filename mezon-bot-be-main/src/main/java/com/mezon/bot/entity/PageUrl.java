package com.mezon.bot.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "news_page_category_id", referencedColumnName = "id")
    private NewsPageCategory newsPageCategory;

    @OneToMany(mappedBy = "pageUrl", fetch = FetchType.LAZY)
    private List<News> newsList;
}
