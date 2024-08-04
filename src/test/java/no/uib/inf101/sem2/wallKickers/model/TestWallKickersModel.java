package no.uib.inf101.sem2.wallKickers.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.wallKickers.model.gameBoard.RandomLevelFactory;
import no.uib.inf101.sem2.wallKickers.model.gameBoard.levels.LevelFactory;
import no.uib.inf101.sem2.wallKickers.model.player.Player;
import no.uib.inf101.sem2.wallKickers.model.player.PlayerState;
import no.uib.inf101.sem2.wallKickers.model.player.Position;

public class TestWallKickersModel {
    
    @Test
    public void testMovePlayer() {
        LevelFactory levelFactory = new RandomLevelFactory(35, 35);
        WallKickersModel model = new WallKickersModel(levelFactory);

        Position originalPos = model.getPlayerPosition();
        boolean didmove = model.movePlayer(10, -10);
        Position movedPos = model.getPlayerPosition();

        assertNotEquals(originalPos, movedPos);
        assertTrue(didmove);
        assertEquals(new Position(originalPos.x() + 10, originalPos.y() - 10), movedPos);

        Position collisionPos = new Position(460, 630); //ocupied cell
        model.jumpAnimation();
        model.jump();
        Player ilegalCopy = new Player(collisionPos, PlayerState.JUMP_RIGHT);
        boolean collision = model.isLegalMove(ilegalCopy);

        double moveX = 460 - model.getPlayerPosition().x();
        double moveY = 630 - model.getPlayerPosition().y();
        boolean collisionMove = model.movePlayer(moveX, moveY);

        assertFalse(collision);
        assertFalse(collisionMove);

        model.playerKilled();
        assertEquals(GameState.GAMEOVER, model.getGameState());
    }

    @Test
    public void testClockTick() {
        LevelFactory levelFactory = new RandomLevelFactory(35, 35);
        WallKickersModel model = new WallKickersModel(levelFactory);

        Position originalPos = model.getPlayerPosition();
        model.jumpAnimation();
        model.jump();
        //jump methods changes the values for speed, but doesn't move the player
        Position notMoved = model.getPlayerPosition();
        //clocktick moves player with velocity at the time
        model.clockTick();
        Position movedPos = model.getPlayerPosition();

        assertEquals(originalPos, notMoved);
        assertNotEquals(originalPos, movedPos);
        //xVel is 4, yVel is 7 after jump methods
        assertEquals(new Position(originalPos.x() + 4, originalPos.y()-7), movedPos);
    }

    @Test
    public void testCollision() {
        LevelFactory levelFactory = new RandomLevelFactory(35, 35);
        WallKickersModel model = new WallKickersModel(levelFactory);

        model.jumpAnimation();
        model.jump();
        model.clockTick();

        Position originalPos = model.getPlayerPosition();
        PlayerState originalState = model.getPlayerState();

        model.wallCollision(new Player(originalPos, originalState));

        Position newPos = model.getPlayerPosition();
        PlayerState newState = model.getPlayerState();

        assertEquals(originalPos, newPos); //Equal because no obstacle at position
        assertNotEquals(originalState, newState);
    }
}
