package it.unibo.pps.es3.support;

import it.unibo.pps.e2.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionTest {

    @ParameterizedTest
    @CsvSource({"1, 0", "1, 1", "2, 3", "4, -1", "6, 1", "10000, -100"})
    public void testPositionCanBeCreated(int x, int y) {
        assertDoesNotThrow(() -> new Position(x, y));
    }

    @Test
    public void testCopyIsEqualToCopiedPosition() {
        int genericX = 1;
        int genericY = 2;
        final Position toCopy = new Position(genericX, genericY);
        assertEquals(toCopy.copy(), toCopy);
    }
}
