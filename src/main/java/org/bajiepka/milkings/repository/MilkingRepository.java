package org.bajiepka.milkings.repository;

import org.bajiepka.milkings.model.Milking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface MilkingRepository extends JpaRepository<Milking, Long> {

    @Query("select max (m.milkedAt) from Milking m")
    Optional<LocalDateTime> getLastMilkingTime();
}
