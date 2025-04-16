package com.thitiwut.toyrobotcoding.model;

import com.thitiwut.toyrobotcoding.constant.Direction;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Position {
    private int x;
    private int y;
    private Direction facing;

    @Override
    public String toString() {
        return x + "," + y + "," + facing;
    }
}
