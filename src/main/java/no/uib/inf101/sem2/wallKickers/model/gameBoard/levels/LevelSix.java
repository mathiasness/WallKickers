package no.uib.inf101.sem2.wallKickers.model.gameBoard.levels;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.wallKickers.model.gameBoard.GameBoard;
import no.uib.inf101.sem2.wallKickers.model.obstacles.*;

/**
 * class extends Gameboard and represents a level structure
 */
public class LevelSix extends GameBoard{
    
    //constructor
    public LevelSix(int rows, int cols) {
        super(rows, cols);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                this.set(new CellPosition(row, col), '-');
            }
        }
        
        this.generateObstacle(new CellPosition(20, 17), new Wall(10));
        this.generateObstacle(new CellPosition(21, 24), new SpikeWall(8));
        this.generateObstacle(new CellPosition(21, 10), new SpikeWall(8));
        this.generateObstacle(new CellPosition(21, 30), new Wall(3));
        this.generateObstacle(new CellPosition(21, 4), new Wall(3));
        this.generateObstacle(new CellPosition(10, 1), new Wall(10));
        this.generateObstacle(new CellPosition(10, 33), new Wall(10));
        this.generateObstacle(new CellPosition(1, 17), new Wall(10));
        this.generateObstacle(new CellPosition(4, 17), new SpikeWall(3));
    }
}
