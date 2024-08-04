package no.uib.inf101.sem2.wallKickers.view.imagesAndColors;

import java.awt.image.BufferedImage;
import no.uib.inf101.sem2.wallKickers.model.player.PlayerState;

public interface ImageLibrary {
    
    /**
     * method returns the background image
     * 
     * @return BufferedImage
     */
    BufferedImage getBackgroundImage();

    /**
     * method takes in the playerstate of the player and returns the corresponding animation image
     * 
     * @param playerState, representing the current playerstate of the player
     * @return Buffered image animation
     */
    BufferedImage getPlayerImage(PlayerState playerState);

    /**
     * method takes in the char walltype and returns the corresponding wall image
     * 
     * @param c char
     * @return Buffered image of wall
     */
    BufferedImage getObstacleTextureImage(char c, int partOfWall);
}
