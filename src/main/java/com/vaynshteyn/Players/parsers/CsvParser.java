package com.vaynshteyn.Players.parsers;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.vaynshteyn.Players.models.Player;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CsvParser {

    private static String FILE_PATH = "src/main/java/com/vaynshteyn/Players/res/players.csv";

    public static void main(String[] args) throws IOException {
        var players = parseToList();
        players.forEach(System.out::println);
    }

    public static List<Player> parseToList() throws IOException {
        List<Player> players;
        try (
                Reader reader = Files.newBufferedReader(Paths.get(FILE_PATH));
        ) {
            CsvToBean<Player> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Player.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            players = csvToBean.parse();
        }
        return players;
    }

    public static void parseToCsv(List<Player> players) {

    }
}
