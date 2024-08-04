package no.uib.inf101.sem2.wallKickers.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.wallKickers.model.GameState;
import no.uib.inf101.sem2.wallKickers.model.player.PlayerState;
import no.uib.inf101.sem2.wallKickers.view.imagesAndColors.ColorTheme;
import no.uib.inf101.sem2.wallKickers.view.imagesAndColors.DefaultColorTheme;
import no.uib.inf101.sem2.wallKickers.view.imagesAndColors.DefaultImageLibrary;
import no.uib.inf101.sem2.wallKickers.view.imagesAndColors.ImageLibrary;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * Class represents the view of the WallKickers Game, draws the data from the model
 */
public class WallKickersView extends JPanel {
    private ImageLibrary imageLibrary;
    private ViewableWallKickersModel model;
    private ColorTheme colorTheme;
    private int highScore;

    /**
     * Constructor for the View
     * 
     * @param model ViewableWallKickersModel
     */
    public WallKickersView(ViewableWallKickersModel model) {
        this.model = model;
        this.imageLibrary = new DefaultImageLibrary();
        this.colorTheme = new DefaultColorTheme();
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(800, 750));
        this.highScore = this.model.getHighScore();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawGame(g2);
    }

    //////////////////////////////////////
    /// Private Methods
    /////////////////////////////////////

    private void drawGame(Graphics2D g2) {
        drawImageBackground(g2);
        Rectangle2D area = new Rectangle2D.Double(
            this.model.getPlayableArea().cellX(), 
            this.model.getPlayableArea().cellY(), 
            this.model.getPlayableArea().cellWidth(), 
            this.model.getPlayableArea().cellHeight()
        );
        CellPositionToPixelConverter cellPixels = new CellPositionToPixelConverter(
            area, this.model.getDimension());
        drawCells(g2, this.model.getTilesOnBoard(), cellPixels, this.imageLibrary, this.model);
        drawPlayerImage(g2);
        GameState currentState = this.model.getGameState();
        if (currentState != GameState.ACTIVE_GAME) drawGameState(g2, currentState);
        drawScore(g2, currentState);
        if (this.model.getScore() == 0 
            && this.model.getGameState() == GameState.ACTIVE_GAME) drawInstructions(g2);
    }

    private void drawImageBackground(Graphics2D g2) {
        BufferedImage image = this.imageLibrary.getBackgroundImage();
        Inf101Graphics.drawImage(g2, image, 0, 0, 0.55);
    }

    private void drawPlayerImage(Graphics2D g2) {
        PlayerState playerState = this.model.getPlayerState();
        double x = this.model.getPlayerPosition().x();
        double y = this.model.getPlayerPosition().y();
        BufferedImage image = this.imageLibrary.getPlayerImage(playerState);
        Inf101Graphics.drawCenteredImage(g2, image, x, y, 1.0, this.model.getFlipRotation());
    }

    private void drawGameState(Graphics2D g2, GameState gameState) {
        Rectangle2D gameOver = new Rectangle2D.Double(0, 0, getWidth(), getHeight());
        g2.setColor(colorTheme.getGameOverColor());
        g2.fill(gameOver);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 30));

        if (gameState == GameState.GAMEOVER) {
            Inf101Graphics.drawCenteredString(g2, "GAME OVER", 0, 0, getWidth(), getHeight());
            g2.setFont(new Font("Arial", Font.BOLD, 15));
            Inf101Graphics.drawCenteredString(
                g2, "PRESS SPACEBAR TO START NEW GAME", 0, 100, getWidth(), getHeight());
        } else Inf101Graphics.drawCenteredString(
            g2, "PRESS SPACEBAR TO START", 0, 0, getWidth(), getHeight());
    }

    private void drawInstructions(Graphics2D g2) {
        double x = this.model.getPlayerPosition().x() + 200;
        double y = this.model.getPlayerPosition().y() - 40;
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 10));
        Inf101Graphics.drawCenteredString(g2, "PRESS SPACEBAR TO JUMP",x , y - 20, 40, 20);
        Inf101Graphics.drawCenteredString(g2, "HOLD TO JUMP HIGHER", x, y, 40, 20);
        Inf101Graphics.drawCenteredString(g2, "PRESS AGAIN TO BACKFLIP", x, y + 20, 40, 20);
    }

    private void drawScore(Graphics2D g2, GameState gameState) {
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 15));

        if (gameState == GameState.GAMEOVER) {
            if (this.model.getScore() > this.highScore) {
                updateHighScore();
                Inf101Graphics.drawCenteredString(
                g2, "NEW HIGHSCORE: " + this.highScore, 0, 50, getWidth(), getHeight());
            } else Inf101Graphics.drawCenteredString(
                g2, "SCORE: " + this.model.getScore(), 0, 50, getWidth(), getHeight());
        } else if (gameState == GameState.ACTIVE_GAME){
            Inf101Graphics.drawCenteredString(
                g2, "Score: " + this.model.getScore(), 0, 0, 100, 50);
            Inf101Graphics.drawCenteredString(
                g2, "Highscore: " + this.highScore, 0, 50, 100, 100);
        }
    }

    private static void drawCells(
        Graphics2D g2, Iterable<GridCell<Character>> iterable, CellPositionToPixelConverter cellPixels, 
        ImageLibrary imageLibrary, ViewableWallKickersModel model) {
        for (GridCell<Character> cell : iterable) {
            if (cell.value() != '-' && cell.value() != 'G') {
                int partOfWall = model.getPartOfWall(cell.pos());
                BufferedImage wallTexture = imageLibrary.getObstacleTextureImage(cell.value(), partOfWall);
                Inf101Graphics.drawImage(g2, wallTexture, 
                cellPixels.getCellData(cell.pos()).cellX(), 
                cellPixels.getCellData(cell.pos()).cellY(), 
                0.50);
            }
        }
    }

    private void updateHighScore() {
        this.highScore = this.model.getHighScore();
    }
}
