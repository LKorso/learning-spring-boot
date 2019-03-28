package com.sdr.learningspringboot.configuration;

import com.sdr.learningspringboot.domain.Chapter;
import com.sdr.learningspringboot.repository.ChapterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner init(ChapterRepository repository) {
        return args -> Flux.just(
                new Chapter("Quick start with Java"),
                new Chapter("Reactive Web with Soring Boot"),
                new Chapter("... and more!")
        )
                .flatMap(repository::save)
                .subscribe(System.out::println);
    }
}
