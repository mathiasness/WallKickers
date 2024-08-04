package no.uib.inf101.sem2.wallKickers.model.gameBoard;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.Grid;
import no.uib.inf101.sem2.wallKickers.model.CellData;
import no.uib.inf101.sem2.wallKickers.model.obstacles.Obstacle;

/**
 * Class represents the gameboard with tiles. Grid with number of rows and cols, and a char representing
 * obstacles, walls and empty spaces on the board.
 */
public class GameBoard extends Grid<Character> implements ObstacleGenerator {

    //Constructor
    public GameBoard(int rows, int cols) {
        super(rows, cols);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                this.set(new CellPosition(row, col), '-');
            }
        }
    }

    //////////////////////////////////////
    /// ObstacleGenerator implementations
    /////////////////////////////////////

    @Override
    public void generateObstacle(CellPosition cp, Obstacle obstacle) {
        int row = cp.row();
        int col = cp.col();
        for (int rows = 0; rows < obstacle.getLength(); rows ++) {
            this.set(new CellPosition(row + rows, col), obstacle.getObstacleType());
        }
    }

    //////////////////////////////////////
    /// Public mehtods
    /////////////////////////////////////

    /**
     * method moves the board and adds new rows
     * 
     * @param playableArea CellData
     * @param originalY double
     * @param nextBoard Gameboard
     * @param rowToAdd int
     * @param speed double
     * @return RowaddedEvent
     */
    public LevelHandler moveBoard(CellData playableArea, double originalY, GameBoard nextBoard, int rowToAdd, double speed) {
        double tileHeight = playableArea.cellHeight() / this.rows;
        CellData newPlayableArea;
        boolean rowAdded;
        if (playableArea.cellY() >= originalY + tileHeight) {
            newPlayableArea = addRowFromNextBoard(playableArea, tileHeight, nextBoard, rowToAdd);
            rowAdded = true;
        } else {
            newPlayableArea = changeBoardY(playableArea, speed);
            rowAdded = false;
        }
        return new LevelHandler(newPlayableArea, rowAdded);
    }

    //////////////////////////////////////
    /// Private helper mehtods
    /////////////////////////////////////
    
    private CellData addRowFromNextBoard(CellData playableArea, double tileHeight, GameBoard nextBoard, int rowToAdd) {//må sjekke hvor mange rader som har blitt fjernet
        shiftTiles();
        for (int col = 0; col < this.cols; col++) {
            this.set(new CellPosition(0, col), nextBoard.get(new CellPosition(rowToAdd, col)));
        }
        return new CellData(
            playableArea.cellX(), 
            playableArea.cellY() - tileHeight, 
            playableArea.cellWidth(), 
            playableArea.cellHeight());
    }
 
    private CellData changeBoardY(CellData playableArea, double speed) {
        double y = playableArea.cellY() + speed;
        return new CellData(playableArea.cellX(), y, playableArea.cellWidth(), playableArea.cellHeight());
    } 

    private void shiftTiles() {
        for (int row = this.rows - 2; row >= 0; row --) {
            for (int col = 0; col < this.cols; col ++) {
                this.set(new CellPosition(row + 1, col), this.get(new CellPosition(row, col)));
            }
        }
    }
}   
