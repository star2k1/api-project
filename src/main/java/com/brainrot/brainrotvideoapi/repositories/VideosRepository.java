package com.brainrot.brainrotvideoapi.repositories;

import org.springframework.stereotype.Repository;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
public class VideosRepository {

    private static final Random r = new Random();

    public String getRandomVideoId() {
        return extractVideoId(VIDEO_URLS.get(r.nextInt(0, VIDEO_URLS.size())));
    }

    public void addVideo(String videoUrl) {
        VIDEO_URLS.add(URLDecoder.decode(videoUrl, StandardCharsets.UTF_8));
    }

    public List<String> getAllVideos() {
        List<String> videoIds = new ArrayList<>();
        for (String videoId : VIDEO_URLS) {
            videoIds.add(extractVideoId(videoId));
        }
        return videoIds;
    }

    private static final List<String> VIDEO_URLS = new ArrayList<>(List.of(
            "https://www.youtube.com/watch?v=yiQg-wC080Q",
            "https://www.youtube.com/watch?v=As9XMZoelDA",
            "https://www.youtube.com/watch?v=ZAfXWEBeadw",
            "https://www.youtube.com/watch?v=YxOGgRy6Ylw",
            "https://www.youtube.com/watch?v=dHy-qfkO54E",
            "https://www.youtube.com/watch?v=m0xqjaqmPyc",
            "https://www.youtube.com/watch?v=u0xVmniADjc",
            "https://www.youtube.com/watch?v=j64_m4I5je8",
            "https://www.youtube.com/watch?v=NZzXzymUgEg",
            "https://www.youtube.com/watch?v=iKYGL2yXpnU",
            "https://www.youtube.com/watch?v=yjfwl4Y4Hqo",
            "https://www.youtube.com/watch?v=CBEvfZu4HE4",
            "https://www.youtube.com/watch?v=rzLIUgnKY40",
            "https://www.youtube.com/watch?v=fxsvI0XXYoM"
    ));

    public static String extractVideoId(String youtubeUrl) {
        String videoId = null;
        String pattern = "(?<=watch\\?v=)[^#&?\\n]*";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(youtubeUrl);

        if (matcher.find()) {
            videoId = matcher.group();
        }
        return videoId;
    }
}
