package com.rai.onkar.videostreamingapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VideoService {
    public void uploadVideo(MultipartFile file) {
        // Upload video to AWS S3
        // Save video metadata to database
    }
}
