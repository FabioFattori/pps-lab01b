package it.unibo.pps.e3.models.cells;

import it.unibo.pps.e3.support.Position;

public abstract class CoreCell implements Cell {
    private final Position position;
    private int mineProximityCounter;

    public CoreCell(Position initialPosition) {
        position = initialPosition;
        mineProximityCounter = 0;
    }

    @Override
    public Position getPosition() {
        return this.position.copy();
    }

    @Override
    public abstract boolean isMine();

    @Override
    public void increaseMineProximityCounter() {
        mineProximityCounter++;
    }

    @Override
    public int getMineProximityCounter() {
        return mineProximityCounter;
    }
}
