package no.uib.inf101.sem2.wallKickers.model.gameBoard.levels;

import no.uib.inf101.sem2.wallKickers.model.gameBoard.GameBoard;

public interface LevelFactory {
    /**
     * method gets the next level
     * 
     * @return Gameboard object, level
     */
    GameBoard getNextLevel();

    /**
     * method returns number of rows
     * 
     * @return int
     */
    int getRows();

    /**
     * method returns number of cols
     * 
     * @return int
     */
    int getCols();
}
