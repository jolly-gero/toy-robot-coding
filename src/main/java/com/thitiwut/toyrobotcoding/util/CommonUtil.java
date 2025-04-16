package com.thitiwut.toyrobotcoding.util;

import com.thitiwut.toyrobotcoding.model.Position;
import com.thitiwut.toyrobotcoding.model.TableTop;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CommonUtil {
    public static boolean isValidPosition(int x, int y, TableTop tableTop) {
        return x >= 0 && x < tableTop.width() && y >= 0 && y < tableTop.height();
    }

    public static String getVisual(Position pos, TableTop tableTop) {
        StringBuilder sb = new StringBuilder();
        for (int y = tableTop.height() - 1; y >= 0; y--) {
            for (int x = 0; x < tableTop.width(); x++) {
                if (pos != null && pos.getX() == x && pos.getY() == y) {
                    sb.append(switch (pos.getFacing()) {
                        case NORTH -> "^ ";
                        case EAST -> "> ";
                        case SOUTH -> "v ";
                        case WEST -> "< ";
                    });
                } else {
                    sb.append(". ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
