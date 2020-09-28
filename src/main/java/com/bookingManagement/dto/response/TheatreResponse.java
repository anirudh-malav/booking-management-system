package com.bookingManagement.dto.response;

import com.bookingManagement.model.ShowSeat;
import lombok.Data;

import java.util.List;

@Data
public class TheatreResponse {
    private Long theaterId;
    private String theaterName;
    private List<ShowSeat> showSeatList;
}
