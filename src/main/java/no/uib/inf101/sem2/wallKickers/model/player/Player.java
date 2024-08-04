package no.uib.inf101.sem2.wallKickers.model.player;

import java.util.Objects;
import no.uib.inf101.sem2.wallKickers.model.Direction;

/**
 * Class represents the player in the game with a Position and a PlayerState
 */
public class Player {
    private Position pos;
    private PlayerState playerState;

    //Constructor
    public Player(Position pos, PlayerState playerState) {
        this.pos = pos;
        this.playerState = playerState;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Player)) return false;

        Player other = ((Player) obj);
        return (this.playerState == other.playerState) && (this.pos.equals(other.pos));
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.pos, this.playerState);
    }

    //////////////////////////////////////
    /// Public Methods
    /////////////////////////////////////

    /**
     * method gets horrisontal player positon
     * 
     * @return float x
     */
    public double getX() {
        return this.pos.x();
    }

    /**
     * method gets vertical player position
     * 
     * @return float y
     */
    public double getY() {
        return this.pos.y();
    }

    /**
     * method creates a new Playerdata which consist of the position and the playerstate
     * 
     * @return playerdata
     */
    public PlayerData getPlayerData() {
        return new PlayerData(this.pos, this.playerState);
    }

    /**
     * Method makes a copy of the player and returns a new player with a new position
     * 
     * @param x float, horisontal movement
     * @param y float, vertical movement
     * @return Player object, new Player with new position
     */
    public Player playerShiftedBy(double deltaX, double deltaY) {
        Position newPosition = new Position(this.pos.x() + deltaX, this.pos.y() + deltaY);
        return new Player(newPosition, this.playerState);
    }

    /**
     * returns a new player object with new state
     * 
     * @param newplayerState updated state
     * @return new player with updated state
     */
    public Player playerStateUpdate(PlayerState newplayerState) {
        return new Player(this.pos, newplayerState);
    }

    /**
     * method looks at the current state of the player and returns new Player with 
     * with successive playerstate
     * 
     * @return Player object, new player with next state
     */
    public Player nextInAnimationImageOrder() {
        PlayerState currentState = this.playerState;
        PlayerState nextState = switch(currentState) {
            case LEAN_LEFT -> PlayerState.JUMP_LEFT;
            case LEAN_RIGHT -> PlayerState.JUMP_RIGHT;
            case JUMP_RIGHT -> PlayerState.BACKFLIP_LEFT;
            case JUMP_LEFT -> PlayerState.BACKFLIP_RIGHT;

            default -> throw new IllegalArgumentException(
                "No succession for'" + currentState + "'");
        };
        return playerStateUpdate(nextState);
    }

    /**
     * method returns the Direction cooresponding to the given PlayerState
     * 
     * @param state PlayerState
     * @return Direction
     */
    public Direction playerStateToDirectionSwitch(PlayerState state) {
        Direction newDirection = switch(state) {
            case JUMP_LEFT, BACKFLIP_LEFT -> Direction.LEFT;
            case JUMP_RIGHT, BACKFLIP_RIGHT -> Direction.RIGHT;
            case LEAN_LEFT, LEAN_RIGHT -> Direction.STATIC;

            default -> throw new IllegalArgumentException(
                "No matching direction for '" + state + "'");
        };
        return newDirection;
    }

    /**
     * method returns the speed as double cooresponding to the direction
     * 
     * @param direction Direction
     * @return double, speed
     */
    public double directionToSpeedSwitch(Direction direction) {
        double horisontalSpeed = switch(direction) {
            case LEFT -> -4;
            case RIGHT -> 4;
            case STATIC -> 0;

            default -> throw new IllegalArgumentException(
                "No matching speed for '" + direction + "'");
        };
        return horisontalSpeed;
    }
}
