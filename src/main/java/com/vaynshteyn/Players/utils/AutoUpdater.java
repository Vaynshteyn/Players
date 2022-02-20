package com.vaynshteyn.Players.utils;

import com.vaynshteyn.Players.controllers.PlayerController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class AutoUpdater {
        public static int PAUSE = 1000 * 60 * 15;
    public static Thread thread;

    public static void run() {
        if (thread != null && thread.isAlive()) {
            thread.interrupt();
        }
        thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(PAUSE);
                    var connection = (HttpURLConnection) new URL("http://localhost:8080/player").openConnection();
                    connection.setConnectTimeout(5000);
                    connection.setRequestMethod("POST");
                    connection.connect();
                    System.out.printf("Code: %s;%nMessage: %s%n", connection.getResponseCode(),connection.getResponseMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public void updatePause() {
        if (thread.isAlive()) {
            thread.interrupt();
        }
        run();
    }
}
