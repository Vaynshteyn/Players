package com.vaynshteyn.Players.controllersTest;

import com.vaynshteyn.Players.controllers.PlayerController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PlayerControllerTest {
    @Autowired
    PlayerController playerController;

    @Test
    void updateTest() {
        var response = playerController.updatePlayers();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Players updated", response.getBody());
    }
}
