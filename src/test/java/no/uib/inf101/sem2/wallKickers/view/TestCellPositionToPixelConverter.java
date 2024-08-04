package no.uib.inf101.sem2.wallKickers.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.awt.geom.Rectangle2D;
import org.junit.jupiter.api.Test;
import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.wallKickers.model.gameBoard.GameBoard;

public class TestCellPositionToPixelConverter {
    
    @Test
    public void sanityTest() {
        GridDimension gd = new GameBoard(3, 4);
        CellPositionToPixelConverter converter = new CellPositionToPixelConverter(
            new Rectangle2D.Double(29, 29, 340, 240), gd
        );
        Rectangle2D expected = new Rectangle2D.Double(199, 109, 85, 80);
        assertEquals(expected, converter.getBoundsForCell(new CellPosition(1, 2)));
    }   
}
