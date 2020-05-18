package org.bajiepka.milkings.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Setter
@Entity
@Where(clause = "is_active = 'true'")
@Table(name = "milk", schema = "public")
public class Milking {

    @Id
    @SequenceGenerator(name="seq-gen",sequenceName="MILKING_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq-gen")
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "description")
    private String description;

    @Column(name = "milked_at")
    private LocalDateTime milkedAt;

    @Column(name = "last_milking")
    private LocalDateTime lastMilking;

    @PrePersist
    private void init(){
        isActive = true;
        milkedAt = Optional.ofNullable(milkedAt).orElse(LocalDateTime.now());
    }
}
