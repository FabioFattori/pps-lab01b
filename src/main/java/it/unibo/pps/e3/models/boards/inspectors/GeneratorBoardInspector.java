package it.unibo.pps.e3.models.boards.inspectors;

import it.unibo.pps.e3.models.cells.Cell;

import java.util.List;

public interface GeneratorBoardInspector {
    List<List<Cell>> inspect(List<List<Cell>> generatedMatrix);
}
