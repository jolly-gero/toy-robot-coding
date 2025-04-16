package com.thitiwut.toyrobotcoding.service;

import com.thitiwut.toyrobotcoding.constant.Direction;
import com.thitiwut.toyrobotcoding.model.Position;
import com.thitiwut.toyrobotcoding.model.TableTop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandServiceTest {
    private RobotService robot;
    private CommandService command;

    @BeforeEach
    void setUp() {
        TableTop tableTop = new TableTop(5, 5);
        robot = new RobotService(tableTop);
        command = new CommandService(robot);
    }

    @Test
    void testIgnoreCommandsBeforePlace() {
        command.processCommand("MOVE");
        command.processCommand("LEFT");
        command.processCommand("REPORT");

        assertNull(robot.getPosition());
    }

    @Test
    void testValidPlaceAndMove() {
        command.processCommand("PLACE 0,0,NORTH");
        command.processCommand("MOVE");

        Position pos = robot.getPosition();
        assertNotNull(pos);
        assertEquals(0, pos.getX());
        assertEquals(1, pos.getY());
        assertEquals(Direction.NORTH, pos.getFacing());
    }

    @Test
    void testInvalidCommand() {
        command.processCommand("PLACE 0,0,NORTH");
        command.processCommand("forward");

        Position pos = robot.getPosition();
        assertNotNull(pos);
        assertEquals(0, pos.getX());
        assertEquals(0, pos.getY());
        assertEquals(Direction.NORTH, pos.getFacing());
    }

    @Nested
    @DisplayName("Rotation Tests")
    class TestRotation {
        @Test
        void testLeftTurn() {
            command.processCommand("PLACE 0,0,NORTH");
            command.processCommand("LEFT");

            assertEquals(Direction.WEST, robot.getPosition().getFacing());
        }

        @Test
        void testRightTurn() {
            command.processCommand("PLACE 0,0,NORTH");
            command.processCommand("RIGHT");
            assertEquals(Direction.EAST, robot.getPosition().getFacing());
        }
    }


    @Nested
    @DisplayName("Invalid Position Tests")
    class InvalidPlace {
        @Test
        void incompletePlaceCommand() {
            command.processCommand("PLACE");
            assertNull(robot.getPosition());
        }

        @Test
        void invalidPosition() {
            command.processCommand("PLACE -1,5,SOUTH");
            assertNull(robot.getPosition());
        }

        @Test
        void invalidDirection() {
            command.processCommand("PLACE 2,3,under");
            assertNull(robot.getPosition());
        }
    }

    @Test
    void testInCompletePlaceCommand() {
        command.processCommand("PLACE 1,2");
        assertNull(robot.getPosition());
    }

    @Test
    void testRepositionRobot() {
        command.processCommand("PLACE 0,0,NORTH");
        command.processCommand("MOVE");
        command.processCommand("PLACE 2,2,EAST");
        Position pos = robot.getPosition();
        assertEquals(2, pos.getX());
        assertEquals(2, pos.getY());
        assertEquals(Direction.EAST, pos.getFacing());
    }

    @Test
    void testReportOutput() {
        command.processCommand("PLACE 1,2,SOUTH");
        command.processCommand("REPORT");
        String expected = """
                1,2,SOUTH
                . . . . .\s
                . . . . .\s
                . v . . .\s
                . . . . .\s
                . . . . .\s
                """;
        assertEquals(expected, robot.report());
    }
}