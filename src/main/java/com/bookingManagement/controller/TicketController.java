package com.bookingManagement.controller;

import com.bookingManagement.common.annotations.ValidateRequest;
import com.bookingManagement.common.constants.Constants;
import com.bookingManagement.common.exceptions.InvalidDataException;
import com.bookingManagement.common.helper.APIResponse;
import com.bookingManagement.dto.request.BookingRequestDto;
import com.bookingManagement.dto.response.Response;
import com.bookingManagement.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.Controller.BASE_URL)
    public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping(Constants.Controller.BOOK_TICKET)
    //@ValidateRequest
    public ResponseEntity<Response> bookTicketNow(@RequestBody BookingRequestDto bookingRequestDto) {
        try {
            return APIResponse.renderSuccess(ticketService.bookTicket(bookingRequestDto), 200, HttpStatus.OK);
        }catch(ObjectOptimisticLockingFailureException e){
            throw new InvalidDataException("seats are not available");
        }
    }

    @PostMapping(Constants.Controller.IN_PROGRESS)
    @ValidateRequest
    public ResponseEntity<Response> markInProgress(@RequestBody BookingRequestDto bookingRequestDto) {
        try {
            return APIResponse.renderSuccess(ticketService.inProgress(bookingRequestDto), 200, HttpStatus.OK);
        }catch(ObjectOptimisticLockingFailureException e){
            throw new InvalidDataException("Seats are not available");
        }
    }
    }
