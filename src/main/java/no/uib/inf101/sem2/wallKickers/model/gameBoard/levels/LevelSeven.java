package no.uib.inf101.sem2.wallKickers.model.gameBoard.levels;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.wallKickers.model.gameBoard.GameBoard;
import no.uib.inf101.sem2.wallKickers.model.obstacles.*;

/**
 * class extends Gameboard and represents a level structure
 */
public class LevelSeven extends GameBoard{
    
    //constructor
    public LevelSeven(int rows, int cols) {
        super(rows, cols);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                this.set(new CellPosition(row, col), '-');
            }
        }
        
        this.generateObstacle(new CellPosition(30, 29), new Wall(3));
        this.generateObstacle(new CellPosition(30, 5), new Wall(3));
        this.generateObstacle(new CellPosition(20, 29), new Wall(3));
        this.generateObstacle(new CellPosition(20, 5), new Wall(3));
        this.generateObstacle(new CellPosition(18, 17), new SpikeWall(3));
        this.generateObstacle(new CellPosition(15, 17), new Wall(3));
        this.generateObstacle(new CellPosition(12, 17), new SpikeWall(3));
        this.generateObstacle(new CellPosition(5, 17), new Wall(3));
        this.generateObstacle(new CellPosition(0, 17), new BlinkWall(3));
    }
}