package com.mezon.bot.repository;

import com.mezon.bot.entity.News;
import com.mezon.bot.entity.PageUrl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    List<News> findByPageUrl(PageUrl pageUrl);

    List<News> findByPublishDateAfter(LocalDateTime date);

    List<News> findByTitleContaining(String keyword);

    boolean existsByUrl(String url);

    @Query("SELECT n.url FROM News n WHERE LOWER(n.pageUrl.url) = LOWER(:pageUrl)")
    Set<String> findUrlsByPageUrl(@Param("pageUrl") String pageUrl);

    @Query("SELECT n FROM News n " +
            "JOIN n.pageUrl p " +
            "JOIN p.newsPageCategory npc " +
            "JOIN npc.newsPage np " +
            "WHERE LOWER(np.name) = LOWER(:newsPageName) " +
            "AND LOWER(npc.category.name) = LOWER(:category)")
    Page<News> findByNewsPageNameAndCategory(
            @Param("newsPageName") String newsPageName,
            @Param("category") String category,
            Pageable pageable);

    @Modifying
    @Transactional
    @Query("DELETE FROM News n WHERE n.publishDate < :threshold")
    void deleteNewsOlderThan(@Param("threshold") LocalDateTime threshold);
}
