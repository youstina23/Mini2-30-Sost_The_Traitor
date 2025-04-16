package com.example.MiniProj2.services;

import com.example.MiniProj2.models.Rating;
import com.example.MiniProj2.repositories.RatingRepository;
import com.example.MiniProj2.repositories.CaptainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private final CaptainRepository captainRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository, CaptainRepository captainRepository) {
        this.ratingRepository = ratingRepository;
        this.captainRepository = captainRepository;
    }

    public Rating addRating(Rating rating) {
        System.out.println("Received rating: " + rating);

        Rating savedRating = ratingRepository.save(rating);
        System.out.println("Saved rating with ID: " + savedRating.getId());

        if ("captain".equalsIgnoreCase(rating.getEntityType())) {
            Long captainId = rating.getEntityId();
            System.out.println("Rating is for a captain. Captain ID: " + captainId);

            List<Rating> captainRatings = ratingRepository.findByEntityIdAndEntityType(captainId, "captain");
            System.out.println("Found " + captainRatings.size() + " ratings for Captain ID: " + captainId);

            double average = captainRatings.stream()
                    .mapToInt(Rating::getScore)
                    .average()
                    .orElse(0.0);
            System.out.println("Calculated average rating: " + average);

            captainRepository.findById(captainId).ifPresentOrElse(
                    captain -> {
                        System.out.println("Captain found: " + captain.getName());
                        captain.setAvgRatingScore(average);
                        captainRepository.save(captain);
                        System.out.println("Updated captain's average rating.");
                    },
                    () -> System.out.println("No captain found with ID: " + captainId)
            );
        } else {
            System.out.println("Rating is not for a captain. EntityType = " + rating.getEntityType());
        }

        return savedRating;
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