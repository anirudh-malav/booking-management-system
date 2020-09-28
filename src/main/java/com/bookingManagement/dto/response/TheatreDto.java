package com.bookingManagement.dto.response;

import com.bookingManagement.model.City;
import com.bookingManagement.model.ShowSeat;
import lombok.Data;

import java.util.List;

@Data
public class TheatreDto {

    private Long theatreId;

    private String theatreName;

    private City city;

    private List<ShowSeat> showSeatList;
}
