package com.sdr.learningspringboot.repository;

import com.sdr.learningspringboot.domain.Chapter;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ChapterRepository extends ReactiveCrudRepository<Chapter, String> {
}
