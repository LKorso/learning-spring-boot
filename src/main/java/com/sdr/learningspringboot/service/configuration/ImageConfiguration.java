package com.sdr.learningspringboot.service.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

@Configuration
public class ImageConfiguration {

    @Value("${image.upload.root}")
    private String uploadRoot;

    @Bean
    CommandLineRunner setUp() {
        return (args) -> {
            FileSystemUtils.deleteRecursively(new File(uploadRoot));
            Files.createDirectory(Paths.get(uploadRoot));
            FileCopyUtils.copy("Test file1", new FileWriter(uploadRoot + "/learning-spring-boot-cover1.jpg"));
            FileCopyUtils.copy("Test file2", new FileWriter(uploadRoot + "/learning-spring-boot-cover2.jpg"));
            FileCopyUtils.copy("Test file3", new FileWriter(uploadRoot + "/learning-spring-boot-cover3.jpg"));
        };
    }

}
