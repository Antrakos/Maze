package helpers;

import java.awt.*;
import java.util.ArrayList;

class BoardGenerator {

    static Integer[][] generate(int size, Point pStart, Point pFinish) {
        return getBoard(size, pStart, pFinish);
    }

    static Integer[][] generateWithObstacles(int size, Point pStart, Point pFinish, ArrayList<Rectangle> rects) {
        Integer[][] board = getBoard(size, pStart, pFinish);
        for (Rectangle rect : rects) {
            for (int i = rect.x; i < rect.x + rect.width; i++) {
                for (int j = rect.y; j < rect.y + rect.height; j++) {
                    board[i][j] = -1;
                }
            }
        }
        return board;
    }

    private static Integer[][] getBoard(int size, Point pStart, Point pFinish) {
        Integer[][] board = new Integer[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = 0;
            }
        }
        board[pStart.x][pStart.y] = -2;
        board[pFinish.x][pFinish.y] = -3;
        return board;
    }
}
