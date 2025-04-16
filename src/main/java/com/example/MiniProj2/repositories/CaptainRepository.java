package com.example.MiniProj2.repositories;

import com.example.MiniProj2.models.Captain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CaptainRepository extends JpaRepository<Captain, Long> {

    List<Captain> findByAvgRatingScoreGreaterThan(Double threshold);

    Optional<Captain> findByLicenseNumber(String licenseNumber);
}

