package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.controller.ScreeningController;
import com.epam.training.ticketservice.model.Screening;
import com.epam.training.ticketservice.repository.MovieRepository;
import com.epam.training.ticketservice.repository.RoomRepository;
import com.epam.training.ticketservice.repository.ScreeningRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class ScreeningServiceTest {
    private ScreeningRepository screeningRepository;
    private ScreeningService screeningService;
    private RoomRepository roomRepository;
    private MovieRepository movieRepository;
    private MovieService movieService;
    private RoomService roomService;
    private List<Screening> screeningList = new ArrayList<>();
    private Screening screening1 = new Screening("Avengers","Pedersoli",
            LocalDateTime.of(2021, 05 , 06, 16, 00),
            120, "action");
    private Screening screening2 = new Screening("Avengers 2","Pedersoli",
            LocalDateTime.of(2021, 05 , 06, 18, 20),
            120, "action");

    @BeforeEach
    public void init() {
        screeningRepository = Mockito.mock(ScreeningRepository.class);
        roomRepository = Mockito.mock(RoomRepository.class);
        movieRepository = Mockito.mock(MovieRepository.class);
        screeningService = Mockito.mock(ScreeningService.class);
        roomService = Mockito.mock(RoomService.class);
        movieService = Mockito.mock(MovieService.class);
    }

    @Test
    public void deleteScreaning() {
        when(screeningRepository.findAll()).thenReturn(screeningList);

        screeningService.deleteScreening("Avengers","Pedersoli",
                LocalDateTime.of(2021, 05 , 06, 16, 00));
        var result = screeningService.listScreenings();

        Assertions.assertTrue(result.size() == 0);
    }

    @Test
    public void getAllScreening() {
        screeningList.add(screening1);
        screeningList.add(screening2);

        when(screeningService.listScreenings()).thenReturn(screeningList);
        var result = screeningService.listScreenings();

        Assertions.assertTrue(result.size() == 2);
    }

    @Test
    public void createScreening() {
        screeningList.add(screening1);
        screeningService.createScreening("Avengers","Pedersoli",
                LocalDateTime.of(2021, 05 , 06, 16, 00));
        when(screeningService.listScreenings()).thenReturn(screeningList);
        var result = screeningService.listScreenings();

        Assertions.assertTrue(result.size() == 1);
    }
}
