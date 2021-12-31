package com.frknpg.RndWeb.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.concurrent.Executors;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class WebController {

    @GetMapping(value = "/random-number", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter getRandomNumber() {
        var emitter = new SseEmitter();
        var sseMvcExecutor = Executors.newSingleThreadExecutor();
        sseMvcExecutor.execute(() -> {
            try {
                for (int i = 0; true; i++) {
                    SseEmitter.SseEventBuilder event = SseEmitter.event()
                      .data(getRandomNumber(1000, 9999))
                      .id(String.valueOf(i))
                      .name("message");
                    emitter.send(event);
                    Thread.sleep(1000);
                }
            } catch (Exception ex) {
                emitter.completeWithError(ex);
            }
        });
        return emitter;
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}
