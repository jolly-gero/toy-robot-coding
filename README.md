# Toy Robot Coding

A Java console application simulating a toy robot moving on a 5x5 tabletop grid.

The robot can:
- Be placed on the table using `PLACE X,Y,Direction`
- Directions are support only (`NORTH`, `EAST`, `SOUTH`, `WEST`)
- Move forward (`MOVE`)
- Rotate 90 degrees (`LEFT`, `RIGHT`)
- Report its current position (`REPORT`) along with a visual grid display and direction symbol
  - `^` = NORTH
  - `>` = EAST
  - `v` = SOUTH
  - `<` = WEST

Rules:
- The simulator prevents the robot from falling off the table.
- First command must be PLACE
- Robot will ignore commands until placed
- Invalid positions and moves are ignored safely

---

## Requirements

- Java 17+
- Maven 3+

---

## Build & Run
### Build:
```bash
mvn clean package
```
### Run Application:
```bash
java -cp target/toy-robot-coding-1.0-SNAPSHOT.jar com.thitiwut.toyrobotcoding.ToyRobotApplication
