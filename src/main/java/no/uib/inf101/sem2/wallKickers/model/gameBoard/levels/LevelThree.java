package no.uib.inf101.sem2.wallKickers.model.gameBoard.levels;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.wallKickers.model.gameBoard.GameBoard;
import no.uib.inf101.sem2.wallKickers.model.obstacles.*;

/**
 * class extends Gameboard and represents a level structure
 */
public class LevelThree extends GameBoard{
    
    //constructor
    public LevelThree(int rows, int cols) {
        super(rows, cols);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                this.set(new CellPosition(row, col), '-');
            }
        }
        
        this.generateObstacle(new CellPosition(30, 15), new Wall(4));
        this.generateObstacle(new CellPosition(24, 25), new Wall(4));
        this.generateObstacle(new CellPosition(20, 15), new BlinkWall(4));
        this.generateObstacle(new CellPosition(13, 27), new Wall(7));
        this.generateObstacle(new CellPosition(0, 18), new BounceWall(13));
        this.generateObstacle(new CellPosition(5, 18), new SpikeWall(3));
    }
}
