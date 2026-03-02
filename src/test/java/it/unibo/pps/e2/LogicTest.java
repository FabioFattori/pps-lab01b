package it.unibo.pps.e2;

import it.unibo.pps.e2.mockery.MockedPositionGenerator;
import it.unibo.pps.e2.piecelogics.KnightPiece;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class LogicTest {
    private Logics logics;
    private final int SIZE = 6;

    @BeforeEach
    public void init() {
        logics = new LogicsImpl(SIZE, new MockedPositionGenerator(), new KnightPiece());
    }

    @Test
    public void testPassedRowAndColumnNeedsToBeInsideTheGameBoard() {
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
        assertTrue(
                logics.hasPawn(
                        logics.getPawnCurrentPosition().getX(),
                        logics.getPawnCurrentPosition().getY()
                )
        );
    }

    @Test
    public void testHasKnightPositionWithWrongPosition() {
        final Position wrongKnightPosition = new Position(2, 2);
        assertFalse(logics.hasKnight(wrongKnightPosition.getX(), wrongKnightPosition.getY()));
    }

    @Test
    public void testHasKnightPositionWithCorrectPosition() {
        assertTrue(
                logics.hasKnight(
                        logics.getKnightCurrentPosition().getX(),
                        logics.getKnightCurrentPosition().getY()
                )
        );
    }

    @Test
    public void testKnightHitCorrectlyThePawnAtHisPosition() {
        final Position pawnPosition = logics.getPawnCurrentPosition();
        assertTrue(logics.hit(pawnPosition.getX(), pawnPosition.getY()));
    }
}
