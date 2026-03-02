package it.unibo.pps.e2;

import it.unibo.pps.e2.generators.PositionGenerator;

import java.util.*;

public class LogicsImpl implements Logics {

    private final Position pawn;
    private Position knight;
    private final int size;

    public LogicsImpl(int size, PositionGenerator generator) {
        this.size = size;
        this.pawn = generator.generatePawnPosition(size);
        this.knight = generator.generateKnightPosition(this.pawn, size);
    }

    @Override
    public boolean hit(int row, int col) {
        if (row < 0 || col < 0 || row >= this.size || col >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        // Below a compact way to express allowed moves for the knight
        int x = row - this.knight.getX();
        int y = col - this.knight.getY();
        if (x != 0 && y != 0 && Math.abs(x) + Math.abs(y) == 3) {
            this.knight = new Position(row, col);
            return this.pawn.equals(this.knight);
        }
        return false;
    }

    @Override
    public boolean hasKnight(int row, int col) {
        return this.knight.equals(row, col);
    }

    @Override
    public boolean hasPawn(int row, int col) {
        return this.pawn.equals(row, col);
    }

    @Override
    public Position getPawnCurrentPosition() {
        return this.pawn.copy();
    }

    @Override
    public Position getKnightCurrentPosition() {
        return this.knight.copy();
    }
}
