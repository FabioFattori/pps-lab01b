package it.unibo.pps.es3.mockery;

import it.unibo.pps.e3.models.boards.CoreBoardGenerator;
import it.unibo.pps.e3.models.boards.inspectors.GenericBoardInspector;

public class MockedBoardGenerator extends CoreBoardGenerator {
    public MockedBoardGenerator() {
        super(new GenericBoardInspector());
    }

    @Override
    public boolean isNextGeneratedCellABomb(int row, int col) {
        return col % 3 == 0;
    }
}
