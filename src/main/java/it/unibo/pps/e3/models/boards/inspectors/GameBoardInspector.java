package it.unibo.pps.e3.models.boards.inspectors;

import it.unibo.pps.e3.models.cells.Cell;
import it.unibo.pps.e3.support.Position;

import java.util.List;

public interface GameBoardInspector {
    List<Cell> getCellNeighbors(Cell cell, List<List<Cell>> generatedMatrix);
    List<Cell> getPositionNeighbors(Position position, List<List<Cell>> generatedMatrix);
}
