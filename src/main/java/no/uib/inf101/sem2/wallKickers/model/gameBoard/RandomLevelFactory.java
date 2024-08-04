package no.uib.inf101.sem2.wallKickers.model.gameBoard;

import java.util.Random;
import no.uib.inf101.sem2.wallKickers.model.gameBoard.levels.*;

/**
 * Class implements LevelFactory,
 * selects the next level randomly
 */
public class RandomLevelFactory implements LevelFactory {
    private int rows;
    private int cols;

    //constructor
    public RandomLevelFactory(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    //////////////////////////////////////
    /// LevelFactory implementations
    /////////////////////////////////////

    @Override
    public GameBoard getNextLevel() {
        Random random = new Random();
        int r = random.nextInt(10);
        return nextLevel(r);
    }

    @Override
    public int getRows() {
        return this.rows;
    }

    @Override
    public int getCols() {
        return this.cols;
    }

    //////////////////////////////////////
    /// Private helper methods
    /////////////////////////////////////
    
    private GameBoard nextLevel(int value) {
        GameBoard level = switch(value) {
            case 0 -> new StartLevel(rows, cols);
            case 1 -> new LevelOne(rows, cols);
            case 2 -> new LevelTwo(rows, cols);
            case 3 -> new LevelThree(rows, cols);
            case 4 -> new LevelFour(rows, cols);
            case 5 -> new LevelFive(rows, cols);
            case 6 -> new LevelSix(rows, cols);
            case 7 -> new LevelSeven(rows, cols);
            case 8 -> new LevelEight(rows, cols);
            case 9 -> new LevelNine(rows, cols);
            case 10 -> new LevelTen(rows, cols);

            default -> throw new IllegalArgumentException(
                "No available level for '" + value + "'");
        };
        return level;
    }
}
