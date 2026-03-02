package it.unibo.pps.es3.cells;

import it.unibo.pps.e3.models.cells.SafeCell;
import it.unibo.pps.e3.support.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class SafeCellTest extends CoreCellTest {
    @BeforeEach
    public void init() {
        cell = new SafeCell(new Position(CELL_X, CELL_Y));
    }

    @Test
    public void testSafeCellCannotBeBombs() {
        assertFalse(cell.isMine());
    }
}
