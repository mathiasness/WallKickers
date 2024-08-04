package no.uib.inf101.sem2.wallKickers.model.gameBoard.levels;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.wallKickers.model.gameBoard.GameBoard;
import no.uib.inf101.sem2.wallKickers.model.obstacles.*;

/**
 * class extends Gameboard and represents a level structure
 */
public class LevelOne extends GameBoard{
    
    //constructor
    public LevelOne(int rows, int cols) {
        super(rows, cols);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                this.set(new CellPosition(row, col), '-');
            }
        }
        this.generateObstacle(new CellPosition(27, 26), new Wall(6));
        this.generateObstacle(new CellPosition(22, 18), new Wall(3));
        this.generateObstacle(new CellPosition(17, 10), new Wall(3));
        this.generateObstacle(new CellPosition(12, 2), new Wall(3));
        this.generateObstacle(new CellPosition(3, 15), new Wall(6));
        this.generateObstacle(new CellPosition(0, 20), new Wall(3));
    }
}