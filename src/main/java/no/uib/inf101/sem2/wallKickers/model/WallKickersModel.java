package no.uib.inf101.sem2.wallKickers.model;

import java.util.List;
import java.util.ArrayList;
import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.wallKickers.controller.ControllableWallKickersModel;
import no.uib.inf101.sem2.wallKickers.model.gameBoard.GameBoard;
import no.uib.inf101.sem2.wallKickers.model.gameBoard.LevelHandler;
import no.uib.inf101.sem2.wallKickers.model.gameBoard.levels.LevelFactory;
import no.uib.inf101.sem2.wallKickers.model.gameBoard.levels.StartLevel;
import no.uib.inf101.sem2.wallKickers.model.player.Player;
import no.uib.inf101.sem2.wallKickers.model.player.PlayerState;
import no.uib.inf101.sem2.wallKickers.model.player.Position;
import no.uib.inf101.sem2.wallKickers.view.ViewableWallKickersModel;

/**
 * Class represents the model for WallKickers, holds the data of the game and changes the View
 */
public class WallKickersModel implements ViewableWallKickersModel, ControllableWallKickersModel {
    private GameBoard gameBoard;
    private Player player;
    private LevelFactory levelFactory;
    private double xVelocity;
    private double yVelocity;
    private static int terminalVelocity = 6;
    private Direction direction;
    private double flipRotation;
    private CellData playableArea = new CellData(150, 0, 500, 750);
    private char lastCollision = '-';
    private GameState gameState;
    private GameBoard nextLevel;
    private int rowToAdd;
    private int score;
    private boolean wallsBlinked = false;


    /**
     * Constructor for the model
     * 
     * @param levelFactory
     */
    public WallKickersModel(LevelFactory levelFactory) {
        this.levelFactory = levelFactory;
        this.gameBoard = new StartLevel(levelFactory.getRows(), levelFactory.getCols());
        this.nextLevel = generateNextLevel();
        this.player = new Player(new Position(400, 725), PlayerState.LEAN_RIGHT);
        this.direction = Direction.LEFT;
        findStartingPosition();
        this.xVelocity = 0;
        this.yVelocity = 0;
        this.flipRotation = 0;
        this.gameState = GameState.WELCOME;
        this.rowToAdd = gameBoard.rows() - 1;
        this.score = 0;
    }

    //////////////////////////////////////
    /// Viewable model implementations
    /////////////////////////////////////

    @Override
    public GridDimension getDimension() {
        return this.gameBoard;
    }

    @Override
    public Iterable<GridCell<Character>> getTilesOnBoard() {
        return this.gameBoard;
    }

    @Override
    public Position getPlayerPosition() {
        return this.player.getPlayerData().pos();
    }

    @Override
    public PlayerState getPlayerState() {
        return this.player.getPlayerData().state();
    }

    @Override
    public double getFlipRotation() {
        return this.flipRotation;
    }

    @Override
    public CellData getPlayableArea() {
        return playableArea;
    }

    @Override
    public GameState getGameState() {
        return this.gameState;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public int getPartOfWall(CellPosition pos) {
        int row = pos.row();
        int col = pos.col();
        char value = this.gameBoard.get(pos);
        return partHandler(row, col, value);
    }

    @Override
    public int getHighScore() {
        HighScoreHandler highScoreHandler = new HighScoreHandler(this.score);
        return highScoreHandler.scoreHandler();
    }

    //////////////////////////////////////
    /// Controllable model implementations
    /////////////////////////////////////

    @Override
    public void jump() {
        this.direction = player.playerStateToDirectionSwitch(getPlayerState());
        this.xVelocity = player.directionToSpeedSwitch(this.direction);
        this.yVelocity = -7;
    }

    @Override
    public void jumpAnimation() {
        this.player = this.player.nextInAnimationImageOrder();
    }

    @Override
    public void clockTick() {
        movingBehavior();
        flipRotation();
        gravityBehaviour();
        isElectric();
    }

    @Override
    public void setGameState(GameState newState) {
        this.gameState = newState;
    }

    @Override
    public void newGame() {
        playableArea = new CellData(150, 0, 500, 750);
        this.gameBoard = new StartLevel(levelFactory.getRows(), levelFactory.getCols());
        this.nextLevel = generateNextLevel();
        this.player = new Player(new Position(400, 725), PlayerState.LEAN_RIGHT);
        this.direction = Direction.LEFT;
        findStartingPosition();
        this.xVelocity = 0;
        this.yVelocity = 0;
        this.flipRotation = 0;
        this.lastCollision = '-';
        this.gameState = GameState.WELCOME;
        this.rowToAdd = gameBoard.rows() - 1;
        this.score = 0;
    }

    @Override
    public void blinkWall() {
        blinkValuesOfBoard(this.gameBoard);
        blinkValuesOfBoard(nextLevel);
    }

    //////////////////////////////////////
    /// Private helper methods
    /////////////////////////////////////

    private void blinkValuesOfBoard(GameBoard board) {
        for (GridCell<Character> cell : board) {
            if (cell.value() == 'E') {
                board.set(cell.pos(), 'G');
                wallsBlinked = true;
            } else if (cell.value() == 'G') {
                board.set(cell.pos(), 'E');
                wallsBlinked = false;
            }

            if (cell.value() == 'I') {
                board.set(cell.pos(), 'O');
                wallsBlinked = true;
            } else if (cell.value() == 'O') {
                board.set(cell.pos(), 'I');
                wallsBlinked = false;
            }
        }
    }

    void playerKilled() {
        this.gameState = GameState.GAMEOVER;
    }

    private void flipRotation() {
        if (getPlayerState() == PlayerState.BACKFLIP_LEFT) this.flipRotation = this.flipRotation - Math.PI/9;
        else if (getPlayerState() == PlayerState.BACKFLIP_RIGHT) this.flipRotation = this.flipRotation + Math.PI/9;
        else this.flipRotation = 0;
    }

    private GameBoard generateNextLevel() {
        GameBoard nextLevel = levelFactory.getNextLevel();
        return nextLevel;
    }

    private void addScore() {
        this.score ++;
    }

    private void handleLevel() {
        LevelHandler levelHandler = this.gameBoard.moveBoard(this.playableArea, 0, this.nextLevel, rowToAdd, -this.yVelocity);
        boolean rowAdded = levelHandler.rowAdded();
        this.playableArea = levelHandler.playableArea();
        if(rowAdded) {
            addScore();
            nextRow();
        }
    }

    private void nextRow() {
        if (rowToAdd == 0) {
            rowToAdd = gameBoard.rows() - 1;
            this.nextLevel = generateNextLevel();
        } else rowToAdd--;
    }

    private int partHandler(int row, int col, char value) {
        if (row == 0) return 2;
        else if (row == gameBoard.rows() - 1) {
            if (value != this.gameBoard.get(new CellPosition(row - 1, col))) return 0;
            else return 1;
        } else if (value != this.gameBoard.get(new CellPosition(row - 1, col))) return 0;
        else if (value == this.gameBoard.get(new CellPosition(row - 1, col)) 
        && value == this.gameBoard.get(new CellPosition(row + 1, col))) return 1;
        else return 2;
    }

    //////////////////////////////////////
    /// Private movement helper methods
    /////////////////////////////////////

    private void movingBehavior() {
        if (this.player.getY() < 300 && this.yVelocity < 1) {
            movePlayer(this.xVelocity, 0);
            handleLevel();
        } else if (this.player.getY() > 770) this.gameState = GameState.GAMEOVER;
        else movePlayer(this.xVelocity, this.yVelocity);
    }

    boolean movePlayer(double deltaX, double deltaY) {
        Player repositionedCopy = this.player.playerShiftedBy(deltaX, deltaY);
        if (isLegalMove(repositionedCopy)) { 
            this.player = repositionedCopy;
            return true;
        } else {
            if (lastCollision == 'W' 
                || lastCollision == 'L' 
                || lastCollision == 'E'
                || lastCollision == 'I') wallCollision(repositionedCopy);
            if (lastCollision == 'B') {
                wallCollision(repositionedCopy);
                jumpAnimation();
                jump();
            }
            if (lastCollision == 'R' 
                || lastCollision == 'O') playerKilled();
            return false;
        }
    }

    private void findStartingPosition() {
        while(movePlayer(-1, 0));
    }

    private void gravityBehaviour() {
        if (this.direction != Direction.STATIC
            || lastCollision == 'E' 
            && this.direction == Direction.STATIC
            && wallsBlinked) gravity();
        else this.yVelocity = 0;
    }

    private void gravity() {
        if (this.yVelocity < terminalVelocity) this.yVelocity += 0.5;
    }

    boolean isLegalMove(Player movedCopy) {
        PixelToCellPosition pixelToCP = new PixelToCellPosition(playableArea, this.gameBoard);
        double playerRadius = 20; //img size is 40X40
        Position topLeft = new Position(movedCopy.getX() - playerRadius, movedCopy.getY() - playerRadius);
        Position topRight = new Position(movedCopy.getX() + playerRadius, movedCopy.getY() - playerRadius);
        Position bottomLeft = new Position(movedCopy.getX() - playerRadius, movedCopy.getY() + playerRadius);
        Position bottomRight = new Position(movedCopy.getX() + playerRadius, movedCopy.getY() + playerRadius);

        List<Position> corners = new ArrayList<>();
        if (this.direction == Direction.RIGHT) {
            corners.add(bottomRight);
            corners.add(topRight);
        }

        if (this.direction == Direction.LEFT) {
            corners.add(bottomLeft);
            corners.add(topLeft);
        }

        for (Position position : corners) {
            if (!pixelToCP.pixelIsOnGrid(position)) return true;
            int col = pixelToCP.getCellPositionOfPixel(position).col();
                if (0 <= col && col <= 34) {
                char value = this.gameBoard.get(pixelToCP.getCellPositionOfPixel(position));
                if (value != '-' && value != 'G') {
                    this.lastCollision = value;
                    return false;
                }
            }
        }
        return true;
    }

    private void isElectric() {
        if (lastCollision == 'I' 
            && this.direction == Direction.STATIC
            && wallsBlinked) playerKilled();
    }

    //////////////////////////////////////
    /// Private collision helper methods
    /////////////////////////////////////

    void wallCollision(Player repositionedCopy) {
        CollisionHandler collisionHandler = findLegalPosition(repositionedCopy);
        PlayerState newState = collisionHandler.state();
        repositionedCopy = collisionHandler.repositionedCopy();
        repositionedCopy = repositionedCopy.playerStateUpdate(newState);
        this.direction = repositionedCopy.playerStateToDirectionSwitch(repositionedCopy.getPlayerData().state());
        this.xVelocity = repositionedCopy.directionToSpeedSwitch(this.direction);
        this.player = repositionedCopy;
    }

    private CollisionHandler findLegalPosition(Player repositionedCopy) {
        int moveX = 0;
        PlayerState newState = repositionedCopy.getPlayerData().state();
        if (this.direction == Direction.LEFT) {
            moveX = 1;
            newState = PlayerState.LEAN_RIGHT;
        } else if (this.direction == Direction.RIGHT) {
            moveX = -1;
            newState = PlayerState.LEAN_LEFT;
        }
        while (!isLegalMove(repositionedCopy)) {
            repositionedCopy = repositionedCopy.playerShiftedBy(moveX, 0);
        }

        return new CollisionHandler(newState, repositionedCopy);
    }
}
