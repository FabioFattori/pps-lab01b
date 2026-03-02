package it.unibo.pps.e2;

import it.unibo.pps.e2.generators.PositionGenerator;
import it.unibo.pps.e2.piecelogics.Piece;

public class LogicsImpl implements Logics {

    private final Position pawn;
    private final Piece movingPiece;
    private final int size;

    public LogicsImpl(int size, PositionGenerator generator, Piece movingPiece) {
        this.size = size;
        this.pawn = generator.generatePawnPosition(size);
        this.movingPiece = movingPiece;
        this.movingPiece.initializePiecePosition(generator.generateKnightPosition(this.pawn, size));
    }

    private boolean areCoordinatesOutsideGameBoard(int row, int col) {
        return row < 0 || col < 0 || row >= this.size || col >= this.size;
    }

    @Override
    public boolean hit(int row, int col) {
        if (areCoordinatesOutsideGameBoard(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        // Below a compact way to express allowed moves for the knight
        if (movingPiece.validateAndUpdateCurrentPosition(new Position(row, col))) {
            return this.pawn.equals(this.movingPiece.getCurrentPosition());
        }
        return false;
    }

    @Override
    public boolean hasKnight(int row, int col) {
        return this.movingPiece.getCurrentPosition().equals(row, col);
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
        return this.movingPiece.getCurrentPosition().copy();
    }
}
