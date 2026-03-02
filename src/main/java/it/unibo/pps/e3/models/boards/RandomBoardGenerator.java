package it.unibo.pps.e3.models.boards;

import it.unibo.pps.e3.models.boards.inspectors.GenericBoardInspector;

import java.util.Random;

public class RandomBoardGenerator extends CoreBoardGenerator {
    private final Random random;

    public RandomBoardGenerator() {
        super(new GenericBoardInspector());
        random = new Random();
    }

    @Override
    public boolean isNextGeneratedCellABomb(int row, int col) {
        final int randomBound = 7;
        final int bombChoice = 0;

        return random.nextInt(randomBound) == bombChoice;
    }
}
