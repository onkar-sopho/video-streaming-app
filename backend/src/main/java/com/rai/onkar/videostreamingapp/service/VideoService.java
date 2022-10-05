package com.rai.onkar.videostreamingapp.service;

import com.rai.onkar.videostreamingapp.controller.dto.VideoDto;
import com.rai.onkar.videostreamingapp.model.Video;
import com.rai.onkar.videostreamingapp.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final S3Service s3Service;
    private final VideoRepository videoRepository;

    public void uploadVideo(MultipartFile file) {

        // Upload video to AWS S3 and fetch the video url
        String videoUrl = s3Service.uploadFile(file);

        var video = new Video();
        video.setVideoUrl(videoUrl);

        // Save video metadata to database
        videoRepository.save(video);
    }

    public void editVideo(VideoDto videoDto) {
        // find the video by video id

        // map the videoDto fields to video

        // save the video to the database
    }
}
