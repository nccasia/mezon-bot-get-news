package com.mezon.bot.cronjob;

import com.mezon.bot.dto.NewsResponse;
import com.mezon.bot.entity.News;
import com.mezon.bot.repository.NewsRepository;
import com.mezon.bot.service.PageUrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class ScheduleNews {

    @Value("${python.api.url}")
    private String pythonUrl;

    private final RestTemplate restTemplate;

    private final NewsRepository newsRepository;

    private final PageUrlService pageUrlService;

    @Scheduled(cron = "0 */5 * * * *")
    @Async("threadPoolTaskScheduler")
    public void getNewsFromBaoMoi() {
        log.info("getNewsFromBaoMoi(): GET NEWS");
        List<String> urls = pageUrlService.getPageUrlsByNewsPageName("Báo mới");
        urls.forEach(this::fetchNewsFromUrlAndSave);
    }

    private void fetchNewsFromUrlAndSave(String url) {
        String apiUrl = new StringBuilder(pythonUrl)
                .append("/api/news?url=")
                .append(url)
                .toString();
        Set<String> existingUrls = newsRepository.findUrlsByPageUrl(url);
        try {
            NewsResponse[] newsArrayResponse = restTemplate.getForObject(apiUrl, NewsResponse[].class);

            if (newsArrayResponse != null && newsArrayResponse.length > 0) {
                List<News> newNewsList = Arrays.stream(newsArrayResponse)
                        .filter(newsData ->
                                newsData.getTitle() != null &&
                                        newsData.getUrl() != null &&
                                        newsData.getPublishDate() != null &&
                                        !existingUrls.contains(new StringBuilder("https://baomoi.com").append(newsData.getUrl()).toString())
                        )
                        .map(newsData -> {
                            String urlResult = (newsData.getUrl() == null || newsData.getUrl().isEmpty())
                                    ? "https://baomoi.com"
                                    : new StringBuilder("https://baomoi.com").append(newsData.getUrl()).toString();

                            return News.builder()
                                    .title(newsData.getTitle())
                                    .source(newsData.getSource() == null || newsData.getSource().isEmpty() ? "Báo mới" : newsData.getSource())
                                    .publishDate(convertToDateTime(newsData.getPublishDate()))
                                    .url(urlResult)
                                    .pageUrl(pageUrlService.findByUrl(url))
                                    .build();
                        })
                        .collect(Collectors.toList());

                if (!newNewsList.isEmpty()) {
                    newsRepository.saveAll(newNewsList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Scheduled(cron = "0 0 */4 * * *")
    public void deleteOldNews() {
        log.info("deleteOldNews()");
        LocalDateTime thresholdTime = LocalDateTime.now().minusHours(4);
        newsRepository.deleteNewsOlderThan(thresholdTime);
    }

    private LocalDateTime convertToDateTime(LocalDateTime date) {
        if (date == null) {
            return null;
        }
        String dateString = date.toString();
        String publishDateStr = dateString.replace('T', ' ');
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm[:ss]");

        return LocalDateTime.parse(publishDateStr, formatter);
    }


}
