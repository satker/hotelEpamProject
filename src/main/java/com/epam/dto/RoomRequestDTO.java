package com.epam.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class RoomRequestDTO {
    private Long id;
    private byte capacity;
    private Date arrivalDate;
    private Date departureDate;
    private boolean idDone;
    private RoomTypeDTO roomType;
}
