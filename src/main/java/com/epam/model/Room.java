package com.epam.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @JsonIgnore
    @OneToMany(mappedBy = "room")
    private Set<RoomConfirm> confirmRooms = new HashSet<>();
}