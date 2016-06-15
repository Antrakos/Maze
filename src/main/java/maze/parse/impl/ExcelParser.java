package maze.parse.impl;

import maze.BlockType;
import maze.Point;
import maze.parse.FileParser;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Taras on 07.04.2016.
 */
public class ExcelParser implements FileParser {
    private Point start;

    public ExcelParser() {
    }

    @Override
    public BlockType[][] parse(String path) {
        ArrayList<List<BlockType>> matrix = new ArrayList<>();
        final short border = 2;
        try {
            Sheet sheet = WorkbookFactory.create(new File(path)).getSheetAt(0);
            boolean startTopFlag = false;
            boolean startLeftFlag = false;
            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (cell.getCellStyle().getBorderTop() == border)
                        startTopFlag = true;
                    if (cell.getCellStyle().getBorderLeft() == border) {
                        startLeftFlag = true;
                        if (startTopFlag)
                            matrix.add(new ArrayList<>());
                    }
                    if (startLeftFlag && startTopFlag) {
                        if (Objects.equals(cell.getStringCellValue(), "f"))
                            matrix.get(matrix.size() - 1).add(BlockType.DESTINATION);
                        else if (Objects.equals(cell.getStringCellValue(), "s")) {
                            matrix.get(matrix.size() - 1).add(BlockType.START);
                            start = new Point(matrix.get(matrix.size() - 1).size() - 1, matrix.size() - 1);
                        } else if (cell.getCellStyle().getFillBackgroundColorColor() != null)
                            matrix.get(matrix.size() - 1).add(BlockType.DISABLED);
                        else
                            matrix.get(matrix.size() - 1).add(BlockType.NORMAL);
                    }
                    if (cell.getCellStyle().getBorderRight() == border) {
                        startLeftFlag = false;
                        if (cell.getCellStyle().getBorderBottom() == border)
                            startTopFlag = false;
                    }
                }
            }
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
        BlockType[][] result = new BlockType[matrix.size()][matrix.get(0).size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = matrix.get(i).toArray(new BlockType[matrix.get(i).size()]);
        }
        return result;
    }

    @Override
    public Point getStart() {
        return start;
    }

    private Point getStart(Sheet sheet) {
        int i = 0;
        int j;
        for (Row row : sheet) {
            j = 0;
            i++;
            for (Cell cell : row) {
                j++;
                if (cell.getCellStyle().getBorderTop() == 2 && cell.getCellStyle().getBorderLeft() == 2)
                    return new Point(i, j);
            }
        }
        return new Point();
    }

    @Override
    public void write(List<Point> points, String fromPath, String toPath, long scriptTime, long startTime) {
        try {
            Workbook wb = WorkbookFactory.create(new File(fromPath));
            Sheet sheet = wb.getSheetAt(0);
            Point start = getStart(sheet);
            for (int i = 0; i < points.size(); i++)
                sheet.getRow(points.get(i).x + start.x).getCell(points.get(i).y + start.y).setCellValue(i + 1);
            sheet.getRow(1).getCell(41).setCellValue(scriptTime);
            sheet.getRow(1).getCell(17).setCellValue(System.currentTimeMillis() - startTime);
            wb.write(new FileOutputStream(toPath));
            System.out.println("Moves: " + (points.size() + 1));
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}
