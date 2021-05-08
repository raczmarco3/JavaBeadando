package com.epam.training.ticketservice.controller;

import com.epam.training.ticketservice.controller.ScreeningController;
import com.epam.training.ticketservice.model.Screening;
import com.epam.training.ticketservice.repository.MovieRepository;
import com.epam.training.ticketservice.repository.RoomRepository;
import com.epam.training.ticketservice.repository.ScreeningRepository;
import com.epam.training.ticketservice.service.MovieService;
import com.epam.training.ticketservice.service.RoomService;
import com.epam.training.ticketservice.service.ScreeningService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ScreeningControllerTest {
    private ScreeningController screeningController;
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
        screeningController = new ScreeningController(screeningService);
    }

    @Test
    public void deleteScreaning() {
        when(screeningRepository.findAll()).thenReturn(screeningList);

        screeningController.deleteScreaning("Avengers","Pedersoli",
                LocalDateTime.of(2021, 05 , 06, 16, 00));
        var result = screeningController.getAllScreenings();

        Assertions.assertTrue(result.size() == 0);
    }

    @Test
    public void getAllScreening() {
        screeningList.add(screening1);
        screeningList.add(screening2);

        when(screeningController.getAllScreenings()).thenReturn(screeningList);
        var result = screeningController.getAllScreenings();

        Assertions.assertTrue(result.size() == 2);
    }

    @Test
    public void createScreening() {
        screeningList.add(screening1);
        screeningController.createScreaning("Avengers","Pedersoli",
                LocalDateTime.of(2021, 05 , 06, 16, 00));
        when(screeningController.getAllScreenings()).thenReturn(screeningList);
        var result = screeningController.getAllScreenings();

        Assertions.assertTrue(result.size() == 1);
    }
}
