package no.uib.inf101.sem2.wallKickers.model.gameBoard.levels;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.wallKickers.model.gameBoard.GameBoard;
import no.uib.inf101.sem2.wallKickers.model.obstacles.*;

/**
 * class extends Gameboard and represents a level structure
 */
public class LevelFour extends GameBoard{
    
    //constructor
    public LevelFour(int rows, int cols) {
        super(rows, cols);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                this.set(new CellPosition(row, col), '-');
            }
        }
        
        this.generateObstacle(new CellPosition(30, 12), new Wall(3));
        this.generateObstacle(new CellPosition(22, 8), new Wall(6));
        this.generateObstacle(new CellPosition(5, 20), new SpikeWall(22));
        this.generateObstacle(new CellPosition(18, 20), new BlinkWall(4));
        this.generateObstacle(new CellPosition(5, 21), new BounceWall(13));
        this.generateObstacle(new CellPosition(22, 21), new BounceWall(5));
        this.generateObstacle(new CellPosition(0, 21), new Wall(5));
    }
    
}