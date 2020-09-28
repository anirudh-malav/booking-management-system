package com.bookingManagement.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class TicketDto {

    private String ticketId;

    private String cityName;

    private Long theatreId;

    private Long showId;

    private List<Long> bookedSeats;
}
