package com.brainrot.brainrotvideoapi.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Video {

    private String id;
    private String url;

    @Override
    public String toString() {
        return String.format("%s", url);
    }
}
