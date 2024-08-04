package no.uib.inf101.sem2.grid;

/**
 * @author Torstein Strømme
 * A GridCell contains a CellPosition and a value E.
 *
 * @param CellPosition  the position of the cell
 * @param E       the value of the cell
 */
public record GridCell<E>(CellPosition pos, E value) { }