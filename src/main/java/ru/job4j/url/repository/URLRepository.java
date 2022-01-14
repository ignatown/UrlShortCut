package ru.job4j.url.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.url.model.URL;

import javax.transaction.Transactional;
import java.util.Optional;

public interface URLRepository extends CrudRepository<URL, Integer> {
    Optional<URL> findByLink(String link);
    Optional<URL> findByShortLink(String shortLink);
    @Transactional
    @Modifying
    @Query("UPDATE URL u SET u.count = u.count + 1 WHERE u.id = :id")
    void incrementURL(Integer id);

}