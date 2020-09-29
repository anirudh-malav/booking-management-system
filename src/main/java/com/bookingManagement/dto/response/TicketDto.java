package com.bookingManagement.dto.response;

import com.bookingManagement.model.ShowSeat;
import lombok.Data;

import java.util.List;

@Data
public class TicketDto {

    private String ticketId;

    private String cityName;

    private Long theatreId;

    private Long showId;

    private List<ShowSeat> bookedShowSeats;
}
