import maze.BlockType;
import maze.Point;
import maze.calculator.Calculator;
import maze.calculator.impl.CalculatorImpl;
import maze.move.Movable;
import maze.move.impl.Knight;
import maze.parse.FileParser;
import maze.parse.impl.ExcelParser;
import maze.parse.impl.TextParser;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.List;

/**
 * @author Taras Zubrei
 */
public class HorseExcelTest {
    @Test
    public void excelTest() {
        Movable actor = new Knight();
        FileParser parser = new ExcelParser();
        long startTime = System.currentTimeMillis();
        BlockType[][] matrix = parser.parse(System.getProperty("user.dir") + "/src/test/resources/horse.xlsx");
        Calculator calc = new CalculatorImpl(matrix, actor);
        long startScriptTime = System.currentTimeMillis();
        List<Point> points = calc.find(parser.getStart());
        long scriptTime = System.currentTimeMillis() - startScriptTime;
        parser.write(points, System.getProperty("user.dir") + "/src/test/resources/horse.xlsx", System.getProperty("user.dir") + "/src/test/resources/result.xlsx", scriptTime, startTime);
    }

    @Test
    public void textTest() {
        Movable actor = new Knight();
        FileParser parser = new TextParser();
        long startTime = System.currentTimeMillis();
        BlockType[][] matrix = parser.parse(Paths.get("./src/test/resources/test.txt").toAbsolutePath().toString());
        Calculator calc = new CalculatorImpl(matrix, actor);
        long startScriptTime = System.currentTimeMillis();
        List<Point> points = calc.find(parser.getStart());
        long scriptTime = System.currentTimeMillis() - startScriptTime;
        parser.write(points, System.getProperty("user.dir") + "/src/test/resources/test.txt", System.getProperty("user.dir") + "/src/test/resources/result.txt", scriptTime, startTime);
    }

    @Test
    public void matrixTest() {
        Movable actor = new Knight();
        BlockType[][] matrix = new BlockType[1000][1000];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = BlockType.NORMAL;
            }
        }
        matrix[matrix.length - 1][matrix[matrix.length - 1].length - 1] = BlockType.DESTINATION;
        Calculator calc = new CalculatorImpl(matrix, actor);
        long startTime = System.currentTimeMillis();
        List<Point> points = calc.find(new Point());
        long scriptTime = System.currentTimeMillis() - startTime;
        System.out.println("Time: " + scriptTime);
    }
}
