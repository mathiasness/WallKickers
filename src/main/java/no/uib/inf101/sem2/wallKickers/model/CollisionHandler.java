package no.uib.inf101.sem2.wallKickers.model;

import no.uib.inf101.sem2.wallKickers.model.player.Player;
import no.uib.inf101.sem2.wallKickers.model.player.PlayerState;

/**
 * Collision handler consist of PlayerState and Player
 */
public record CollisionHandler(PlayerState state, Player repositionedCopy) { }
