package maze.move;

import maze.Point;

/**
 * Created by Taras on 05.04.2016.
 */
public interface Movable {
    Point[] getMoves(final Point start);

    Point[] move(final Point start, final Point end);
}
