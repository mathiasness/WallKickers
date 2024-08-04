package no.uib.inf101.sem2.wallKickers.view.imagesAndColors;

import no.uib.inf101.sem2.wallKickers.model.player.PlayerState;
import no.uib.inf101.sem2.wallKickers.view.Inf101Graphics;
import java.awt.image.BufferedImage;

/**
 * Class holds the default pictures for the WallKickers game
 */
public class DefaultImageLibrary implements ImageLibrary {
    private BufferedImage backgroundImage = Inf101Graphics.loadImageFromResources("/forrestBackground.png");
    private BufferedImage pixelCatAnimations = Inf101Graphics.loadImageFromResources("/pixelCatAnimations.png");
    private BufferedImage obstacleTextures = Inf101Graphics.loadImageFromResources("/obstacleTextures.png");

    //////////////////////////////////////
    /// ImageLibrary implentations
    /////////////////////////////////////

    @Override
    public BufferedImage getBackgroundImage() {
        return backgroundImage;
    }
    
    @Override
    public BufferedImage getPlayerImage(PlayerState playerState) {
        BufferedImage animation = switch(playerState) {
            case JUMP_LEFT -> pixelCatAnimations.getSubimage(40, 40, 40, 40);
            case JUMP_RIGHT -> pixelCatAnimations.getSubimage(40, 0, 40, 40);
            case BACKFLIP_LEFT -> pixelCatAnimations.getSubimage(0, 40, 40, 40);
            case BACKFLIP_RIGHT -> pixelCatAnimations.getSubimage(80, 0, 40, 40);
            case LEAN_LEFT -> pixelCatAnimations.getSubimage(80, 40, 40, 40);
            case LEAN_RIGHT -> pixelCatAnimations.getSubimage(0, 0, 40, 40);

            default -> throw new IllegalArgumentException(
                "No available image for '" + playerState + "'");
        };

        return animation;
    }

    @Override
    public BufferedImage getObstacleTextureImage(char c, int partOfWall) {
        int wallType = switch(c) {
            case 'W' -> 0;
            case 'R' -> 1;
            case 'B' -> 2;
            case 'E' -> 3;
            case 'I' -> 4;
            case 'O' -> 5;
            case '-', 'G' -> 6;

            default -> throw new IllegalArgumentException(
                "No available color for '" + c + "'");
        };
        BufferedImage wallTexture = obstacleTextures.getSubimage(wallType * 28, partOfWall * 43, 28, 43);
        return wallTexture;
    }
    
}
