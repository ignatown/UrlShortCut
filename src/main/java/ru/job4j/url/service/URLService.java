package ru.job4j.url.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import ru.job4j.url.model.URL;
import ru.job4j.url.repository.SiteRepository;
import ru.job4j.url.repository.URLRepository;

import java.util.List;

@Service
public class URLService {
    private final StringGenerator generator;
    private final URLRepository urlRepository;
    private final SiteRepository siteRepository;
    private static final Log log = LogFactory.getLog(
            URLService.class);

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
        log.info("in saveURL save new URL with get short id: " + url.getShortLink());
        return url;
    }

    public String getURLByShortLink(String shortLink) {
        URL temp = urlRepository.findByShortLink(shortLink).orElse(new URL());
        if (temp.getId() != 0) {
            urlRepository.incrementURL(temp.getId());
        }
        log.info("in getURLByShortLink get URL by short: " + shortLink
                + " :: " + temp.getLink());
        return temp.getLink();
    }

    public List<URL> getStatistic() {
        return (List<URL>) urlRepository.findAll();
    }
}
