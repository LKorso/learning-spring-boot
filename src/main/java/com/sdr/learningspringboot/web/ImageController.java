package com.sdr.learningspringboot.web;

import com.sdr.learningspringboot.domain.Image;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class ImageController {

    @GetMapping("/images")
    public Flux<Image> images() {
        return Flux.just(
                new Image(1, "picture-1.jpg"),
                new Image(2, "picture-2.jpg"),
                new Image(3, "picture-3.jpg")
        );
    }

    @PostMapping("/images")
    public Mono<Void> create(@RequestBody Flux<Image> images) {
        return images.map(
                image -> {
                    log.info("We will save " + image + " soon!");
                    return image;
                }
        ).then();
    }
}
