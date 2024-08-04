package no.uib.inf101.sem2.wallKickers.model.gameBoard.levels;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.wallKickers.model.gameBoard.GameBoard;
import no.uib.inf101.sem2.wallKickers.model.obstacles.*;

/**
 * class extends Gameboard and represents a level structure
 */
public class LevelEight extends GameBoard{
    
    //constructor
    public LevelEight(int rows, int cols) {
        super(rows, cols);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                this.set(new CellPosition(row, col), '-');
            }
        }
        
        this.generateObstacle(new CellPosition(1, 17), new BlinkWall(30));
        this.generateObstacle(new CellPosition(25, 30), new Wall(2));
        this.generateObstacle(new CellPosition(15, 30), new Wall(2));
        this.generateObstacle(new CellPosition(5, 30), new Wall(2));
        this.generateObstacle(new CellPosition(25, 4), new Wall(2));
        this.generateObstacle(new CellPosition(15, 4), new Wall(2));
        this.generateObstacle(new CellPosition(5, 4), new Wall(2));
    }
    
}
