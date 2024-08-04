package no.uib.inf101.sem2.wallKickers.model.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.wallKickers.model.Direction;


public class TestPlayer {
    
    @Test
    public void testHashCodeAndEquals() {
        Player p1 = new Player(new Position(0, 0), PlayerState.LEAN_LEFT);
        Player p2 = new Player(new Position(0, 0), PlayerState.LEAN_LEFT);
        Player p3 = new Player(new Position(0, 0), PlayerState.LEAN_LEFT).playerShiftedBy(1, 1);
        Player p4 = new Player(new Position(0, 0), PlayerState.LEAN_RIGHT);

        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
        assertNotEquals(p1, p3);
        assertNotEquals(p1, p4);
    }

    @Test
    public void testPlayerShifted() {
        Player p1 = new Player(new Position(0, 0), PlayerState.LEAN_LEFT);
        Player p2 = new Player(new Position(1, 1), PlayerState.LEAN_LEFT);
        Player p3 = new Player(new Position(4, 4), PlayerState.LEAN_LEFT);

        //test that we can shift player by delta pixels
        assertNotEquals(p1, p2);
        assertNotEquals(p1, p3);
        assertEquals(p1.playerShiftedBy(1, 1), p2);
        assertEquals(p1.playerShiftedBy(4, 4), p3);

    }

    @Test
    public void testAnimationOrder() {
        Player p1 = new Player(new Position(0, 0), PlayerState.LEAN_LEFT);
        Player p2 = new Player(new Position(0, 0), PlayerState.JUMP_LEFT);
        Player p3 = new Player(new Position(0, 0), PlayerState.LEAN_RIGHT);
        Player p4 = new Player(new Position(0, 0), PlayerState.BACKFLIP_LEFT);

        //test animation order, shold go lean - jump - backflip
        assertNotEquals(p1, p2);
        assertNotEquals(p3, p4);
        assertEquals(p1.nextInAnimationImageOrder(), p2);
        assertEquals(p3.nextInAnimationImageOrder().nextInAnimationImageOrder(), p4);
        assertThrows(IllegalArgumentException.class, () -> p4.nextInAnimationImageOrder());
    }

    @Test
    public void testGetMethods() {
        Player p1 = new Player(new Position(100, 200), PlayerState.JUMP_RIGHT);

        assertEquals(p1.getX(), 100);
        assertEquals(p1.getY(), 200);
        assertEquals(p1.getPlayerData(), new PlayerData(new Position(100, 200), PlayerState.JUMP_RIGHT));
    }

    @Test
    public void testPlayerStateUpdate() {
        Player p1 = new Player(new Position(0, 0), PlayerState.LEAN_LEFT);
        Player expected = new Player(new Position(0, 0), PlayerState.JUMP_LEFT);
        Player expected2 = new Player(new Position(0, 0), PlayerState.BACKFLIP_LEFT);


        assertEquals(expected, p1.playerStateUpdate(PlayerState.JUMP_LEFT));
        assertNotEquals(expected, p1);
        assertEquals(expected2, p1.playerStateUpdate(PlayerState.BACKFLIP_LEFT));
    }

    @Test
    public void testPlayerStateToDirectionSwitch() {
        Player p1 = new Player(new Position(0, 0), PlayerState.LEAN_LEFT);
        Direction left = p1.playerStateToDirectionSwitch(PlayerState.BACKFLIP_LEFT);
        Direction right = p1.playerStateToDirectionSwitch(PlayerState.JUMP_RIGHT);
        Direction leaning = p1.playerStateToDirectionSwitch(PlayerState.LEAN_LEFT);

        assertEquals(left, Direction.LEFT);
        assertEquals(right, Direction.RIGHT);
        assertEquals(leaning, Direction.STATIC);
    }

    @Test
    public void testDirectionToSpeedSwitch() {
        Player p1 = new Player(new Position(0, 0), PlayerState.LEAN_LEFT);
        double speedLeft = p1.directionToSpeedSwitch(Direction.LEFT);
        double speedRight = p1.directionToSpeedSwitch(Direction.RIGHT);
        double speedStatic = p1.directionToSpeedSwitch(Direction.STATIC);

        assertEquals(-4, speedLeft);
        assertEquals(4, speedRight);
        assertEquals(0, speedStatic);
    }
}
