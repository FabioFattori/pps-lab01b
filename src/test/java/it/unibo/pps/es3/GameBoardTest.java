package it.unibo.pps.es3;

import it.unibo.pps.e3.models.boards.BoardGenerator;
import it.unibo.pps.e3.models.boards.inspectors.GenericBoardInspector;
import it.unibo.pps.e3.models.boards.GameBoard;
import it.unibo.pps.e3.models.cells.Cell;
import it.unibo.pps.es3.mockery.MockedBoardGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameBoardTest {
    private GameBoard board;
    private final int BOARD_SIZE = 7;
    private BoardGenerator generator;
    private GenericBoardInspector inspector;

    @BeforeEach
    public void init() {
        this.generator = new MockedBoardGenerator();
        this.inspector = new GenericBoardInspector();
        board = new GameBoard(BOARD_SIZE, generator, inspector);
    }

    @Test
    public void testGetBoardReturnsActualSize() {
        assertEquals(BOARD_SIZE, board.getBoardSize());
    }

    @Test
    public void getTestUponCreationTheCellsArePopulated() {
        final List<List<Cell>> cellMatrix = board.getCells();
        assertEquals(BOARD_SIZE, cellMatrix.size());
        for (int i = 0; i < BOARD_SIZE; i++) {
            assertEquals(BOARD_SIZE, cellMatrix.get(i).size());
        }
    }

    @Test
    public void testUponCreationTheCellsCountersAreFilledCorrectly() {
        final List<List<Cell>> cellMatrix = board.getCells();
        final int numberOfBombsThatANeighborHasNearAtLeast = 2;

        for (int row = 0; row < cellMatrix.size(); row++) {
            for (int col = 0; col < cellMatrix.get(row).size(); col++) {
                if (generator.isNextGeneratedCellABomb(row, col)) {
                    final Cell bombCell = cellMatrix.get(row).get(col);
                    List<Cell> neighbors = inspector.getCellNeighbors(bombCell, cellMatrix);
                    neighbors.forEach((cell -> assertTrue(cell.getMineProximityCounter() >= numberOfBombsThatANeighborHasNearAtLeast)));
                    assertTrue(cellMatrix.get(row).get(col).isMine());
                }
            }
        }
    }
}
