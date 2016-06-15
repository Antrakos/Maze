package maze.parse.impl;

import maze.BlockType;
import maze.Point;
import maze.parse.ArrayParser;

/**
 * Created by Taras on 09.04.2016.
 */
public class IntParser<T extends Integer> implements ArrayParser<T> {
    private Point start;

    public IntParser() {
    }

    @Override
    public BlockType[][] parse(Integer[][] board) {
        BlockType[][] matrix = new BlockType[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                switch (board[i][j]) {
                    case 0:
                        matrix[i][j] = BlockType.NORMAL;
                        break;
                    case -2:
                        matrix[i][j] = BlockType.START;
                        start = new Point(i, j);
                        break;
                    case -1:
                        matrix[i][j] = BlockType.DISABLED;
                        break;
                    case -3:
                        matrix[i][j] = BlockType.DESTINATION;
                        break;
                }
            }
        }
        return matrix;
    }

    @Override
    public Point getStart() {
        return start;
    }
}
