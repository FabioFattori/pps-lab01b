package it.unibo.pps.e3.models.boards;

import it.unibo.pps.e3.models.boards.inspectors.GeneratorBoardInspector;
import it.unibo.pps.e3.models.cells.BombCell;
import it.unibo.pps.e3.models.cells.Cell;
import it.unibo.pps.e3.models.cells.SafeCell;
import it.unibo.pps.e3.support.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class CoreBoardGenerator implements BoardGenerator {
    protected final GeneratorBoardInspector inspector;

    protected CoreBoardGenerator(GeneratorBoardInspector inspector) {
        this.inspector = inspector;
    }

    public abstract boolean isNextGeneratedCellABomb(int row, int col);

    private List<Cell> generateRow(int boardSize, int row) {
        List<Cell> singleRow = new ArrayList<>();
        for (int col = 0; col < boardSize; col++) {
            final Position cellPosition = new Position(row, col);
            if (isNextGeneratedCellABomb(row, col)) {
                singleRow.add(new BombCell(cellPosition));
                continue;
            }

            singleRow.add(new SafeCell(cellPosition));
        }

        return singleRow;
    }

    public List<List<Cell>> generate(int boardSize) {
        List<List<Cell>> rowsContainer = new ArrayList<>();

        for (int row = 0; row < boardSize; row++) {
            rowsContainer.add(generateRow(boardSize, row));
        }

        return inspector.inspect(rowsContainer);
    }
}
