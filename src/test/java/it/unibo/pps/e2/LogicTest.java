package it.unibo.pps.e2;

import it.unibo.pps.e2.mockery.MockedPositionGenerator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class LogicTest {
    private Logics logics;
    private final int SIZE = 6;

    @BeforeEach
    public void init() {
        logics = new LogicsImpl(SIZE, new MockedPositionGenerator());
    }

    @Test
    public void testPassedRowAndColumnNeedsToStayInTheGrid() {
        int exceededRow = SIZE + 1;
        int exceededCol = SIZE + 1;
        assertThrows(IndexOutOfBoundsException.class, () -> logics.hit(exceededRow, exceededCol));
        assertThrows(IndexOutOfBoundsException.class, () -> logics.hit(SIZE, exceededCol));
        assertThrows(IndexOutOfBoundsException.class, () -> logics.hit(exceededRow, SIZE));
    }

    @Test
    public void testHasPawnPositionWithWrongPosition() {
        final Position wrongPawnPosition = new Position(2, 2);
        assertFalse(logics.hasPawn(wrongPawnPosition.getX(), wrongPawnPosition.getY()));
    }

    @Test
    public void testHasPawnPositionWithCorrectPosition() {
        assertTrue(logics.hasPawn(logics.getPawnCurrentPosition().getX(), logics.getPawnCurrentPosition().getY()));
    }

    @Test
    public void testHasKnightPositionWithWrongPosition() {
        final Position wrongKnightPosition = new Position(2, 2);
        assertFalse(logics.hasKnight(wrongKnightPosition.getX(), wrongKnightPosition.getY()));
    }

    @Test
    public void testHasKnightPositionWithCorrectPosition() {
        assertTrue(logics.hasKnight(logics.getKnightCurrentPosition().getX(), logics.getKnightCurrentPosition().getY()));
    }

    @Test
    public void testKnightHitCorrectlyThePawnAtHisPosition() {
        final Position pawnPosition = logics.getPawnCurrentPosition();
        assertTrue(logics.hit(pawnPosition.getX(), pawnPosition.getY()));
    }

    @ParameterizedTest
    @CsvSource({
            "5, 3",
            "5, 1",
            "1, 3",
            "1, 1",
            "4, 4",
            "4, 0",
            "2, 4",
            "2, 0",
    })
    public void testKnightCorrectlyMovesInALDirection(int x, int y) {
        final Position expectedNewKnightPosition = new Position(x, y);
        logics.hit(x, y);
        assertEquals(expectedNewKnightPosition, logics.getKnightCurrentPosition());
    }

    @ParameterizedTest
    @CsvSource({"3, 0", "0, 1", "3, 1", "2, 2"})
    public void testKnightOnlyMovesInALDirection(int x, int y) {
        final Position notExpectedPosition = new Position(x, y);
        logics.hit(x, y);
        assertNotEquals(notExpectedPosition, logics.getKnightCurrentPosition());
    }
}
