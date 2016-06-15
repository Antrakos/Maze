package maze.parse;

import maze.BlockType;
import maze.Point;

import java.util.List;

/**
 * Created by Taras on 07.04.2016.
 */
public interface FileParser {
    BlockType[][] parse(String path);

    void write(List<Point> points, String fromPath, String toPath, long scriptTime, long startTime);

    Point getStart();
}
