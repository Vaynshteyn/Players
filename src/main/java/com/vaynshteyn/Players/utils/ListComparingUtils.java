package com.vaynshteyn.Players.utils;

import com.vaynshteyn.Players.models.Player;

import java.util.List;
import java.util.stream.Collectors;

public class ListComparingUtils {
    public static List<Player> getPlayersForSaving(List<Player> newPlayers, List<Player> oldPlayers) {
        return newPlayers.stream().filter(newPlayer -> oldPlayers.stream().noneMatch(oldPlayer -> oldPlayer.compare(newPlayer))).collect(Collectors.toList());
    }

    public static List<Player> getPlayersForDeleting(List<Player> newPlayers, List<Player> oldPlayers) {
        return oldPlayers.stream().filter(oldPlayer -> newPlayers.stream().noneMatch(newPlayer -> newPlayer.compare(oldPlayer))).collect(Collectors.toList());

    }
}
