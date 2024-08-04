package no.uib.inf101.sem2.wallKickers.model.obstacles;

public class ElectricWall implements Obstacle {
    private int length;
    private char type;

    //constructor
    public ElectricWall(int length) {
        this.length = length;
        this.type = 'I';
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
