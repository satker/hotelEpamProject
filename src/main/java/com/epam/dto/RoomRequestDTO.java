package com.epam.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class RoomRequestDTO {
    private byte capacity;
    private Date arrivalDate;
    private Date departure;
    private boolean idDone;
}
