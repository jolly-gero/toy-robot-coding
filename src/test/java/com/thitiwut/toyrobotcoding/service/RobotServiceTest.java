package com.thitiwut.toyrobotcoding.service;

import com.thitiwut.toyrobotcoding.constant.Direction;
import com.thitiwut.toyrobotcoding.model.TableTop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RobotServiceTest {
    private RobotService robot;

    @BeforeEach
    void setUp() {
        robot = new RobotService(new TableTop(5, 5));
    }

    @Test
    void testMove() {
        robot.place(0, 0, Direction.NORTH);
        robot.move();
        String expected = """
                0,1,NORTH
                . . . . .\s
                . . . . .\s
                . . . . .\s
                ^ . . . .\s
                . . . . .\s
                """;
        assertEquals(expected, robot.report());
    }

    @Test
    void testRotation() {
        robot.place(0, 0, Direction.NORTH);
        robot.left();
        String expected = """
                0,0,WEST
                . . . . .\s
                . . . . .\s
                . . . . .\s
                . . . . .\s
                < . . . .\s
                """;
        assertEquals(expected, robot.report());
    }

    @Test
    void testMoveAndRotate() {
        robot.place(1, 2, Direction.EAST);
        robot.move();
        robot.move();
        robot.right();
        robot.move();
        robot.right();
        robot.move();
        String expected = """
                2,1,WEST
                . . . . .\s
                . . . . .\s
                . . . . .\s
                . . < . .\s
                . . . . .\s
                """;
        assertEquals(expected, robot.report());
    }

    @Test
    void testInvalidMoveOffTable() {
        robot.place(0, 0, Direction.SOUTH);
        robot.move();
        String expected = """
                0,0,SOUTH
                . . . . .\s
                . . . . .\s
                . . . . .\s
                . . . . .\s
                v . . . .\s
                """;
        assertEquals(expected, robot.report());
    }

    @Test
    void testIgnoreCommandsBeforePlace() {
        RobotService newRobot = new RobotService(new TableTop(5, 5));
        newRobot.move();
        newRobot.left();
        newRobot.right();
        assertEquals("", newRobot.report());
    }

    @Test
    void testInvalidPlace() {
        RobotService newRobot = new RobotService(new TableTop(5, 5));
        newRobot.move();
        newRobot.left();
        newRobot.right();
        assertEquals("", newRobot.report());
    }
}