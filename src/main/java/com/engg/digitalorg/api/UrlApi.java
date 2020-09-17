package com.engg.digitalorg.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The interface Url api.
 */
@RequestMapping("/url")
@Api(tags = "Url Services", description = "Url Api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public interface UrlApi {

    /**
     * Gets and redirect.
     *
     * @param shortUrl the short url
     * @return the and redirect
     */
    @ApiOperation(value = "Redirect", notes = "Finds original url from short url and redirects")
    @GetMapping(value = "{short-url}")
    @Cacheable(value = "short-url", key = "#short-url", sync = true)
    ResponseEntity<Void> getAndRedirect(@PathVariable("short-url") String shortUrl);

    /**
     * Send simple message.
     *
     * @param to      the to
     * @param subject the subject
     * @param text    the text
     */
    void sendSimpleMessage(String to, String subject, String text);
}
