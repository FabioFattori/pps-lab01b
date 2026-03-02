package it.unibo.pps.e2.piecelogics;

import it.unibo.pps.e2.Position;

public interface Piece {
    Position getCurrentPosition();

    void initializePiecePosition(Position initialPosition);

    boolean isMoveValid(Position newPossiblePosition);

    boolean validateAndUpdateCurrentPosition(Position newPossiblePosition);
}
