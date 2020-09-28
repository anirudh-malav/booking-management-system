package com.bookingManagement.service;

import com.bookingManagement.dto.response.InProgressDto;
import com.bookingManagement.dto.response.TicketDto;
import com.bookingManagement.dto.request.BookingRequestDto;

public interface TicketService {
    TicketDto bookTicket(BookingRequestDto bookingRequestDto);

    InProgressDto inProgress(BookingRequestDto bookingRequestDto);
}
