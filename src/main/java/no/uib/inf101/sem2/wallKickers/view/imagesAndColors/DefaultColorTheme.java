package no.uib.inf101.sem2.wallKickers.view.imagesAndColors;

import java.awt.Color;

/**
 * Class holds the Default colors for the WallKickers game
 */
public class DefaultColorTheme implements ColorTheme {

    //////////////////////////////////////
    /// ColorTheme implentations
    /////////////////////////////////////

    @Override
    public Color getCellColor(char c) throws IllegalArgumentException {
        Color color = switch(c) {
            case 'W' -> Color.LIGHT_GRAY;
            case 'R' -> Color.RED;
            case 'B' -> Color.PINK;
            case 'E' -> Color.GREEN;
            case 'I' -> Color.GRAY;
            case 'O' -> Color.BLUE;
            case '-', 'G' -> new Color(0, 0, 0, 0);

            default -> throw new IllegalArgumentException(
                "No available color for '" + c + "'");
        };
        return color;
    }

    @Override
    public Color getFrameColor() {
        return Color.WHITE;
    }
    
    @Override
    public Color getGameOverColor() {
        return new Color(0, 0, 0, 90);
    }
}
