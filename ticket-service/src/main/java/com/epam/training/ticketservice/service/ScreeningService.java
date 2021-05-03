package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.model.Screening;
import com.epam.training.ticketservice.repository.MovieRepository;
import com.epam.training.ticketservice.repository.RoomRepository;
import com.epam.training.ticketservice.repository.ScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class ScreeningService {
    private ScreeningRepository screeningRepository;
    private RoomRepository roomRepository;
    private MovieRepository movieRepository;

    @Autowired
    public ScreeningService( ScreeningRepository screeningRepository, MovieRepository movieRepository, RoomRepository roomRepository)
    {
        this.screeningRepository = screeningRepository;
        this.movieRepository = movieRepository;
        this.roomRepository = roomRepository;
    }

    @Transactional
    public void createScreening(String movieTitle, String roomName, LocalDateTime dateTime)
    {
        if(movieRepository.findByTitle(movieTitle) != null && roomRepository.findByName(roomName) != null)
        {
            screeningRepository.save(new Screening(movieTitle, roomName, dateTime));
        }
    }

    @Transactional
    public void deleteScreening(String movieTitle, String roomName, LocalDateTime dateTime)
    {
        var screening = StreamSupport.stream(screeningRepository.findAll().spliterator(), false)
                .filter(s -> s.getRoomName().equals(roomName) &&
                        s.getMovieTitle().equals(movieTitle) &&
                        s.getDateTime().equals(dateTime)).findAny();
        if(screening != null){ screeningRepository.delete(screening.get()); }
    }

    @Transactional
    public List<Screening> listScreenings()
    {
        List<Screening> screenings = new ArrayList<>();
        screeningRepository.findAll().forEach( screening -> screenings.add(screening));
        return screenings;
    }
}
