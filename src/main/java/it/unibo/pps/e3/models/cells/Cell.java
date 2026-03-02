package it.unibo.pps.e3.models.cells;

import it.unibo.pps.e3.support.Position;

public interface Cell {
    Position getPosition();

    boolean isMine();

    void increaseMineProximityCounter();

    int getMineProximityCounter();
}
