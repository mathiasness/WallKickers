package no.uib.inf101.sem2.wallKickers.model.obstacles;

/**
* Class represents a long wall obstacle with Length, and a type W
* Player can stick to wall objects
*/
public class Wall implements Obstacle{
    private int length;
    private char type;

    //constructor
    public Wall(int length) {
        this.length = length;
        this.type = 'W';
    }

    //////////////////////////////////////
    /// Obstacle implementations
    /////////////////////////////////////

    @Override
    public int getLength() {
        return this.length;
    }

    @Override
    public char getObstacleType() {
        return this.type;
    }
}
