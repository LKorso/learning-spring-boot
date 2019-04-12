package com.sdr.learningspringboot.service;

import com.sdr.learningspringboot.domain.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class ImageService {

    @Value("${image.upload.root}")
    private String uploadRoot;

    private final ResourceLoader resourceLoader;


    public Flux<Image> findAllImages() {
        try {
            return Flux.fromIterable(Files.newDirectoryStream(Paths.get(uploadRoot)))
                    .map(path -> new Image(path.hashCode(), path.getFileName().toString()));
        } catch (IOException e) {
            return Flux.empty();
        }
    }

    public Mono<Resource> findOneImage(String fileName) {
        return Mono.fromSupplier(
                () -> resourceLoader.getResource(String.format("file:%s/%s", uploadRoot, fileName))
        );
    }

    public Mono<Void> createImages(Flux<FilePart> files) {
        return files
                .flatMap(file -> file.transferTo(Paths.get(uploadRoot, file.filename()).toFile()))
                .then();
    }

    public Mono<Void> deleteImage(String fileName) {
        return Mono.fromRunnable(
                () -> {
                    try {
                        Files.deleteIfExists(Paths.get(uploadRoot, fileName));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

}
