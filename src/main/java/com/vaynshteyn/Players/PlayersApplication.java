package com.vaynshteyn.Players;

import com.vaynshteyn.Players.utils.AutoUpdater;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlayersApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlayersApplication.class, args);
		AutoUpdater.run();
	}

}
