package no.uib.inf101.sem2.wallKickers.model.gameBoard.levels;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.wallKickers.model.gameBoard.GameBoard;
import no.uib.inf101.sem2.wallKickers.model.obstacles.*;

/**
 * class extends Gameboard and represents a level structure
 */
public class LevelFive extends GameBoard{
    
    //constructor
    public LevelFive(int rows, int cols) {
        super(rows, cols);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                this.set(new CellPosition(row, col), '-');
            }
        }
        
        this.generateObstacle(new CellPosition(30, 17), new Wall(3));
        this.generateObstacle(new CellPosition(23, 17), new ElectricWall(3));
        this.generateObstacle(new CellPosition(16, 17), new Wall(3));
        this.generateObstacle(new CellPosition(9, 17), new ElectricWall(3));
        this.generateObstacle(new CellPosition(2, 17), new Wall(3));
        this.generateObstacle(new CellPosition(0, 27), new Wall(3));
        this.generateObstacle(new CellPosition(0, 6), new Wall(3));
    }
    
}