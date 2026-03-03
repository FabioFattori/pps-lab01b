package it.unibo.pps.e3.models.boards;

import it.unibo.pps.e3.models.boards.inspectors.GameBoardInspector;
import it.unibo.pps.e3.models.cells.Cell;
import it.unibo.pps.e3.support.Position;

import java.util.List;

public class GameBoard implements Board {
    private int boardSize;
    private List<List<Cell>> cellMatrix;
    private GameBoardInspector inspector;

    public GameBoard(int boardSize, BoardGenerator generator, GameBoardInspector inspector) {
        initializeBoard(boardSize, generator, inspector);
    }

    @Override
    public Cell pickCell(Position pickedCellCoordinates) {
        this.visitNeighborsAndMakeThemVisible(pickedCellCoordinates);
        return getCellAtPosition(pickedCellCoordinates);
    }

    @Override
    public Cell getCellAtPosition(Position position) {
        if (inspector.isPositionOutsideOfBoard(position, boardSize)) {
            throw new IndexOutOfBoundsException();
        }

        return cellMatrix.get(position.getX()).get(position.getY());
    }

    private void visitNeighborsAndMakeThemVisible(Position pickedCellCoordinates) {
        final Cell pickedCell = getCellAtPosition(pickedCellCoordinates);
        if (pickedCell.isVisible()) {
            return;
        }
        pickedCell.toggleVisibility();
        if (pickedCell.isMine()) {
            return;
        }

        inspector.getCellNeighbors(pickedCell, cellMatrix).stream()
                .filter((cell -> !cell.isMine() && !cell.isVisible()))
                .forEach((neighbour) -> visitNeighborsAndMakeThemVisible(neighbour.getPosition()));
    }

    @Override
    public void initializeBoard(int boardSize, BoardGenerator generator, GameBoardInspector inspector) {
        this.boardSize = boardSize;
        this.inspector = inspector;
        this.cellMatrix = generator.generate(boardSize);
    }

    @Override
    public int getBoardSize() {
        return boardSize;
    }

    @Override
    public List<List<Cell>> getCells() {
        return this.cellMatrix.stream().toList();
    }
}
