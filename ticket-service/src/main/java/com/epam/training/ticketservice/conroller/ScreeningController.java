package com.epam.training.ticketservice.conroller;

import com.epam.training.ticketservice.model.Screening;
import com.epam.training.ticketservice.service.ScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ScreeningController {
    private ScreeningService screeningService;

    @Autowired
    public ScreeningController(ScreeningService screeningService){ this.screeningService = screeningService; }

    public void createScreaning(String movieTitle, String roomName, LocalDateTime dateTime)
    {
        screeningService.createScreening(movieTitle, roomName, dateTime);
    }

    public void deleteScreaning(String movieTitle, String roomName, LocalDateTime dateTime)
    {
        screeningService.deleteScreening(movieTitle, roomName, dateTime);
    }

    public List<Screening> listScreenings(){ return screeningService.listScreenings();
    }
}
