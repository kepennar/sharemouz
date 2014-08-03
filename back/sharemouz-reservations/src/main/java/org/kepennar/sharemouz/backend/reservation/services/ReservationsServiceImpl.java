package org.kepennar.sharemouz.backend.reservation.services;

import org.kepennar.sharemouz.backend.offer.model.Offer;
import org.kepennar.sharemouz.backend.reservation.model.Reservation;
import org.kepennar.sharemouz.backend.reservation.repository.ReservationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Created by kepennar on 03/08/14.
 */
@Service
public class ReservationsServiceImpl implements ReservationsService {
    private final ReservationRepository repo;

    @Inject
    public ReservationsServiceImpl(ReservationRepository reservationRepository) {
        this.repo = reservationRepository;
    }

    @Override
    public Optional<Reservation> findById(String id) {
        return Optional.ofNullable(repo.findOne(id));
    }

    @Override
    public Page<Reservation> find(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public void deleteById(String id) {
        repo.delete(id);
    }

    @Override
    public Reservation create(Reservation reservation) {
        return repo.save(reservation);
    }

    @Override
    public Page<Reservation> findByOffer(Offer offer, Pageable pageable) {
        return repo.findByOffer(offer, pageable);
    }
}
