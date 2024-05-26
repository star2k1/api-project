package com.brainrot.brainrotvideoapi.services;

import com.brainrot.brainrotvideoapi.models.dto.Video;
import com.brainrot.brainrotvideoapi.repositories.VideosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VideosService {

    private final VideosRepository repository;

    private static final String baseUrl = "http://localhost:8080/";

    public String getTestVideo() {
        return "This is test video";
    }

    public String addNew(String videoUrl) {
        repository.addVideoUrl(videoUrl);
        return "New video added successfully";
    }

    public String playAll() {
        List<Video> allVideos = repository.getAllVideos();
        StringBuilder videoIds = new StringBuilder();
        for (Video video : allVideos) {
            if (!videoIds.isEmpty()) {
                videoIds.append(",");
            }
            videoIds.append(video.getId());
        }
        String fullUrl = baseUrl + "?ids=" + videoIds;
        playVideo(fullUrl);
        return "Playing all videos...";
    }

    public String playRandom() {
        Video vid = repository.getRandomVideo();
        String fullUrl = baseUrl + "?ids=" + vid.getId();
        playVideo(fullUrl);
        return "Playing random video from " + vid + "...";
    }

    public void playVideo(String fullUrl) {
        String os = System.getProperty("os.name").toLowerCase();
        Runtime runtime = Runtime.getRuntime();

        try {
            if (os.contains("win")) {
                runtime.exec(new String[]{"rundll32", "url.dll,FileProtocolHandler", fullUrl});
            } else if (os.contains("mac")) {
                runtime.exec(new String[]{"open", fullUrl});
            } else if (os.contains("nix") || os.contains("nux")) {
                runtime.exec(new String[]{"xdg-open", fullUrl});
            } else {
                System.err.println("Unsupported operating system: " + os);
            }
            System.out.println("HTML page opened successfully.");
        } catch (IOException e) {
            System.err.println("Error opening HTML page: " + e.getMessage());
        }
    }


}
