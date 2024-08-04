package no.uib.inf101.sem2.wallKickers.model;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.wallKickers.model.player.Position;

/**
 * Class finds the corresponding Cellposition to a Position
 */
public class PixelToCellPosition {
    private CellData area;
    private GridDimension gd;

    //Constructor
    public PixelToCellPosition(CellData area, GridDimension gd) {
        this.area = area;
        this.gd = gd;
    }

    //////////////////////////////////////
    /// Public methods
    /////////////////////////////////////

    /**
     * method checks if the given position is on the grid
     * true if pixel is on the grid
     * 
     * @param pos Position
     * @return boolean
     */
    public boolean pixelIsOnGrid(Position pos) {
        double x = pos.x();
        double y = pos.y();
        if (x < this.area.cellX()) return false;
        else if (x > this.area.cellX() + this.area.cellWidth()) return false;
        else if (y < this.area.cellY()) return false;
        else if (y > this.area.cellY() + this.area.cellHeight()) return false;
        else return true;
    }

    /**
     * method takes in the Position of a pixel and returns the CellPosition of the pixel
     * 
     * @param pos Position
     * @return CellPosition
     */
    public CellPosition getCellPositionOfPixel(Position pos) { //throw?
        double x = pos.x();
        double y = pos.y();
        double areaX = this.area.cellX();
        double areaY = this.area.cellY();
        double areaWidth = this.area.cellWidth();
        double areaHeight = this.area.cellHeight();
        double cellWidth = areaWidth / gd.cols();
        double cellHeight = areaHeight / gd.rows();

        double row = ((y - areaY) / cellHeight);
        double col = ((x - areaX) / cellWidth);
        return new CellPosition((int)row, (int)col);
    }
}
