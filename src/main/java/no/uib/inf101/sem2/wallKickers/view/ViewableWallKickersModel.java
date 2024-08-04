package no.uib.inf101.sem2.wallKickers.view;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.wallKickers.model.CellData;
import no.uib.inf101.sem2.wallKickers.model.GameState;
import no.uib.inf101.sem2.wallKickers.model.player.PlayerState;
import no.uib.inf101.sem2.wallKickers.model.player.Position;

public interface ViewableWallKickersModel {
    
    /**
     * method returns the grid dimensions of the board
     * 
     * @return GridDimensions, how many rows and cols on the board
     */
    GridDimension getDimension();

    /**
     * method returning iterable of gridcell objects representing all tiles on the board
     * 
     * @return Iterable with symbols representing the tiles
     */
    Iterable<GridCell<Character>> getTilesOnBoard();

    /**
     * method gets the current player position 
     * 
     * @return Position of player
     */
    Position getPlayerPosition();

    /**
     * metod gets the current player state
     * 
     * @return Playerstate
     */
    PlayerState getPlayerState();

    /**
     * method returns rotation degree when player is doing a backflip
     * 
     * @return double in radians
     */
    double getFlipRotation();

    /**
     * method creates the dimensions for the area where the grid/gameboard should be drawn
     * 
     * @return Celldata for area
     */
    CellData getPlayableArea();

    /**
     * method returns the current gamestate
     * 
     * @return gamestate
     */
    GameState getGameState();

    /**
     * method returns the score
     * 
     * @return int score
     */
    int getScore();

    /**
     * method returns int where 0: top of wall, 1: middle, 2: bottom of wall
     * 
     * @param pos position of the cell
     * @return int
     */
    int getPartOfWall(CellPosition pos);

    /**
     * method returns the highscore
     * 
     * @return int
     */
    int getHighScore();
}
