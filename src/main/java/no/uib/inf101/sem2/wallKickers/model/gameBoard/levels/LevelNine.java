package no.uib.inf101.sem2.wallKickers.model.gameBoard.levels;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.wallKickers.model.gameBoard.GameBoard;
import no.uib.inf101.sem2.wallKickers.model.obstacles.*;

/**
 * class extends Gameboard and represents a level structure
 */
public class LevelNine extends GameBoard{
    
    //constructor
    public LevelNine(int rows, int cols) {
        super(rows, cols);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                this.set(new CellPosition(row, col), '-');
            }
        }
        
        this.generateObstacle(new CellPosition(30, 17), new Wall(3));
        this.generateObstacle(new CellPosition(25, 30), new BounceWall(3));
        this.generateObstacle(new CellPosition(20, 28), new BounceWall(3));
        this.generateObstacle(new CellPosition(15, 26), new BounceWall(3));
        this.generateObstacle(new CellPosition(25, 4), new BounceWall(3));
        this.generateObstacle(new CellPosition(20, 6), new BounceWall(3));
        this.generateObstacle(new CellPosition(15, 8), new BounceWall(3));
        this.generateObstacle(new CellPosition(12, 17), new ElectricWall(4));
        this.generateObstacle(new CellPosition(4, 30), new Wall(5));
        this.generateObstacle(new CellPosition(4, 4), new Wall(5));
        this.generateObstacle(new CellPosition(1, 17), new Wall(3));
    }
}