package com.epam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class RoomRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Min(1)
    private byte capacity;
    @NotNull
    private Date arrivalDate;
    @NotNull
    private Date departureDate;
    private boolean is_done = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "room_type_id")
    private RoomType roomType;

    @OneToOne(mappedBy = "roomRequest")
    private Set<RoomConfirm> confirmRooms = new HashSet<>();
}
