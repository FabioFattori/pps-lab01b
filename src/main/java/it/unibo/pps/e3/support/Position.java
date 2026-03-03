package it.unibo.pps.e3.support;

import it.unibo.pps.e3.Pair;

public class Position {
    private final Pair<Integer, Integer> coordinates;

    public Position(int x, int y) {
        coordinates = new Pair<>(x, y);
    }

    public Position(Pair<Integer, Integer> pair) {
        this.coordinates = pair;
    }

    public Position copy() {
        return new Position(coordinates.x(), coordinates.y());
    }

    public int getX() {
        return coordinates.x();
    }

    public int getY() {
        return coordinates.y();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Position other = (Position) obj;
        return this.equals(other);
    }

    public boolean equals(Position other) {
        return this.equals(other.getX(), other.getY());
    }

    public boolean equals(int x, int y) {
        return this.getX() == x && this.getY() == y;
    }

    @Override
    public String toString() {
        return String.format("x=%d, y=%d", getX(), getY());
    }
}
