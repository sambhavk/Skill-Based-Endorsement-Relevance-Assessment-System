package com.fabhotels.round2.persistence;

import com.fabhotels.round2.domain.Endorsement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EndorsementRepository extends JpaRepository<Endorsement, Long> {
    List<Endorsement> findByEndorsee(Long endorsee);
}
