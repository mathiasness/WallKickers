package no.uib.inf101.sem2.wallKickers.model.gameBoard.levels;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.wallKickers.model.gameBoard.GameBoard;
import no.uib.inf101.sem2.wallKickers.model.obstacles.*;

/**
 * class extends Gameboard and represents a level structure
 */
public class StartLevel extends GameBoard{

    //constructor
    public StartLevel(int rows, int cols) {
        super(rows, cols);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                this.set(new CellPosition(row, col), '-');
            }
        }
        this.generateObstacle(new CellPosition(32, 13), new Wall(3));
        this.generateObstacle(new CellPosition(24, 13), new Wall(3));
        this.generateObstacle(new CellPosition(26, 23), new Wall(6));
        this.generateObstacle(new CellPosition(21, 26), new Wall(3));
        this.generateObstacle(new CellPosition(16, 18), new Wall(3));
        this.generateObstacle(new CellPosition(13, 19), new SpikeWall(3));
        this.generateObstacle(new CellPosition(10, 18), new Wall(6));
        this.generateObstacle(new CellPosition(0, 18), new Wall(6));
    }
}
