package no.uib.inf101.sem2.wallKickers;

import javax.swing.JFrame;
import no.uib.inf101.sem2.wallKickers.controller.WallKickersController;
import no.uib.inf101.sem2.wallKickers.model.WallKickersModel;
import no.uib.inf101.sem2.wallKickers.model.gameBoard.RandomLevelFactory;
import no.uib.inf101.sem2.wallKickers.view.WallKickersView;

/**
 * Entry point for this project. WallKickers by Mathias Hop Ness, a replica of Wallkickers.
 */
public class WallKickersMain {
  public static void main(String[] args) {
    RandomLevelFactory levelFactory = new RandomLevelFactory(35, 35);
    WallKickersModel model = new WallKickersModel(levelFactory);
    WallKickersView view = new WallKickersView(model);
    new WallKickersController(model, view);

    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("INF101");
    frame.setContentPane(view);
    frame.pack();
    frame.setVisible(true);
    frame.setResizable(false);
  }
}
