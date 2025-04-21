package com.mezon.bot.repository;

import com.mezon.bot.entity.Category;
import com.mezon.bot.entity.NewsPage;
import com.mezon.bot.entity.NewsPageCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsPageCategoryRepository extends JpaRepository<NewsPageCategory, Long> {

    // Tìm tất cả NewsPageCategory theo NewsPage
    List<NewsPageCategory> findByNewsPage(NewsPage newsPage);

    // Tìm tất cả NewsPageCategory theo Category
    List<NewsPageCategory> findByCategory(Category category);

    @Query("SELECT c.name FROM NewsPageCategory npc JOIN npc.category c JOIN npc.newsPage np WHERE LOWER(np.name) = LOWER(:newsPageName)")
    List<String> findCategoryNamesByNewsPageName(@Param("newsPageName") String newsPageName);
}
