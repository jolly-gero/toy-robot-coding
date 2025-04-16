package com.thitiwut.toyrobotcoding.service;

import com.thitiwut.toyrobotcoding.constant.Direction;
import com.thitiwut.toyrobotcoding.model.Position;
import com.thitiwut.toyrobotcoding.model.TableTop;
import com.thitiwut.toyrobotcoding.util.CommonUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RobotService {
    @Getter
    private Position position;
    private final TableTop tableTop;

    public void place(int x, int y, Direction facing) {
        if (CommonUtil.isValidPosition(x, y, tableTop)) {
            position = new Position(x, y, facing);
        } else {
            String message = String.format("""
                           Invalid Position input: (%s,%s)\s
                           The table is %sx%s and Position shouldn't exceed (%s,%s)
                           \s""",
                    x, y,
                    tableTop.width(), tableTop.height(),
                    tableTop.width() - 1, tableTop.height() - 1
            );
            System.out.printf(message);
        }
    }

    public void move() {
        if (position == null) return;
        int x = position.getX();
        int y = position.getY();

        switch (position.getFacing()) {
            case Direction.NORTH -> y++;
            case Direction.EAST -> x++;
            case Direction.SOUTH -> y--;
            case Direction.WEST -> x--;
        }

        if (CommonUtil.isValidPosition(x, y, tableTop)) {
            position.setX(x);
            position.setY(y);
        } else {
            System.out.println("Input Command will cause the robot to fall off. Type 'REPORT' to see the current position.");
        }
    }

    public void left() {
        if (position != null) {
            position.setFacing(position.getFacing().left());
        }
    }

    public void right() {
        if (position != null) {
            position.setFacing(position.getFacing().right());
        }
    }

    public String report() {
        return position != null ? position + "\n" + CommonUtil.getVisual(position, tableTop) : "";
    }

    public boolean isPlaced() {
        return position != null;
    }
}
