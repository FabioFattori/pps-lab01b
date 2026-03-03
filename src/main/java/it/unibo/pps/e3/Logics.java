package it.unibo.pps.e3;

import it.unibo.pps.e3.support.Position;

public interface Logics {
    String MARKED_FLAG = "M";
    String BOMB_FLAG = "*";
    String EMPTY_FLAG = "";
    boolean pickPosition(Position position);

    boolean hasPlayerWon();

    String getDisplayValue(Position position);
}
