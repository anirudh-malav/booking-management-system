package com.bookingManagement.common.helper;

import com.bookingManagement.common.constants.Constants;
import com.bookingManagement.dto.response.*;
import com.bookingManagement.model.ShowSeat;
import com.bookingManagement.repository.ShowSeatRepository;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

public class APIResponse {

    @Autowired
    ShowSeatRepository showSeatRepository;

    public static ResponseEntity<Response> renderSuccess(Object data, int code, HttpStatus status) {
        Meta meta = successMeta(code);
        Response response = new Response();
        response.setMeta(meta);
        response.setData(data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public static ResponseEntity<Response> renderFailure(String customErrorMessage, int code, HttpStatus status) {
        Meta meta = failureMeta(customErrorMessage, code);
        Response response = new Response();
        response.setMeta(meta);
        return new ResponseEntity<>(response, status);
    }

    public static TheatreResponse getTheatreResponse(TheatreDto theatreDto){
        TheatreResponse theatreResponse = new TheatreResponse();
        theatreResponse.setTheaterId(theatreDto.getTheatreId());
        theatreResponse.setTheaterName(theatreDto.getTheatreName());
        theatreResponse.setShowSeatList(theatreDto.getShowSeatList());
        return theatreResponse;
    }

    public static SeatsStatusResponse getSeatStatusResponse(TheatreDto theatreDto){
        SeatsStatusResponse seatsStatusResponse = new SeatsStatusResponse();
        seatsStatusResponse.setTheaterId(theatreDto.getTheatreId());
        seatsStatusResponse.setTheaterName(theatreDto.getTheatreName());

        Map<Long, List<ShowSeat>> showSeatMap = buildShowSeatMap(theatreDto.getShowSeatList());
        List<TheatreView> theatreViewList = new ArrayList<>();

        for (Map.Entry<Long, List<ShowSeat>> set : showSeatMap.entrySet()){
            TheatreView theatreView = new TheatreView();
            buildTheatreView(theatreView, set.getKey(), set.getValue());
            theatreViewList.add(theatreView);
        }

        seatsStatusResponse.setTheatreViewList(theatreViewList);

        return seatsStatusResponse;
    }

    private static Meta successMeta(int code) {
        Meta meta = new Meta();
        meta.setCode(code);
        //setDescriptionAndUICode(meta, code);
        meta.setRequestId(MDC.get("requestId"));
        meta.setStatus("success");
        return meta;
    }

    private static Meta failureMeta(String error, int code) {
        Meta meta = new Meta();
        meta.setCode(code);
        //setDescriptionAndUICode(meta, code);
        meta.setError(error);
        meta.setRequestId(MDC.get("requestId"));
        meta.setStatus("failure");
        return meta;
    }

    private static Map<Long, List<ShowSeat>> buildShowSeatMap(List<ShowSeat> showSeatList){
        Map<Long, List<ShowSeat>> showSeatMap = new HashMap<>();
        for(ShowSeat showSeat : showSeatList){
            if(showSeatMap.containsKey(showSeat.getShowId())){
                List<ShowSeat> seatList = showSeatMap.get(showSeat.getShowId());
                seatList.add(showSeat);
                showSeatMap.put(showSeat.getShowId(), seatList);
            }else{
                List<ShowSeat> newList = new ArrayList<>();
                newList.add(showSeat);
                showSeatMap.put(showSeat.getShowId(), newList);
            }
        }
        return showSeatMap;
    }

    private static void buildTheatreView(TheatreView theatreView, Long showId, List<ShowSeat> showSeatList) {
        theatreView.setShowId(showId);
        List<Long> availableList = new ArrayList<>();
        List<Long> bookedList = new ArrayList<>();
        List<Long> inProgressList = new ArrayList<>();

        for (ShowSeat showSeat : showSeatList) {
            if (showSeat.getStatus().toString().equals(Constants.Status.BOOKED)) {
                bookedList.add(showSeat.getSeatId());
            } else {
                Date current = Calendar.getInstance().getTime();
                //if seat is not updated in last 10 minutes, its available.
                if ((current.getTime() - showSeat.getModifyDate().getTime()) > 600000L) {
                    availableList.add(showSeat.getSeatId());
                } else {
                    inProgressList.add(showSeat.getSeatId());
                }
            }
        }
        theatreView.setAvailableSeatList(availableList);
        theatreView.setBookedSeatList(bookedList);
        theatreView.setInProgressSeatList(inProgressList);
    }

}
