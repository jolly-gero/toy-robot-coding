package com.thitiwut.toyrobotcoding.service;

import com.thitiwut.toyrobotcoding.constant.Direction;
import lombok.AllArgsConstructor;

import java.util.Scanner;

@AllArgsConstructor
public class CommandService {
    private final RobotService robotService;

    public void processCommand(String input) {
        var line = input.trim();
        if (line.startsWith("PLACE")) {
            String[] parts;
            try {
                parts = line.substring(6).split(",");
            } catch (Exception e) {
                System.out.println("Incomplete PLACE command");
                return;
            }
            if (parts.length == 3) {
                try {
                    int x = Integer.parseInt(parts[0].trim());
                    int y = Integer.parseInt(parts[1].trim());
                    Direction dir = Direction.valueOf(parts[2].trim().toUpperCase());
                    robotService.place(x, y, dir);
                } catch (Exception e) {
                    System.out.println("Invalid PLACE command: " + line);
                }
            } else {
                System.out.println("Invalid PLACE command format (support only 3 parts): " + line);
            }
        } else if (robotService.isPlaced()) {
            switch (input) {
                case "MOVE" -> robotService.move();
                case "LEFT" -> robotService.left();
                case "RIGHT" -> robotService.right();
                case "REPORT" -> System.out.println(robotService.report());
                default -> System.out.println("Invalid Command: " + line);
            }
        } else {
            System.out.println("The Robot haven't been put on the table properly yet. Please type Command according to this format 'PLACE x,y,DIRECTION' ");
        }
    }

    public void processInput(Scanner scanner) {
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("q")) break;
            if (!input.isBlank()) processCommand(input);
        }
    }
}
