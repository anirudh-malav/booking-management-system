package com.bookingManagement.service.impl;

import com.bookingManagement.common.annotations.EnableLogging;
import com.bookingManagement.common.exceptions.InvalidDataException;
import com.bookingManagement.dto.request.BookingRequestDto;
import com.bookingManagement.dto.response.InProgressDto;
import com.bookingManagement.dto.response.TicketDto;
import com.bookingManagement.model.*;
import com.bookingManagement.repository.CityRepository;
import com.bookingManagement.repository.ShowSeatRepository;
import com.bookingManagement.repository.TheatreRepository;
import com.bookingManagement.repository.TicketRepository;
import com.bookingManagement.service.TicketService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class TicketServiceImpl implements TicketService{

    @Autowired
    CityRepository cityRepository;

    @Autowired
    TheatreRepository theatreRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ShowSeatRepository showSeatRepository;

    @EnableLogging
    @Transactional
    @Override
    public TicketDto bookTicket(@NotNull BookingRequestDto bookingRequestDto){

        Optional<City> cityEntity = cityRepository.findByCityName(bookingRequestDto.getCityName());
        validateCity(cityEntity, bookingRequestDto);

        Optional<Theatre> theatreEntity = theatreRepository.findById(bookingRequestDto.getTheatreId());
        validateTheatre(theatreEntity, bookingRequestDto);

        Optional<Show> showEntity = theatreEntity.get().getShowList().stream().filter(s->s.getShowId().equals(bookingRequestDto.getShowId())).findFirst();
        validateShow(showEntity, bookingRequestDto);

        Map<Long,ShowSeat> seatMap = new HashMap<>();
        List<Seat> seatList = new LinkedList<>();

        List<ShowSeat> showSeatList = showSeatRepository.findByShowId(showEntity.get().getShowId());

        showSeatList.stream().forEach(seat -> seatMap.put(seat.getSeatId(),seat));

        for(Long seatId : bookingRequestDto.getSeatIdList()) {
            if(seatMap.containsKey(seatId)) {
                if(seatMap.get(seatId).getStatus().equals(SeatStatus.AVAILABLE)){
                    Date current = Calendar.getInstance().getTime();
                    if((current.getTime() - seatMap.get(seatId).getModifyDate().getTime()) > 600000L){
                        seatMap.get(seatId).setStatus(SeatStatus.BOOKED);
                        seatList.add(Seat.builder().seatId(seatId).build());
                    }else{
                        throw new InvalidDataException("Seats are in Progress");
                    }
                }else {
                        throw new InvalidDataException("Seats are already booked");
                    }
            }else{
                throw new InvalidDataException("Given seats are not available");
            }
        }

        theatreRepository.save(theatreEntity.get());

        Ticket ticket = Ticket.builder().ticketId(UUID.randomUUID().toString())
                .city(cityEntity.get())
                .theatre(theatreEntity.get())
                .show(showEntity.get())
                .seatList(seatList)
                .build();

        ticket = ticketRepository.save(ticket);

        return buildTicketDto(ticket);
    }

    @Transactional
    @Override
    public InProgressDto inProgress(@NotNull BookingRequestDto bookingRequestDto){
        Optional<City> cityEntity = cityRepository.findByCityName(bookingRequestDto.getCityName());
        validateCity(cityEntity, bookingRequestDto);

        Optional<Theatre> theatreEntity = theatreRepository.findById(bookingRequestDto.getTheatreId());
        validateTheatre(theatreEntity, bookingRequestDto);

        Optional<Show> showEntity = theatreEntity.get().getShowList().stream().filter(s->s.getShowId().equals(bookingRequestDto.getShowId())).findFirst();
        validateShow(showEntity, bookingRequestDto);

        Map<Long,ShowSeat> seatMap = new HashMap<>();
        List<Seat> seatList = new LinkedList<>();

        List<ShowSeat> showSeatList = showSeatRepository.findByShowId(showEntity.get().getShowId());

        showSeatList.stream().forEach(seat -> seatMap.put(seat.getSeatId(),seat));


        for(Long seatId : bookingRequestDto.getSeatIdList()) {
            if(seatMap.containsKey(seatId)) {
                if(seatMap.get(seatId).getStatus().equals(SeatStatus.AVAILABLE)){
                    Date current = Calendar.getInstance().getTime();
                    if((current.getTime() - seatMap.get(seatId).getModifyDate().getTime()) > 600000L){
                        seatMap.get(seatId).setModifyDate(Calendar.getInstance().getTime());
                    }else{
                        throw new InvalidDataException("Seats are already in Progress");
                    }
                }else{
                        throw new InvalidDataException("Seats are already booked");
                    }
            }else{
                throw new InvalidDataException("Given seats are not available");
            }
        }

        theatreRepository.save(theatreEntity.get());

        InProgressDto inProgressDto = new InProgressDto();
        inProgressDto.setMessage("Seat booking is in Progress");
        return inProgressDto;
    }

    private TicketDto buildTicketDto(Ticket ticket){
        TicketDto ticketDto = new TicketDto();
        ticketDto.setTicketId(ticket.getTicketId());
        ticketDto.setCityName(ticket.getCity().getCityName());
        ticketDto.setTheatreId(ticket.getTheatre().getTheatreId());
        ticketDto.setShowId(ticket.getShow().getShowId());

        List<Seat> seatList = ticket.getSeatList();
        List<Long> seatIdList = new LinkedList<>();
        for(Seat seat: seatList){
            seatIdList.add(seat.getSeatId());
        }
        ticketDto.setBookedSeats(seatIdList);

        return ticketDto;
    }

    private void validateCity(Optional<City> cityEntity, BookingRequestDto bookingRequestDto ){
        if(!cityEntity.isPresent())
            throw new InvalidDataException("City "+bookingRequestDto.getCityName()+" is not available");
    }

    private void validateTheatre(Optional<Theatre> theatreEntity, BookingRequestDto bookingRequestDto){
        if(!theatreEntity.isPresent())
            throw new InvalidDataException("Theatre "+bookingRequestDto.getTheatreId()+" is Not Available");
        if(theatreEntity.get().getCity().getCityName() == null ||
                !theatreEntity.get().getCity().getCityName().equals(bookingRequestDto.getCityName())){
            throw new InvalidDataException("Theatre "+bookingRequestDto.getTheatreId()+" is not present in City "+bookingRequestDto.getCityName());
        }
    }

    private void validateShow(Optional<Show> showEntity, BookingRequestDto bookingRequestDto){
        if(!showEntity.isPresent())
            throw new InvalidDataException("Show "+bookingRequestDto.getShowId()+" is not Available in theatre "+bookingRequestDto.getTheatreId());
    }
}

