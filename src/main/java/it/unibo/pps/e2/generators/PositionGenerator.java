package it.unibo.pps.e2.generators;

import it.unibo.pps.e2.Position;

public interface PositionGenerator {
    Position generatePawnPosition(int gridSize);
    Position generateKnightPosition(Position pawnPosition,int gridSize);
}
