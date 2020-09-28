package com.bookingManagement.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class SeatsStatusResponse {
    private Long theaterId;
    private String theaterName;
    private List<TheatreView> theatreViewList;
}
