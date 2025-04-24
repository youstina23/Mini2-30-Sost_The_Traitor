package com.example.MiniProj2.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import com.example.MiniProj2.models.Trip;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phoneNumber;


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Trip> trips;


    public Customer(Long id, String name, String email, String phoneNumber, List<Trip> trips) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.trips = trips;
    }


    public Customer(String name, String email, String phoneNumber, List<Trip> trips) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.trips = trips;
    }


    public Customer(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.trips = new ArrayList<>(); // optional: initialize to empty list
    }



    public Customer() {}


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }
}

