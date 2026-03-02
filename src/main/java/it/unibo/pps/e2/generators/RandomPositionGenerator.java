package it.unibo.pps.e2.generators;

import it.unibo.pps.e2.Position;

import java.util.Random;

public class RandomPositionGenerator implements PositionGenerator {
    private final Random random = new Random();

    @Override
    public Position generatePawnPosition(int gridSize) {
        return new Position(this.random.nextInt(gridSize), this.random.nextInt(gridSize));
    }

    @Override
    public Position generateKnightPosition(Position pawnPosition, int gridSize) {
        Position pos = new Position(this.random.nextInt(gridSize), this.random.nextInt(gridSize));
        // the recursive call below prevents clash with an existing pawn
        if (pawnPosition != null && pawnPosition.equals(pos)) {
            return generateKnightPosition(pawnPosition, gridSize);
        }

        return pos;
    }
}
