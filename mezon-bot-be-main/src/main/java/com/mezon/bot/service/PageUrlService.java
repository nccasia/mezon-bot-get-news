package com.mezon.bot.service;

import com.mezon.bot.entity.PageUrl;
import com.mezon.bot.repository.PageUrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PageUrlService {

    private final PageUrlRepository pageUrlRepository;

    public List<String> getPageUrlsByNewsPageName(String newsPageName) {
        return pageUrlRepository.findAllByNewsPageName(newsPageName);
    }

    public PageUrl findByUrl(String url) {
        return pageUrlRepository.findByUrl(url);
    }
}
