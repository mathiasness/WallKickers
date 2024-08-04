package no.uib.inf101.sem2.wallKickers.view.imagesAndColors;

import java.awt.Color;

public interface ColorTheme {
    
    /**
     * takes in a char and returns the matching color
     * 
     * @param c single char
     * @return Color object, color of the cell
     * @throws IllegalArgumentException if char is null or not defined in method
     */
    Color getCellColor(char c) throws IllegalArgumentException;

    /**
     * returns the color of the cell frame
     * new Color(0, 0, 0, 0) for see-through color
     * 
     * @return Color object, color of the cell frames
     */
    Color getFrameColor();

    /**
     * returns the color for the game over screen
     * 
     * @return Color object
     */
    Color getGameOverColor();
}
