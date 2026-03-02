package it.unibo.pps.e3.models.cells;

public interface Cell {
    int getX();

    int getY();

    boolean isMine();

    void increaseMineProximityCounter();

    int getMineProximityCounter();
}
