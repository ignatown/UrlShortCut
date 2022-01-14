package ru.job4j.url.service;

import org.springframework.stereotype.Service;
import ru.job4j.url.model.Site;
import ru.job4j.url.model.URL;
import ru.job4j.url.repository.SiteRepository;
import ru.job4j.url.repository.URLRepository;

import java.util.List;

@Service
public class URLService {
    private final StringGenerator generator;
    private final URLRepository urlRepository;
    private final SiteRepository siteRepository;

    public URLService(StringGenerator generator, URLRepository urlRepository, SiteRepository siteRepository) {
        this.generator = generator;
        this.urlRepository = urlRepository;
        this.siteRepository = siteRepository;
    }

    public URL saveURL(URL url) {
        url = urlRepository.findByLink(url.getLink()).orElse(url);
        if (url.getId() == 0) {
            url.setShortLink(generator.newShortLink());
            url.setLink(url.getLink());
            url = urlRepository.save(url);
        }
        return url;
    }

    public String getURLByShortLink(String shortLink) {
        URL temp = urlRepository.findByShortLink(shortLink).orElse(new URL());
        if (temp.getId() != 0) {
            urlRepository.incrementURL(temp.getId());
        }
        return temp.getLink();
    }

    public List<URL> getStatistic() {
        return (List<URL>) urlRepository.findAll();
    }
}
