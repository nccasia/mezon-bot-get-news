package com.mezon.bot.repository;

import com.mezon.bot.entity.NewsPageCategory;
import com.mezon.bot.entity.PageUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PageUrlRepository extends JpaRepository<PageUrl, Long> {

    // Tìm PageUrl theo NewsPageCategory
    Optional<PageUrl> findByNewsPageCategory(NewsPageCategory newsPageCategory);

    // Tìm tất cả PageUrl theo url chứa từ khóa
    List<PageUrl> findByUrlContaining(String keyword);

    @Query("SELECT p.url FROM PageUrl p JOIN p.newsPageCategory npc JOIN npc.newsPage np WHERE LOWER(np.name) = LOWER(:newsPageName)")
    List<String> findAllByNewsPageName(String newsPageName);

    PageUrl findByUrl(String url);
}
