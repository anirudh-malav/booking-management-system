package com.bookingManagement.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class BookingRequestDto {

    private String cityName;

    private Long theatreId;

    private Long showId;

    private List<Long> seatIdList;
}
