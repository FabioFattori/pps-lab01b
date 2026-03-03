package it.unibo.pps.e3.models.boards;

import it.unibo.pps.e3.models.boards.enums.BoardState;
import it.unibo.pps.e3.models.boards.inspectors.GameBoardInspector;
import it.unibo.pps.e3.models.cells.Cell;
import it.unibo.pps.e3.support.Position;

import java.util.List;

public class GameBoard implements Board {
    private int boardSize;
    private List<List<Cell>> cellMatrix;
    private GameBoardInspector inspector;
    private BoardState currentState;

    public GameBoard(int boardSize, BoardGenerator generator, GameBoardInspector inspector) {
        initializeBoard(boardSize, generator, inspector);
    }

    @Override
    public Cell pickCell(Position pickedCellCoordinates) {
        if (currentState != BoardState.Ready) {
            throw new IllegalStateException();
        }

        this.visitNeighborsAndMakeThemVisible(pickedCellCoordinates);
        return getCellAtPosition(pickedCellCoordinates);
    }

    @Override
    public Cell getCellAtPosition(Position position) {
        if (inspector.isPositionOutsideOfBoard(position, boardSize)) {
            currentState = BoardState.InvalidState;
            throw new IndexOutOfBoundsException();
        }

        return cellMatrix.get(position.getX()).get(position.getY());
    }

    @Override
    public void makeAllTheBombsVisible() {
        final List<List<Cell>> cells = getCells();

        for (List<Cell> row : cells) {
            row.stream().filter(Cell::isMine).filter(cell -> !cell.isVisible()).forEach(Cell::toggleVisibility);
        }

        currentState = BoardState.MinesAreVisible;
    }

    @Override
    public BoardState getCurrentState() {
        return currentState;
    }

    @Override
    public boolean hasPlayerWon() {
        return getCells().stream()
                .filter(row ->
                        !row.stream()
                                .filter(cell -> !cell.isVisible() && !cell.isMine())
                                .toList().isEmpty()
                ).toList().isEmpty();
    }

    @Override
    public void toggleMarkPosition(Position position) {
        getCellAtPosition(position).toggleMarked();
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
        this.currentState = BoardState.Ready;
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
