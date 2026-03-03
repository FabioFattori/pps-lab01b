package it.unibo.pps.es3;

import it.unibo.pps.e3.Logics;
import it.unibo.pps.e3.LogicsImpl;
import it.unibo.pps.e3.models.boards.BoardGenerator;
import it.unibo.pps.e3.models.boards.GameBoard;
import it.unibo.pps.e3.models.boards.inspectors.GenericBoardInspector;
import it.unibo.pps.e3.models.cells.Cell;
import it.unibo.pps.es3.mockery.MockedBoardGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LogicTest {
    private Logics logic;
    private GameBoard board;

    private Cell getFirstColumnFirstRowCell() {
        return board.getCells().getFirst().get(1);
    }

    private Cell getSecondColumnFirstRowCell() {
        return board.getCells().getFirst().get(4);
    }

    private Cell getBombCell() {
        return board.getCells().getFirst().getFirst();
    }

    @BeforeEach
    public void init() {
        BoardGenerator generator = new MockedBoardGenerator();
        GenericBoardInspector inspector = new GenericBoardInspector();
        int SIZE = 6;
        board = new GameBoard(SIZE, generator, inspector);
        logic = new LogicsImpl(board);
    }

    @Test
    public void testPickPositionIsAMine() {
        final Cell bomb = getBombCell();
        assertTrue(logic.pickPosition(bomb.getPosition()));
    }

    @Test
    public void testAtInitializationPlayerHasWonMustBeFalse() {
        assertFalse(logic.hasPlayerWon());
    }

    @Test
    public void testAfterAMovePlayerHasNotWon() {
        final Cell firstColumnCell = getFirstColumnFirstRowCell();
        logic.pickPosition(firstColumnCell.getPosition());
        assertFalse(logic.hasPlayerWon());
    }

    @Test
    public void testHasPlayerWon() {
        final Cell firstColumnCell = getFirstColumnFirstRowCell();
        final Cell secondColumnCell = getSecondColumnFirstRowCell();
        logic.pickPosition(firstColumnCell.getPosition());
        logic.pickPosition(secondColumnCell.getPosition());
        assertTrue(logic.hasPlayerWon());
    }

    @Test
    public void testGetCounterCellDisplayValue() {
        Cell firstColumnCell = getFirstColumnFirstRowCell();
        logic.pickPosition(firstColumnCell.getPosition());
        final String expectedDisplayValue = String.valueOf(getFirstColumnFirstRowCell().getMineProximityCounter());
        assertEquals(expectedDisplayValue, logic.getDisplayValue(firstColumnCell.getPosition()));
    }

    @Test
    public void testGetBombCellDisplayValue() {
        final Cell bomb = getBombCell();
        logic.pickPosition(bomb.getPosition());
        final String expectedDisplayValue = Logics.BOMB_FLAG;
        assertEquals(expectedDisplayValue, logic.getDisplayValue(bomb.getPosition()));
    }

    @Test
    public void testGetMarkedCellDisplayValue() {
        final Cell bomb = getBombCell();
        bomb.toggleMarked();
        final String expectedDisplayValue = Logics.MARKED_FLAG;
        assertEquals(expectedDisplayValue, logic.getDisplayValue(bomb.getPosition()));
    }

    @Test
    public void testGetHiddenCellDisplayValue() {
        final Cell bomb = getBombCell();
        final String expectedDisplayValue = Logics.EMPTY_FLAG;
        assertEquals(expectedDisplayValue, logic.getDisplayValue(bomb.getPosition()));
    }
}
