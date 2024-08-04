package no.uib.inf101.sem2.wallKickers.model.gameBoard;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.wallKickers.model.obstacles.Obstacle;

public interface ObstacleGenerator {
    
    /**
     * method generates obstacles for the gameboard/level
     * 
     * @param cp cellposition of the top cell in the obstacle
     * @param obstacle type of obstacle to be generated
     */
    void generateObstacle(CellPosition cp, Obstacle obstacle);
}
