package it.unibo.pps.e3.models.boards;

import it.unibo.pps.e3.models.boards.inspectors.GameBoardInspector;
import it.unibo.pps.e3.models.cells.Cell;
import it.unibo.pps.e3.support.Position;

import java.util.List;

public interface Board {
    Cell pickCell(Position pickedCellCoordinates);

    void initializeBoard(int boardSize, BoardGenerator generator, GameBoardInspector inspector);

    int getBoardSize();

    List<List<Cell>> getCells();

    Cell getCellAtPosition(Position position);
}
