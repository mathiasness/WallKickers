package no.uib.inf101.sem2.wallKickers.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.wallKickers.model.gameBoard.GameBoard;
import no.uib.inf101.sem2.wallKickers.model.player.Position;

public class TestPixelToCellPosition {
    
    @Test
    public void testPixelIsOnGrid() {
        CellData area = new CellData(10, 10, 10, 10);
        GameBoard gd = new GameBoard(1,1);
        PixelToCellPosition pixelToCP = new PixelToCellPosition(area, gd);
        
        assertTrue(pixelToCP.pixelIsOnGrid(new Position(15, 15)));
        assertTrue(pixelToCP.pixelIsOnGrid(new Position(10, 10)));
        assertFalse(pixelToCP.pixelIsOnGrid(new Position(8, 12)));
        assertFalse(pixelToCP.pixelIsOnGrid(new Position(30, 12)));
        assertFalse(pixelToCP.pixelIsOnGrid(new Position(15, 40)));
    }

    @Test
    public void testPixelToCellPosition() {
        CellData area = new CellData(50, 50, 200, 200);

        GameBoard gd = new GameBoard(1,1);
        PixelToCellPosition pixelToCP = new PixelToCellPosition(area, gd);

        GameBoard gd2 = new GameBoard(5,5);
        PixelToCellPosition pixelToCP2 = new PixelToCellPosition(area, gd2);

        assertEquals(new CellPosition(0, 0), pixelToCP.getCellPositionOfPixel(new Position(100,100)));
        assertEquals(new CellPosition(4, 4), pixelToCP2.getCellPositionOfPixel(new Position(240, 240)));
        assertEquals(new CellPosition(3, 1), pixelToCP2.getCellPositionOfPixel(new Position(100, 200)));
    }
}
