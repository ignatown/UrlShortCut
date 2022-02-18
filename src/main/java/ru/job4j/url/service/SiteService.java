package ru.job4j.url.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.url.model.Site;
import ru.job4j.url.repository.SiteRepository;

@Service
public class SiteService {
    private final SiteRepository siteRepository;
    private final BCryptPasswordEncoder encoder;
    private final StringGenerator generator;
    private static final Log log = LogFactory.getLog(
            SiteService.class);

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
        log.info("IN saveSite save new site with name: " + site.getName()
        + "and login: " + site.getLogin());
        return site;
    }
}
