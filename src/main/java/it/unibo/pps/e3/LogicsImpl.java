package it.unibo.pps.e3;

import it.unibo.pps.e3.models.boards.Board;
import it.unibo.pps.e3.models.cells.Cell;
import it.unibo.pps.e3.support.Position;

public class LogicsImpl implements Logics {
    private final Board board;

    public LogicsImpl(Board board) {
        this.board = board;
    }

    @Override
    public boolean pickPosition(Position position) {
        return this.board.pickCell(position).isMine();
    }

    @Override
    public boolean hasPlayerWon() {
        return board.hasPlayerWon();
    }

    @Override
    public String getDisplayValue(Position position) {
        final Cell cell = board.getCellAtPosition(position);

        if (cell.isMarked() && !cell.isVisible()) {
            return MARKED_FLAG;
        }

        if (!cell.isVisible()) {
            return EMPTY_FLAG;
        }

        if (cell.isMine()) {
            return BOMB_FLAG;
        }

        return String.valueOf(cell.getMineProximityCounter());
    }

    @Override
    public void toggleMarkPosition(Position position) {
        board.toggleMarkPosition(position);
    }

    @Override
    public void displayAllMinesAndDisableUserInteraction() {
        board.makeAllTheBombsVisible();
    }
}
