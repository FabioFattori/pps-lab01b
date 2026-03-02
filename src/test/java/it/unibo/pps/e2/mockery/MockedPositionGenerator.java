package it.unibo.pps.e2.mockery;

import it.unibo.pps.e2.Position;
import it.unibo.pps.e2.generators.PositionGenerator;

public class MockedPositionGenerator implements PositionGenerator {

    @Override
    public Position generatePawnPosition(int gridSize) {
        return new Position(1, 1);
    }

    @Override
    public Position generateKnightPosition(Position pawnPosition, int gridSize) {
        final int knightOffsetX = 2;
        final int knightOffsetY = 1;
        return new Position(pawnPosition.getX() + knightOffsetX, pawnPosition.getY() + knightOffsetY);
    }
}
