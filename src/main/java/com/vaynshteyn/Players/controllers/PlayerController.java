package com.vaynshteyn.Players.controllers;


import com.vaynshteyn.Players.DAO.PlayerRepository;
import com.vaynshteyn.Players.models.Player;
import com.vaynshteyn.Players.parsers.CsvParser;
import com.vaynshteyn.Players.utils.ListCustomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("player")
public class PlayerController {
    @Autowired
    PlayerRepository playerRepository;

    @PostMapping
    public ResponseEntity<String> updatePlayers() {
        try {
            var newPlayers = CsvParser.parseToList();
            var oldPlayers = getAll().getBody();
            if (!ListCustomUtils.isListNullOrEmpty(newPlayers) && !ListCustomUtils.isListNullOrEmpty(oldPlayers)) {
                var playersForSaving = ListCustomUtils.getElementsExistingOnlyInFirstList(newPlayers, oldPlayers);
                if (!ListCustomUtils.isListNullOrEmpty(playersForSaving)) {
                    playerRepository.saveAll(playersForSaving);
                }
                //I'm not sure, do we need to save deleting from csv position, but I added it
                var playersForDeleting = ListCustomUtils.getElementsExistingOnlyInFirstList(oldPlayers, newPlayers);
                if (!ListCustomUtils.isListNullOrEmpty(playersForDeleting)) {
                    playerRepository.deleteAll(playersForDeleting);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.OK).body(String.format("Player update failed:%n%s", e.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.OK).body("Players updated");
    }

    @GetMapping("all")
    public ResponseEntity<List<Player>> getAll() {
        var players = playerRepository.findAll();
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(players, httpHeaders, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Player> getById(@PathVariable int id) {
        var player = playerRepository.findById(id).orElse(null);
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(player, httpHeaders, HttpStatus.OK);
    }
}
