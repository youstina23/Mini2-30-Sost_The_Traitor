package com.example.MiniProj2.models;

import com.example.MiniProj2.models.Trip;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "captain")
public class Captain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String licenseNumber;
    private Double avgRatingScore;

    @OneToMany(mappedBy = "captain", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Trip> trips;

    // Constructors

    // 1. Full constructor
    public Captain(Long id, String name, String licenseNumber, Double avgRatingScore, List<Trip> trips) {
        this.id = id;
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.avgRatingScore = avgRatingScore;
        this.trips = trips;
    }

    // 2. Constructor without id
    public Captain(String name, String licenseNumber, Double avgRatingScore, List<Trip> trips) {
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.avgRatingScore = avgRatingScore;
        this.trips = trips;
    }

    // 3. Empty constructor
    public Captain() {}

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public Double getAvgRatingScore() {
        return avgRatingScore;
    }

    public void setAvgRatingScore(Double avgRatingScore) {
        this.avgRatingScore = avgRatingScore;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }
}
