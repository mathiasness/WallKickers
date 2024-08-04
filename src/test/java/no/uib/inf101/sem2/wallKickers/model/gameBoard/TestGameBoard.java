package no.uib.inf101.sem2.wallKickers.model.gameBoard;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.wallKickers.model.CellData;
import no.uib.inf101.sem2.wallKickers.model.obstacles.Wall;

public class TestGameBoard {
    
    @Test
    public void testGenerateObstacle() {
        GameBoard testBoard = new GameBoard(10, 10);
        testBoard.generateObstacle(new CellPosition(3, 3), new Wall(3));

        List<GridCell<Character>> boardList = new ArrayList<>();
        for (GridCell<Character> gc : testBoard) {
            if (gc.value() != '-') {
                boardList.add(gc);
            }
        }

        assertEquals(3, boardList.size());
        assertTrue(boardList.contains(new GridCell<Character>(new CellPosition(3, 3), 'W')));
        assertTrue(boardList.contains(new GridCell<Character>(new CellPosition(4, 3), 'W')));
        assertTrue(boardList.contains(new GridCell<Character>(new CellPosition(5, 3), 'W')));
    }

    @Test
    public void testMoveBoard() {
        GameBoard board = new GameBoard(5, 5);
        board.set(new CellPosition(4, 2), 'W');
        board.set(new CellPosition(3, 2), 'W');
        board.set(new CellPosition(4, 3), 'W');

        GameBoard nextBoard = new GameBoard(5, 5);
        nextBoard.set(new CellPosition(4, 1), 'W');

        List<GridCell<Character>> boardList = new ArrayList<>();
        for (GridCell<Character> gc : board) {
            if (gc.value() != '-') {
                boardList.add(gc);
            }
        }

        CellData newArea = board.moveBoard(new CellData(0, 0, 25, 25), 0, nextBoard, 4, 10).playableArea();
        boolean add = board.moveBoard(newArea, 0, nextBoard, 4, 0).rowAdded();

        List<GridCell<Character>> boardListMoved = new ArrayList<>();
        for (GridCell<Character> gc : board) {
            if (gc.value() != '-') {
                boardListMoved.add(gc);
            }
        }

        assertEquals(2, boardListMoved.size());
        assertEquals(3, boardList.size());
        assertTrue(add);
        assertNotEquals(boardList, boardListMoved);
        assertTrue(boardList.contains(new GridCell<Character>(new CellPosition(3, 2), 'W')));
        assertTrue(boardList.contains(new GridCell<Character>(new CellPosition(4, 2), 'W')));
        assertTrue(boardList.contains(new GridCell<Character>(new CellPosition(4, 3), 'W')));
        assertTrue(boardListMoved.contains(new GridCell<Character>(new CellPosition(0, 1), 'W')));
        assertTrue(boardListMoved.contains(new GridCell<Character>(new CellPosition(4, 2), 'W')));
    }
}
