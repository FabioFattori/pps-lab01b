package it.unibo.pps.e3.models.boards.inspectors;

import it.unibo.pps.e3.models.cells.Cell;
import it.unibo.pps.e3.support.Position;

import java.util.List;

public interface GameBoardInspector {
    List<Cell> getCellNeighbors(Cell cell, List<List<Cell>> generatedMatrix);
    boolean isPositionOutsideOfBoard(Position toCheck, int boardSize);
}
