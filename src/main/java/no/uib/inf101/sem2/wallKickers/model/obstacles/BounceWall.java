package no.uib.inf101.sem2.wallKickers.model.obstacles;

public class BounceWall implements Obstacle {
    private int length;
    private char type;

    //constructor
    public BounceWall(int length) {
        this.length = length;
        this.type = 'B';
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
