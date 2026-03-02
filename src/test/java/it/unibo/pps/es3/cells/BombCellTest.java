package it.unibo.pps.es3.cells;

import it.unibo.pps.e3.models.cells.BombCell;
import it.unibo.pps.e3.support.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BombCellTest extends CoreCellTest {
    @BeforeEach
    public void init() {
        cell = new BombCell(new Position(CELL_X, CELL_Y));
    }

    @Test
    public void testBombCellsAreActuallyBombs() {
        assertTrue(cell.isMine());
    }
}
