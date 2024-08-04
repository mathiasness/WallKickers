package no.uib.inf101.sem2.wallKickers.model.obstacles;

/**
* Class represents a spike obstacle with Length, and a type R
* Player can stick to wall objects
*/
public class SpikeWall implements Obstacle {
    private int length;
    private char type;

    //constructor
    public SpikeWall(int length) {
        this.length = length;
        this.type = 'R';
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
