package it.unibo.pps.e3.models;

import it.unibo.pps.e3.models.cells.Cell;
import it.unibo.pps.e3.support.Position;

public interface Board {
    Cell pickCell(Position pickedCellCoordinates);

    void initializeBoard(int boardSize);

    int getBoardSize();
}
