package com.brainrot.brainrotvideoapi.controllers;

import com.brainrot.brainrotvideoapi.services.VideosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/videos")
public class VideosController {

    private final VideosService videosService;

    @GetMapping("test")
    public ResponseEntity<String> getSingleVideo() {
        // Return a view or redirect as needed
        return ResponseEntity.ok(videosService.getTestVideo());
    }

    @GetMapping("random")
    public ResponseEntity<String> playRandomVideo() {
        return ResponseEntity.ok(videosService.playRandom());
    }

    @GetMapping("all")
    public ResponseEntity<String> playAllVideos() {
        return ResponseEntity.ok(videosService.playAll());
    }

    @PostMapping("add")
    public ResponseEntity<String> addVideo(@RequestBody String videoUrl) {
        System.out.println(videoUrl);
        return ResponseEntity.ok(videosService.addNew(videoUrl));
    }

}
