package no.uib.inf101.sem2.wallKickers.model.gameBoard.obstacles;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.wallKickers.model.obstacles.Obstacle;
import no.uib.inf101.sem2.wallKickers.model.obstacles.SpikeWall;
import no.uib.inf101.sem2.wallKickers.model.obstacles.Wall;

public class TestObstactle {
    
    @Test
    public void testObstacles() {
        Obstacle wall = new Wall(4);
        Obstacle spikeWall = new SpikeWall(10);

        assertEquals(4, wall.getLength());
        assertEquals(10, spikeWall.getLength());
        assertEquals('W', wall.getObstacleType());
        assertEquals('R', spikeWall.getObstacleType());
    }
}
