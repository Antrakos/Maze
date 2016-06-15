import maze.Point;
import maze.calculator.Calculator;
import maze.calculator.impl.CalculatorImpl;
import maze.move.Movable;
import maze.move.impl.Knight;
import maze.parse.ArrayParser;
import maze.parse.impl.IntParser;

import java.util.List;

public class HorseMain {

    public static int[][] compute(Integer[][] board) {
        Movable actor = new Knight();

        ArrayParser<Integer> parser = new IntParser<>();
        Calculator calc = new CalculatorImpl(parser.parse(board), actor);
        List<Point> points = calc.find(parser.getStart());
        points.add(0, parser.getStart());
        int[][] result = new int[points.size()][2];
        int i = 0;
        for (Point point : points) {
            result[i++] = new int[]{point.x, point.y};
        }
        return result;
    }
}
