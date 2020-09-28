package com.bookingManagement.service.impl;

import com.bookingManagement.common.annotations.EnableLogging;
import com.bookingManagement.common.exceptions.InvalidDataException;
import com.bookingManagement.dto.response.TheatreDto;
import com.bookingManagement.model.Show;
import com.bookingManagement.model.ShowSeat;
import com.bookingManagement.model.Theatre;
import com.bookingManagement.repository.ShowSeatRepository;
import com.bookingManagement.repository.TheatreRepository;
import com.bookingManagement.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TheatreServiceImpl implements TheatreService {

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @EnableLogging
    @Override
    public TheatreDto getTheatre(Long theatreId) {

        Optional<Theatre> theatreEntity = theatreRepository.findById(theatreId);
        if (!theatreEntity.isPresent()) {
            throw new InvalidDataException("theatre Not Found for Id : " + theatreId);
        }
        return buildTheatreDto(theatreEntity.get());
    }

    private TheatreDto buildTheatreDto(Theatre theatre) {
        TheatreDto theatreDto = new TheatreDto();
        theatreDto.setTheatreId(theatre.getTheatreId());
        theatreDto.setTheatreName(theatre.getTheatreName());

        List<ShowSeat> showSeatList = new ArrayList<>();
        for(Show show: theatre.getShowList()){
            showSeatList.addAll(showSeatRepository.findByShowId(show.getShowId()));
        }
        theatreDto.setShowSeatList(showSeatList);
        return theatreDto;
    }
}
