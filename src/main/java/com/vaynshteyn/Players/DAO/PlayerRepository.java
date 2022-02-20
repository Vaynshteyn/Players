package com.vaynshteyn.Players.DAO;

import com.vaynshteyn.Players.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
}
