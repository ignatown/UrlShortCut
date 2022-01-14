package ru.job4j.url.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.job4j.url.model.Site;
import ru.job4j.url.model.URL;
import ru.job4j.url.service.SiteService;
import ru.job4j.url.service.URLService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private final SiteService siteService;
    private final URLService urlService;

    public RestController(SiteService siteService, URLService urlService) {
        this.siteService = siteService;
        this.urlService = urlService;
    }

    @PostMapping("/registration")
    public ResponseEntity<Site> createSite(@RequestBody Site site) {
        return new ResponseEntity<Site>(
                siteService.saveSite(site),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/convert")
    public ResponseEntity<URL> createURL(@RequestBody URL url) {
        return new ResponseEntity<URL>(
                urlService.saveURL(url),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/r/{shortLink}")
    public ResponseEntity<Void> getShortLink(@PathVariable String shortLink) {
        String url = urlService.getURLByShortLink(shortLink);
        if (url != null) {
            HttpHeaders header = new HttpHeaders();
            header.add("url", url);
            return new ResponseEntity<Void>(
                    header,
                    HttpStatus.MOVED_PERMANENTLY);
        }
        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND
        );
    }

    @GetMapping("/statistic")
    public List<URL> getStatistic(Principal principal) {
        return new ArrayList<>(urlService.getStatistic());
    }
}
