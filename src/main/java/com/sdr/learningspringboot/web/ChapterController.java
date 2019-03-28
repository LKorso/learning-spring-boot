package com.sdr.learningspringboot.web;

import com.sdr.learningspringboot.domain.Chapter;
import com.sdr.learningspringboot.repository.ChapterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class ChapterController {

    private final ChapterRepository chapterRepository;

    @GetMapping("/chapters")
    public Flux<Chapter> listing() {
        return chapterRepository.findAll();
    }
}
