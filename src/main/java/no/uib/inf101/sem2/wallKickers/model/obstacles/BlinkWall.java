package no.uib.inf101.sem2.wallKickers.model.obstacles;

public class BlinkWall implements Obstacle {
    private int length;
    private char type;

    //constructor
    public BlinkWall(int length) {
        this.length = length;
        this.type = 'E';
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
