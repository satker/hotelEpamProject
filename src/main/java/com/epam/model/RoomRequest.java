package com.epam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Date;


@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Slf4j
public class RoomRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Min(1)
    private byte capacity;
    @NotNull
    private Date arrivalDate;
    @NotNull
    private Date departureDate;
    private boolean isDone = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "room_type_id")
    private RoomType roomType;

    @PostPersist
    public void onPrePersist() {
        audit("INSERT");
    }

    @PostUpdate
    public void onPreUpdate() {
        audit("UPDATE");
    }

    @PostRemove
    public void onPreRemove() {
        audit("DELETE");
    }

    private void audit(String operation) {
        log.debug("operation to room request table completed {}", operation);
    }
}
