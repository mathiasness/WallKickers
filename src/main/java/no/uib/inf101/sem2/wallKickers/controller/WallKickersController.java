package no.uib.inf101.sem2.wallKickers.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import no.uib.inf101.sem2.wallKickers.midi.BackGroundMusic;
import no.uib.inf101.sem2.wallKickers.model.GameState;
import no.uib.inf101.sem2.wallKickers.view.WallKickersView;

/**
 * Class represents the controller for the game.
 * Handles keystrokes and changes the model
 */
public class WallKickersController implements KeyListener {
    private ControllableWallKickersModel model;
    private WallKickersView view;
    private Timer refreshRate;
    private Timer jumpTimer;
    private Timer secondTimer;
    private int secondsPassed = 0;
    private int ticks = 0;
    private BackGroundMusic music = new BackGroundMusic();

    /**
     * Constructor for the controller
     * 
     * @param model ControllableWallKickersModel
     * @param view  WallkickersView
     */
    public WallKickersController(ControllableWallKickersModel model, WallKickersView view) {
        this.model = model;
        this.view = view;
        this.view.setFocusable(true);
        this.view.addKeyListener(this);
        this.refreshRate = new Timer(1000/60, this::refreshFrame);
        this.jumpTimer = new Timer(1000/60, this::jumpAction);
        this.secondTimer = new Timer(1000, this::secondEvent);
        this.secondTimer.start();
        this.refreshRate.start();
        music.run();
    }

    //////////////////////////////////////
    /// KeyListener implementations
    /////////////////////////////////////

    @Override
    public void keyTyped(KeyEvent e) {
        //ignore implementation
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (ticks == 0) this.jumpTimer.start();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            this.jumpTimer.stop();
            ticks = 0;
            if (this.model.getGameState() == GameState.WELCOME) {
                this.model.setGameState(GameState.ACTIVE_GAME);
                this.view.repaint();
            } 
            if (this.model.getGameState() == GameState.GAMEOVER) {
                this.model.newGame();
                this.view.repaint();
            }
        }
    }

    //////////////////////////////////////
    /// Private ActionEvent helper methods
    /////////////////////////////////////

    private void jumpAction(ActionEvent ae) {
        if (this.model.getGameState() == GameState.ACTIVE_GAME) {
            if (ticks == 0) {
                this.model.jumpAnimation();
            }
            if (ticks == 12) this.jumpTimer.stop();
            ticks ++;
            this.model.jump();
        }
    }

    private void refreshFrame(ActionEvent ae) {
        if (this.model.getGameState() == GameState.ACTIVE_GAME) {
            this.model.clockTick();
            this.view.repaint();
        }
    }

    private void secondEvent(ActionEvent ae) {
        if (secondsPassed % 3 == 0) this.model.blinkWall();
        secondsPassed++;
        if (secondsPassed % 255 == 0 ) music.run();
    }
}
