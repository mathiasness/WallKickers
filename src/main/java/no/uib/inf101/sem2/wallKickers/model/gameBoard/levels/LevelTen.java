package no.uib.inf101.sem2.wallKickers.model.gameBoard.levels;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.wallKickers.model.gameBoard.GameBoard;
import no.uib.inf101.sem2.wallKickers.model.obstacles.*;

/**
 * class extends Gameboard and represents a level structure
 */
public class LevelTen extends GameBoard{
    
    //constructor
    public LevelTen(int rows, int cols) {
        super(rows, cols);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                this.set(new CellPosition(row, col), '-');
            }
        }
        
        this.generateObstacle(new CellPosition(30, 17), new Wall(3));
        this.generateObstacle(new CellPosition(5, 29), new Wall(22));
        this.generateObstacle(new CellPosition(5, 5), new Wall(22));
        this.generateObstacle(new CellPosition(20, 28), new SpikeWall(2));
        this.generateObstacle(new CellPosition(10, 28), new SpikeWall(2));
        this.generateObstacle(new CellPosition(20, 6), new SpikeWall(2));
        this.generateObstacle(new CellPosition(10, 6), new SpikeWall(2));
        this.generateObstacle(new CellPosition(0, 18), new Wall(6));
        this.generateObstacle(new CellPosition(0, 16), new Wall(6));
    }
}