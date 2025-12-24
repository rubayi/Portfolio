package com.travelplanner.service;

import com.travelplanner.dto.TripRequest;
import com.travelplanner.dto.TripResponse;
import com.travelplanner.entity.Trip;
import com.travelplanner.entity.User;
import com.travelplanner.exception.ResourceNotFoundException;
import com.travelplanner.exception.UnauthorizedException;
import com.travelplanner.repository.TripRepository;
import com.travelplanner.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TripService {

    private final TripRepository tripRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<TripResponse> getAllTripsForUser(String email) {
        User user = findUserByEmail(email);
        return tripRepository.findByOwnerOrderByStartDateDesc(user)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TripResponse getTripById(Long id, String email) {
        Trip trip = findTripAndValidateOwnership(id, email);
        return mapToResponse(trip);
    }

    public TripResponse createTrip(TripRequest request, String email) {
        User user = findUserByEmail(email);
        
        Trip trip = Trip.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .destination(request.getDestination())
                .budget(request.getBudget())
                .owner(user)
                .build();
        
        Trip savedTrip = tripRepository.save(trip);
        return mapToResponse(savedTrip);
    }

    public TripResponse updateTrip(Long id, TripRequest request, String email) {
        Trip trip = findTripAndValidateOwnership(id, email);
        
        trip.setTitle(request.getTitle());
        trip.setDescription(request.getDescription());
        trip.setStartDate(request.getStartDate());
        trip.setEndDate(request.getEndDate());
        trip.setDestination(request.getDestination());
        trip.setBudget(request.getBudget());
        
        if (request.getStatus() != null) {
            trip.setStatus(request.getStatus());
        }
        
        Trip updatedTrip = tripRepository.save(trip);
        return mapToResponse(updatedTrip);
    }

    public void deleteTrip(Long id, String email) {
        Trip trip = findTripAndValidateOwnership(id, email);
        tripRepository.delete(trip);
    }

    @Transactional(readOnly = true)
    public TripResponse getTripSummary(Long id, String email) {
        Trip trip = findTripAndValidateOwnership(id, email);
        return mapToResponseWithSummary(trip);
    }

    private Trip findTripAndValidateOwnership(Long id, String email) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found with id: " + id));
        
        if (!trip.getOwner().getEmail().equals(email)) {
            throw new UnauthorizedException("You don't have permission to access this trip");
        }
        
        return trip;
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
    }

    private TripResponse mapToResponse(Trip trip) {
        return TripResponse.builder()
                .id(trip.getId())
                .title(trip.getTitle())
                .description(trip.getDescription())
                .startDate(trip.getStartDate())
                .endDate(trip.getEndDate())
                .destination(trip.getDestination())
                .budget(trip.getBudget())
                .status(trip.getStatus().name())
                .createdAt(trip.getCreatedAt())
                .updatedAt(trip.getUpdatedAt())
                .build();
    }

    private TripResponse mapToResponseWithSummary(Trip trip) {
        TripResponse response = mapToResponse(trip);
        response.setItineraryCount(trip.getItineraries().size());
        response.setTotalExpenses(
                trip.getExpenses().stream()
                        .map(e -> e.getAmount())
                        .reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add)
        );
        return response;
    }
}
