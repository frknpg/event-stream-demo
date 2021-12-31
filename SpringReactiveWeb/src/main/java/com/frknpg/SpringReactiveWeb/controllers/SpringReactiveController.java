package com.frknpg.SpringReactiveWeb.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class SpringReactiveController {

    @GetMapping(value = "/random-number", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Integer> getRandomNumber() {
        return  Flux.fromStream(Stream.generate(() -> getRandomNumber(1000, 9999))).delayElements(Duration.ofSeconds(3));
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}
