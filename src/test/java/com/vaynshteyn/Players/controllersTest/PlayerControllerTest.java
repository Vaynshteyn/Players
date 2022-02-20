package com.vaynshteyn.Players.controllersTest;

import com.vaynshteyn.Players.controllers.PlayerController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void getAllTest() {
        var response = playerController.getAll();
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());
        assertEquals(8, response.getBody().size());
    }

    @Test
    void getByIdTest() {
        var response = playerController.getById(1);
        var player = response.getBody();
        assertNotNull(player);
        assertEquals(1, player.getId());
        assertEquals("Alexi", player.getPlayerName());
    }
}
