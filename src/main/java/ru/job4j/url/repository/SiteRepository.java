package ru.job4j.url.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.url.model.Site;

import java.util.Optional;

public interface SiteRepository extends CrudRepository<Site, Integer> {
    Optional<Site> findSiteByLogin(String login);
    Optional<Site> findSiteByName(String name);
}