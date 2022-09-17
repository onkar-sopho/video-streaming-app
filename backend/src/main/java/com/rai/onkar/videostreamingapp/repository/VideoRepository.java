package com.rai.onkar.videostreamingapp.repository;

import com.rai.onkar.videostreamingapp.model.Video;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends MongoRepository<Video, String> {
}
