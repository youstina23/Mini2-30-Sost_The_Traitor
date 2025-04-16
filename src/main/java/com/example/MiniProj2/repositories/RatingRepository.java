package com.example.MiniProj2.repositories;

import com.example.MiniProj2.models.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface RatingRepository extends MongoRepository<Rating, String> {

    List<Rating> findByEntityIdAndEntityType(Long entityId, String entityType);

    List<Rating> findByScoreGreaterThan(int minScore);
}
