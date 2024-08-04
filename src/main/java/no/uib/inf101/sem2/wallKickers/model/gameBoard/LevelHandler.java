package no.uib.inf101.sem2.wallKickers.model.gameBoard;

import no.uib.inf101.sem2.wallKickers.model.CellData;

/**
 * LevelHandler consist of Celldata for a playableArea and a boolean rowAdded
 */
public record LevelHandler(CellData playableArea, boolean rowAdded) { }
