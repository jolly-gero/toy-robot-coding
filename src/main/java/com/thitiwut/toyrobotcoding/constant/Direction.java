package com.thitiwut.toyrobotcoding.constant;

public enum Direction {
    // ordering in 90 degree clockwise
    NORTH, EAST, SOUTH, WEST;

    public Direction right() {
        // shift 90 degree clockwise
        return values()[(this.ordinal() + 1) % 4];
    }

    public Direction left() {
        // shift 270 degree clockwise
        return values()[(this.ordinal() + 3) % 4];
    }

}