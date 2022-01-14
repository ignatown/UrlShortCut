package ru.job4j.url.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.url.model.Site;
import ru.job4j.url.repository.SiteRepository;

@Service
public class SiteService {
    private final SiteRepository siteRepository;
    private final BCryptPasswordEncoder encoder;
    private final StringGenerator generator;

    public SiteService(SiteRepository siteRepository, BCryptPasswordEncoder encoder, StringGenerator generator) {
        this.siteRepository = siteRepository;
        this.encoder = encoder;
        this.generator = generator;
    }

    public Site saveSite(Site site) {
        if (siteRepository.findSiteByName(site.getName()).isEmpty()) {
            site.setLogin(generator.newLogin());
            String password = generator.newPassword();
            site.setPassword(encoder.encode(password));
            siteRepository.save(site);
            site.setPassword(password);
        }
        return site;
    }
}
