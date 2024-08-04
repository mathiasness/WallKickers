package no.uib.inf101.sem2.wallKickers.model.gameBoard.levels;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.wallKickers.model.gameBoard.GameBoard;
import no.uib.inf101.sem2.wallKickers.model.obstacles.*;

/**
 * class extends Gameboard and represents a level structure
 */
public class LevelTwo extends GameBoard{
    
    //constructor
    public LevelTwo(int rows, int cols) {
        super(rows, cols);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                this.set(new CellPosition(row, col), '-');
            }
        }
        this.generateObstacle(new CellPosition(7, 1), new BounceWall(20));
        this.generateObstacle(new CellPosition(7, 10), new BounceWall(15));
        this.generateObstacle(new CellPosition(28, 1), new Wall(5));
        this.generateObstacle(new CellPosition(28, 22), new Wall(5));
        this.generateObstacle(new CellPosition(30, 9), new Wall(4));
        this.generateObstacle(new CellPosition(3, 1), new Wall(3));
        this.generateObstacle(new CellPosition(0, 17), new Wall(10));
        
    }
}
