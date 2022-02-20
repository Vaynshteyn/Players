package com.vaynshteyn.Players.controllers;


import com.vaynshteyn.Players.DAO.PlayerRepository;
import com.vaynshteyn.Players.models.Player;
import com.vaynshteyn.Players.parsers.CsvParser;
import com.vaynshteyn.Players.utils.AutoUpdater;
import com.vaynshteyn.Players.utils.ListComparingUtils;
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
            var playersForSaving = ListComparingUtils.getPlayersForSaving(newPlayers, oldPlayers);
            var playersForDeleting = ListComparingUtils.getPlayersForDeleting(newPlayers, oldPlayers);
            playerRepository.saveAll(playersForSaving);
            playerRepository.deleteAll(playersForDeleting);
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
