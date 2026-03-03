package it.unibo.pps.e3.models.boards.inspectors;

import it.unibo.pps.e3.models.cells.Cell;
import it.unibo.pps.e3.support.Position;

import java.util.ArrayList;
import java.util.List;

public class GenericBoardInspector implements GeneratorBoardInspector, GameBoardInspector {
    private static final Position[] NEIGHBOR_OFFSETS = {
            new Position(-1, -1), new Position(-1, 0), new Position(-1, 1),
            new Position(0, -1), new Position(0, 1),
            new Position(1, -1), new Position(1, 0), new Position(1, 1)
    };

    public boolean isCoordinateOutsideOfBoard(int coordinate, int boardSize) {
        return coordinate < 0 || coordinate >= boardSize;
    }

    public boolean isPositionOutsideOfBoard(Position toCheck, int boardSize) {
        return isCoordinateOutsideOfBoard(toCheck.getX(), boardSize)
                || isCoordinateOutsideOfBoard(toCheck.getY(), boardSize);
    }

    private void inspectCellSurrounding(Cell cell, List<List<Cell>> generatedMatrix) {
        getCellNeighbors(cell, generatedMatrix).forEach((_) -> cell.increaseMineProximityCounter());
    }

    public List<Cell> getCellNeighbors(Cell cell, List<List<Cell>> generatedMatrix) {
        List<Cell> neighbors = new ArrayList<>();
        for (Position offset : NEIGHBOR_OFFSETS) {
            int newRow = cell.getPosition().getX() + offset.getX();
            int newCol = cell.getPosition().getY() + offset.getY();

            if (!isPositionOutsideOfBoard(new Position(newRow, newCol), generatedMatrix.size())) {
                neighbors.add(generatedMatrix.get(newRow).get(newCol));
            }
        }

        return neighbors;
    }

    public List<List<Cell>> inspect(List<List<Cell>> generatedMatrix) {
        for (int row = 0; row < generatedMatrix.size(); row++) {
            for (int col = 0; col < generatedMatrix.get(row).size(); col++) {
                inspectCellSurrounding(generatedMatrix.get(row).get(col), generatedMatrix);
            }
        }

        return generatedMatrix;
    }
}
