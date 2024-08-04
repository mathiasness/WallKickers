package no.uib.inf101.sem2.wallKickers.model.player;

/**
 * PlayerData consist of the players Position and the players PlayerState
 * 
 * @param pos Position of player
 * @param state PlayerState of player
 */
public record PlayerData(Position pos, PlayerState state) { }
