package it.unibo.pps.es3;

import it.unibo.pps.e3.models.boards.BoardGenerator;
import it.unibo.pps.e3.models.boards.enums.BoardState;
import it.unibo.pps.e3.models.boards.inspectors.GenericBoardInspector;
import it.unibo.pps.e3.models.boards.GameBoard;
import it.unibo.pps.e3.models.cells.BombCell;
import it.unibo.pps.e3.models.cells.Cell;
import it.unibo.pps.e3.models.cells.SafeCell;
import it.unibo.pps.e3.support.Position;
import it.unibo.pps.es3.mockery.MockedBoardGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class GameBoardTest {
    private GameBoard board;
    private final int BOARD_SIZE = 7;
    private BoardGenerator generator;
    private GenericBoardInspector inspector;
    private List<List<Cell>> cells;
    private final Position OUTSIDE_BOARD_POSITION = new Position(-1, -1);

    @BeforeEach
    public void init() {
        this.generator = new MockedBoardGenerator();
        this.inspector = new GenericBoardInspector();
        board = new GameBoard(BOARD_SIZE, generator, inspector);
        cells = board.getCells();
    }

    private Cell getSafeCell() {
        return cells.getFirst().get(1);
    }

    private Cell getBombCell() {
        return cells.getFirst().getFirst();
    }

    @Test
    public void testGetBoardReturnsActualSize() {
        assertEquals(BOARD_SIZE, board.getBoardSize());
    }

    @Test
    public void getTestUponCreationTheCellsArePopulated() {
        assertEquals(BOARD_SIZE, cells.size());
        for (int i = 0; i < BOARD_SIZE; i++) {
            assertEquals(BOARD_SIZE, cells.get(i).size());
        }
    }

    @Test
    public void testUponCreationTheCellsCountersAreFilledCorrectly() {
        final int numberOfBombsThatANeighborHasNearAtLeast = 2;

        for (int row = 0; row < cells.size(); row++) {
            for (int col = 0; col < cells.get(row).size(); col++) {
                if (generator.isNextGeneratedCellABomb(row, col)) {
                    final Cell bombCell = cells.get(row).get(col);
                    List<Cell> neighbors = inspector.getCellNeighbors(bombCell, cells);
                    neighbors.forEach(
                            (cell ->
                                    assertTrue(cell.getMineProximityCounter() >= numberOfBombsThatANeighborHasNearAtLeast)
                            )
                    );
                    assertTrue(cells.get(row).get(col).isMine());
                }
            }
        }
    }

    @Test
    public void testPickCellWithSafeCellReturnsTheCorrectCell() {
        final Cell expectedSell = getSafeCell();
        final Cell result = board.pickCell(expectedSell.getPosition());
        assertEquals(SafeCell.class, result.getClass());
        assertEquals(expectedSell, result);
        assertTrue(result.isVisible());
    }

    @Test
    public void testPickCellWithBombCellReturnsTheCorrectCell() {
        final Cell expectedSell = getBombCell();
        final Cell result = board.pickCell(expectedSell.getPosition());
        assertEquals(BombCell.class, result.getClass());
        assertEquals(expectedSell, result);
        assertTrue(result.isVisible());
    }

    @Test
    public void testPickWithPositionOutsideOfBoardThrowsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> board.pickCell(OUTSIDE_BOARD_POSITION));
        assertEquals(BoardState.InvalidState, board.getCurrentState());
    }

    private void assertVisibleRecursively(Cell cell, Set<Cell> visited) {
        if (visited.contains(cell)) {
            return;
        }

        visited.add(cell);
        assertTrue(cell.isVisible());

        List<Cell> neighbors = inspector.getCellNeighbors(cell, cells).stream()
                .filter(neighbor -> !neighbor.isMine()).toList();

        for (Cell neighbor : neighbors) {
            assertVisibleRecursively(neighbor, visited);
        }
    }

    @Test
    public void testPickSafeCellWillToggleNeighborVisibility() {
        final Cell pickedCell = getSafeCell();
        board.pickCell(pickedCell.getPosition());
        assertVisibleRecursively(pickedCell, new HashSet<>());
    }

    @Test
    public void testPickWithAlreadyVisibleCellWillIgnorePick() {
        final Cell pickedCell = getSafeCell();
        board.pickCell(pickedCell.getPosition());
        board.pickCell(pickedCell.getPosition());
        assertVisibleRecursively(pickedCell, new HashSet<>());
    }

    @Test
    public void testBoardAtInitializationMustBeReady() {
        assertEquals(BoardState.Ready, board.getCurrentState());
    }

    @Test
    public void testUponShowingAllTheBombsCurrentStateMustChangeProperly() {
        board.makeAllTheBombsVisible();
        assertEquals(BoardState.MinesAreVisible, board.getCurrentState());
    }

    @Test
    public void testInvalidStateBoardCanBeInitialized() {
        assertThrows(IndexOutOfBoundsException.class, () -> board.pickCell(OUTSIDE_BOARD_POSITION));
        board.initializeBoard(board.getBoardSize(), generator, inspector);
        assertEquals(BoardState.Ready, board.getCurrentState());
    }

    @Test
    public void testAtInitializationPlayerHasWonMustBeFalse() {
        assertFalse(board.hasPlayerWon());
    }

    @Test
    public void testAfterAMovePlayerHasNotWon() {
        final Cell firstColumnCell = getBombCell();
        board.pickCell(firstColumnCell.getPosition());
        assertFalse(board.hasPlayerWon());
    }

    @Test
    public void testHasPlayerWon() {
        final Cell firstColumnCell = getSafeCell();
        final Cell secondColumnCell = board.getCells().getFirst().get(4);
        board.pickCell(firstColumnCell.getPosition());
        board.pickCell(secondColumnCell.getPosition());
        assertTrue(board.hasPlayerWon());
    }
}
