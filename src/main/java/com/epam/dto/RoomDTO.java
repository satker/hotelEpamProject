package com.epam.dto;

import com.epam.model.RoomType;
import lombok.Data;

@Data
public class RoomDTO {
    private Integer number;
    private String numberPlace;
    private String costNight;
    private RoomTypeDTO roomType;
}

