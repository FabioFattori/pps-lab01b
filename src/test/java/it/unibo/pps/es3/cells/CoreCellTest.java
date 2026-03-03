package it.unibo.pps.es3.cells;

import it.unibo.pps.e3.models.cells.Cell;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class CoreCellTest {
    protected Cell cell;
    protected final int CELL_X = 1;
    protected final int CELL_Y = 1;

    @Test
    public void testGetPosition() {
        assertEquals(CELL_X, cell.getPosition().getX());
        assertEquals(CELL_Y, cell.getPosition().getY());
    }

    @Test
    public void testCreatedCellIsHidden() {
        assertFalse(cell.isVisible());
    }

    @Test
    public void testToggleCellVisibility() {
        cell.toggleVisibility();
        assertTrue(cell.isVisible());
        cell.toggleVisibility();
        assertFalse(cell.isVisible());
    }

    @Test
    public void testCreatedCellIsNotMarked() {
        assertFalse(cell.isMarked());
    }

    @Test
    public void testToggleCellMark() {
        cell.toggleMarked();
        assertTrue(cell.isMarked());
        cell.toggleMarked();
        assertFalse(cell.isMarked());
    }

    @Test
    public void testIncreasingMineProximityCounterActuallyIncreasesIt() {
        final int expectedCounterValue = 2;
        cell.increaseMineProximityCounter();
        cell.increaseMineProximityCounter();
        assertEquals(expectedCounterValue, cell.getMineProximityCounter());
    }
}
