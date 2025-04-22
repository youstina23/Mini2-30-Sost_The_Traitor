package com.example.MiniProj2.services;

import com.example.MiniProj2.models.Trip;
import com.example.MiniProj2.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TripService {

    private final TripRepository tripRepository;

    @Autowired
    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public Trip addTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    public Trip getTripById(Long id) {
        return tripRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Trip with ID " + id + " not found"));
    }

    public Trip updateTrip(Long id, Trip tripDetails) {
        Trip existingTrip = getTripById(id);
        existingTrip.setTripDate(tripDetails.getTripDate());
        existingTrip.setOrigin(tripDetails.getOrigin());
        existingTrip.setDestination(tripDetails.getDestination());
        existingTrip.setTripCost(tripDetails.getTripCost());
        existingTrip.setCaptain(tripDetails.getCaptain());
        existingTrip.setCustomer(tripDetails.getCustomer());
        return tripRepository.save(existingTrip);
    }

    public void deleteTrip(Long id) {
        if (!tripRepository.existsById(id)) {
            throw new NoSuchElementException("Trip with ID " + id + " not found");
        }
        tripRepository.deleteById(id);
    }

    public List<Trip> findTripsWithinDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return tripRepository.findByTripDateBetween(startDate, endDate);
    }

    public List<Trip> findTripsByCaptainId(Long captainId) {
        return tripRepository.findByCaptainId(captainId);
    }
}
