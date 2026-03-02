package it.unibo.pps.e2;

import it.unibo.pps.e2.piecelogics.KnightPiece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class KnightPieceTest {
    private KnightPiece piece;
    private final Position INITIAL_POSITION = new Position(3, 2);

    private static Stream<Arguments> correctMoves() {
        return Stream.of(
                Arguments.of(5, 3),
                Arguments.of(5, 1),
                Arguments.of(1, 3),
                Arguments.of(1, 1),
                Arguments.of(4, 4),
                Arguments.of(4, 0),
                Arguments.of(2, 4),
                Arguments.of(2, 0)
        );
    }

    private static Stream<Arguments> illegalMoves() {
        return Stream.of(
                Arguments.of(3, 0),
                Arguments.of(0, 1),
                Arguments.of(3, 1),
                Arguments.of(2, 2)
        );
    }

    @BeforeEach
    public void init() {
        piece = new KnightPiece(INITIAL_POSITION);
    }

    @Test
    public void testKnightWithoutInitialPositionCannotValidate() {
        piece = new KnightPiece();
        assertThrows(IllegalStateException.class, () -> piece.isMoveValid(INITIAL_POSITION));
        assertThrows(IllegalStateException.class, () -> piece.validateAndUpdateCurrentPosition(INITIAL_POSITION));
    }

    @Test
    public void testKnightWhichHasInitializedManuallyCanValidate() {
        piece = new KnightPiece();
        piece.initializePiecePosition(INITIAL_POSITION);
        assertDoesNotThrow(() -> piece.isMoveValid(INITIAL_POSITION));
        assertDoesNotThrow(() -> piece.validateAndUpdateCurrentPosition(INITIAL_POSITION));
    }

    @Test
    public void testInitialPositionIsSet() {
        assertEquals(INITIAL_POSITION, piece.getCurrentPosition());
    }

    @ParameterizedTest
    @MethodSource("correctMoves")
    public void testCorrectMoveValidation(int x, int y) {
        assertTrue(piece.isMoveValid(new Position(x, y)));
    }

    @ParameterizedTest
    @MethodSource("illegalMoves")
    public void testIllegalMoveValidation(int x, int y) {
        assertFalse(piece.isMoveValid(new Position(x, y)));
    }

    @ParameterizedTest
    @MethodSource("correctMoves")
    public void testCorrectMoveIsValidatedAndSetAsCurrentPosition(int x, int y) {
        final Position correctMove = new Position(x, y);
        assertTrue(piece.validateAndUpdateCurrentPosition(correctMove));
        assertEquals(correctMove, piece.getCurrentPosition());
    }

    @ParameterizedTest
    @MethodSource("illegalMoves")
    public void testIllegalMoveIsValidatedAndNotSetAsCurrentPosition(int x, int y) {
        final Position illegalMove = new Position(x, y);
        assertFalse(piece.validateAndUpdateCurrentPosition(illegalMove));
        assertNotEquals(illegalMove, piece.getCurrentPosition());
    }
}
