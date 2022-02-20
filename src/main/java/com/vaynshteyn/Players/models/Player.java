package com.vaynshteyn.Players.models;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "players")
public class Player {
    @Id
    @CsvBindByName(column = "id", required = true)
    int id;

    @CsvBindByName(column = "nickname", required = true)
    String playerName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", playerName='" + playerName + '\'' +
                '}';
    }

    public boolean compare(Player newPlayer) {
        return this.getId() == newPlayer.getId();
    }
}
