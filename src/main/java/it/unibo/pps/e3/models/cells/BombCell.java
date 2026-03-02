package it.unibo.pps.e3.models.cells;

import it.unibo.pps.e3.support.Position;

public class BombCell extends CoreCell {
    public BombCell(Position initialPosition) {
        super(initialPosition);
    }

    @Override
    public boolean isMine() {
        return true;
    }
}
