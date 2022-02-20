package com.vaynshteyn.Players.utils;

import com.vaynshteyn.Players.models.Player;

import java.util.List;
import java.util.stream.Collectors;

public class ListCustomUtils {

    public static boolean isListNullOrEmpty(List<Player> players) {
        return players == null || players.isEmpty();
    }

    public static List<Player> getElementsExistingOnlyInFirstList(List<Player> first, List<Player> second) {
        return first.stream().filter(newPlayer -> second.stream()
                .noneMatch(oldPlayer -> oldPlayer.compare(newPlayer)))
                .collect(Collectors.toList());
    }

    public static List<Player> getPlayersForDeleting(List<Player> newPlayers, List<Player> oldPlayers) {
        if (newPlayers == null || oldPlayers == null) {
            return null;
        }
        return oldPlayers.stream().filter(oldPlayer -> newPlayers.stream().noneMatch(newPlayer -> newPlayer.compare(oldPlayer))).collect(Collectors.toList());

    }
}
