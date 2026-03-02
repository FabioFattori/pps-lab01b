package it.unibo.pps.e3.models.boards;

import it.unibo.pps.e3.models.cells.Cell;

import java.util.List;

public interface BoardGenerator {
    List<List<Cell>> generate(int boardSize);

    boolean isNextGeneratedCellABomb(int row, int col);
}
