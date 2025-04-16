package com.example.MiniProj2.services;

import com.example.MiniProj2.models.Rating;
import com.example.MiniProj2.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public Rating addRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    public Rating updateRating(String id, Rating updatedRating) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Rating " + id + " not found"));

        rating.setScore(updatedRating.getScore());
        rating.setComment(updatedRating.getComment());
        rating.setRatingDate(updatedRating.getRatingDate());
        return ratingRepository.save(rating);
    }

    public void deleteRating(String id) {
        if (!ratingRepository.existsById(id)) {
            throw new IllegalArgumentException("Rating " + id + " not found");
        }
        ratingRepository.deleteById(id);
    }

    public List<Rating> getRatingsByEntity(Long entityId, String entityType) {
        return ratingRepository.findByEntityIdAndEntityType(entityId, entityType);
    }

    public List<Rating> findRatingsAboveScore(int minScore) {
        return ratingRepository.findByScoreGreaterThan(minScore);
    }
}