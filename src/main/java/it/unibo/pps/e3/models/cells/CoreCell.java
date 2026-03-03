package it.unibo.pps.e3.models.cells;

import it.unibo.pps.e3.support.Position;

public abstract class CoreCell implements Cell {
    private final Position position;

    private int mineProximityCounter;

    private boolean isVisible;

    private boolean isMarked;

    public CoreCell(Position initialPosition) {
        position = initialPosition;
        mineProximityCounter = 0;
        isMarked = false;
        isVisible = false;
    }

    @Override
    public Position getPosition() {
        return this.position.copy();
    }

    @Override
    public abstract boolean isMine();

    @Override
    public boolean isVisible() {
        return isVisible;
    }

    @Override
    public void toggleVisibility() {
        isVisible = !isVisible;
    }

    @Override
    public boolean isMarked() {
        return isMarked;
    }

    @Override
    public void toggleMarked() {
        isMarked = !isMarked;
    }

    @Override
    public void increaseMineProximityCounter() {
        mineProximityCounter++;
    }

    @Override
    public int getMineProximityCounter() {
        return mineProximityCounter;
    }
}
