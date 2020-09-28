package com.bookingManagement.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class TheatreView {
    Long showId;
    List<Long> availableSeatList;
    List<Long> bookedSeatList;
    List<Long> inProgressSeatList;
}
