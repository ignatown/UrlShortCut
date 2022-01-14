package ru.job4j.url.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class StringGenerator {
    public String newShortLink() {
        return RandomStringUtils.randomAlphanumeric(7);
    }

    public String newLogin() {
        return RandomStringUtils.randomAlphabetic(3) + RandomStringUtils.randomAlphanumeric(3);
    }

    public String newPassword() {
        return RandomStringUtils.randomNumeric(10);
    }
}
