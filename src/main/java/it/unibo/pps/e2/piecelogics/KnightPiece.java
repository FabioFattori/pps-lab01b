package it.unibo.pps.e2.piecelogics;

import it.unibo.pps.e2.Position;

public class KnightPiece implements Piece {
    private Position currentPosition;

    public KnightPiece() {
        currentPosition = null;
    }

    public KnightPiece(Position initialPosition) {
        initializePiecePosition(initialPosition);
    }

    @Override
    public Position getCurrentPosition() {
        return currentPosition;
    }

    @Override
    public void initializePiecePosition(Position initialPosition) {
        this.currentPosition = initialPosition;
    }

    @Override
    public boolean isMoveValid(Position newPossiblePosition) {
        if(currentPosition == null){
            throw new IllegalStateException();
        }

        int x = newPossiblePosition.getX() - this.currentPosition.getX();
        int y = newPossiblePosition.getY() - this.currentPosition.getY();

        return x != 0 && y != 0 && Math.abs(x) + Math.abs(y) == 3;
    }

    @Override
    public boolean validateAndUpdateCurrentPosition(Position newPossiblePosition) {
        final boolean isMoveValid = isMoveValid(newPossiblePosition);
        if (isMoveValid) {
            this.currentPosition = newPossiblePosition;
        }

        return isMoveValid;
    }
}
