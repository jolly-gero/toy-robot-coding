package com.thitiwut.toyrobotcoding;

import com.thitiwut.toyrobotcoding.model.TableTop;
import com.thitiwut.toyrobotcoding.service.CommandService;
import com.thitiwut.toyrobotcoding.service.RobotService;

import java.util.Scanner;

public class ToyRobotApplication {
    public static void main(String[] args) {
        var tableTop = new TableTop(5,5);

        var robotService = new RobotService(tableTop);
        var commandProcessor = new CommandService(robotService);
        var scanner = new Scanner(System.in);

        System.out.println("Enter commands (type q to quit): ");
        commandProcessor.processInput(scanner);
        scanner.close();
    }
}
