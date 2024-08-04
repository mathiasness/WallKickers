package no.uib.inf101.sem2.wallKickers.model.obstacles;

public interface Obstacle {
    
    /**
     * method returns the length of the obstacle
     * 
     * @return int, length of obstacle
     */
    int getLength();

    /**
     * method returns the char representing the obstacle type
     * 
     * @return char, obstacle type
     */
    char getObstacleType();
}
