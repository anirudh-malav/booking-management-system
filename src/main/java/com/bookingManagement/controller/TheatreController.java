package com.bookingManagement.controller;

import com.bookingManagement.common.constants.Constants;
import com.bookingManagement.common.helper.APIResponse;
import com.bookingManagement.dto.response.Response;
import com.bookingManagement.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.Controller.BASE_URL)
public class TheatreController {
    @Autowired
    TheatreService theatreService;

    @GetMapping(Constants.Controller.GET_THEATRE_VIEW)
    //@ValidateRequest
    public ResponseEntity<Response> getTheatreView(@PathVariable("theatreId") Long id) {
        return APIResponse.renderSuccess(APIResponse.getTheatreResponse(theatreService.getTheatre(id)), 200, HttpStatus.OK);
    }

    @GetMapping(Constants.Controller.GET_LIST_VIEW)
    //@ValidateRequest
    public ResponseEntity<Response> getListView(@PathVariable("theatreId") Long id) {
        return APIResponse.renderSuccess(APIResponse.getSeatStatusResponse(theatreService.getTheatre(id)), 200, HttpStatus.OK);
    }
}
