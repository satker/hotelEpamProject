package com.epam.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer number;

    @NotNull
    private String numberPlace;

    @NotNull
    private String costNight;

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
        log.debug("operation to room table completed {}", operation);
    }
}
