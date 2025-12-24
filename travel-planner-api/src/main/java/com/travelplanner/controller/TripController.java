package com.travelplanner.controller;

import com.travelplanner.dto.TripRequest;
import com.travelplanner.dto.TripResponse;
import com.travelplanner.service.TripService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
@RequiredArgsConstructor
@Tag(name = "Trips", description = "Trip management APIs")
public class TripController {

    private final TripService tripService;

    @GetMapping
    @Operation(summary = "Get all trips for current user")
    public ResponseEntity<List<TripResponse>> getAllTrips(
            @AuthenticationPrincipal UserDetails userDetails) {
        List<TripResponse> trips = tripService.getAllTripsForUser(userDetails.getUsername());
        return ResponseEntity.ok(trips);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get trip by ID")
    public ResponseEntity<TripResponse> getTripById(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        TripResponse trip = tripService.getTripById(id, userDetails.getUsername());
        return ResponseEntity.ok(trip);
    }

    @PostMapping
    @Operation(summary = "Create a new trip")
    public ResponseEntity<TripResponse> createTrip(
            @Valid @RequestBody TripRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        TripResponse trip = tripService.createTrip(request, userDetails.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(trip);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing trip")
    public ResponseEntity<TripResponse> updateTrip(
            @PathVariable Long id,
            @Valid @RequestBody TripRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        TripResponse trip = tripService.updateTrip(id, request, userDetails.getUsername());
        return ResponseEntity.ok(trip);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a trip")
    public ResponseEntity<Void> deleteTrip(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        tripService.deleteTrip(id, userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/summary")
    @Operation(summary = "Get trip summary with budget and itinerary count")
    public ResponseEntity<TripResponse> getTripSummary(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        TripResponse summary = tripService.getTripSummary(id, userDetails.getUsername());
        return ResponseEntity.ok(summary);
    }
}
