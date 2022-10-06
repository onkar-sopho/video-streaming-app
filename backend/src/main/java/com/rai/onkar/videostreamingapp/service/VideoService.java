package com.rai.onkar.videostreamingapp.service;

import com.rai.onkar.videostreamingapp.dto.UploadVideoResponse;
import com.rai.onkar.videostreamingapp.dto.VideoDto;
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

    public UploadVideoResponse uploadVideo(MultipartFile file) {

        // Upload video to AWS S3 and fetch the video url
        String videoUrl = s3Service.uploadFile(file);

        var video = new Video();
        video.setVideoUrl(videoUrl);

        // Save video metadata to database
        Video savedVideo = videoRepository.save(video);

        return new UploadVideoResponse(savedVideo.getId(), savedVideo.getVideoUrl());
    }

    public VideoDto editVideo(VideoDto videoDto) {
        // find the video by video id
        Video savedVideo = getVideoById(videoDto.getId());

        // map the videoDto fields to video
        savedVideo.setTitle(videoDto.getTitle());
        savedVideo.setDescription(videoDto.getDescription());
        savedVideo.setVideoStatus(videoDto.getVideoStatus());
        savedVideo.setThumbnailUrl(videoDto.getThumbnailUrl());
        savedVideo.setTags(videoDto.getTags());

        // save the video to the database
        videoRepository.save(savedVideo);
        return videoDto;
    }

    public String uploadThumbnail(MultipartFile file, String videoId) {
        Video savedVideo = getVideoById(videoId);
        String thumbnailUrl = s3Service.uploadFile(file);
        savedVideo.setThumbnailUrl(thumbnailUrl);
        videoRepository.save(savedVideo);
        return thumbnailUrl;
    }

    Video getVideoById(String videoId) {
        return videoRepository.findById(videoId)
                .orElseThrow(() -> new IllegalArgumentException("Could not find video by id: " + videoId));
    }
}
