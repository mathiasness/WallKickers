package no.uib.inf101.sem2.wallKickers.view;

import java.awt.geom.Rectangle2D;
import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.wallKickers.model.CellData;

/**
 * Class finds the respective values of tiles so that they can be drawn in the correct place
 */
public class CellPositionToPixelConverter {
    private Rectangle2D box;
    private GridDimension gd;

    //Constructor
    public CellPositionToPixelConverter(Rectangle2D box, GridDimension gd){
      this.box = box;
      this.gd = gd;
    }
    
    //////////////////////////////////////
    /// Public Methods
    /////////////////////////////////////

    public Rectangle2D getBoundsForCell(CellPosition cp) {
      double cellWidth = box.getWidth() / gd.cols();
      double cellHeight = box.getHeight() / gd.rows();
      double cellX = box.getX() + cp.col() * (cellWidth);
      double cellY = box.getY() + cp.row() * (cellHeight);
      return new Rectangle2D.Double(cellX, cellY, cellWidth, cellHeight);
    }

    /**
     * method takes in the cellposition of the tile and returns a CellData object containing pixels
     * 
     * @param cp, cellposition of the tile
     * @return Celldata
     */
    public CellData getCellData(CellPosition cp) {
      double cellWidth = box.getWidth()/ gd.cols();
      double cellHeight = box.getHeight()/ gd.rows();
      double cellX = box.getX() +  cp.col() * cellWidth;
      double cellY = box.getY() +  cp.row() * cellHeight;
      return new CellData(cellX, cellY, cellWidth, cellHeight);
    }

    /**
     * method uses the pixels of the in the Celldata to draw rectangle
     * 
     * @param cp Cellposition of tile
     * @return Rectangle of tile
     */
    public Rectangle2D getTile(CellPosition cp) {
      return new Rectangle2D.Double(
        getCellData(cp).cellX(), 
        getCellData(cp).cellY(), 
        getCellData(cp).cellWidth(), 
        getCellData(cp).cellHeight()
      );
    }
  }
  