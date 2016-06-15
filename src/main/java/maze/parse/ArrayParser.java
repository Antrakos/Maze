package maze.parse;

import maze.BlockType;
import maze.Point;

/**
 * @author Taras Zubrei
 */
public interface ArrayParser<T> {
    BlockType[][] parse(T[][] data);
    Point getStart();
}
