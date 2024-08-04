package no.uib.inf101.sem2.wallKickers.controller;

import no.uib.inf101.sem2.wallKickers.model.GameState;

public interface ControllableWallKickersModel {

    /**
     * method executes a jump
     */
    void jump();

    /**
     * method changes the model with each clock tick
     */
    void clockTick();

    /**
     * method changes the animation when a jump sequence is initiated
     */
    void jumpAnimation();

    /**
     * method returns the current gamestate
     * @return gamestate
     */
    GameState getGameState();

    /**
     * method sets a new gamestate
     * @param newState
     */
    void setGameState(GameState newState);

    /**
     * method starts a new Game
     */
    void newGame();

    /**
     * method controls disappearing walls
     * 
     */
    void blinkWall();
}
