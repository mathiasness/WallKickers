package no.uib.inf101.sem2.grid;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

/**
 * Class represents a grid generic grid with value E.
 */
public class Grid<E> implements IGrid<E> {
    public int rows;
    public int cols;
    public List<List<E>> grid;


    //Default constructor
    public Grid(int rows, int cols) {
        this(rows, cols, null);
    }
    
    //Generic constructor
    public Grid(int rows, int cols, E defaultvalue) {
        this.rows = rows;
        this.cols = cols;

        grid = new ArrayList<>();

        for (int i = 0; i < rows; i ++) {
            grid.add(i, new ArrayList<>());
            for (int j = 0; j < cols; j++){
                grid.get(i).add(j, defaultvalue);
            }
        }
    }

    @Override
    public int rows() {
        return this.rows;
    }

    @Override
    public int cols() {
        return this.cols;
    }

    @Override
    public Iterator<GridCell<E>> iterator() {
        List<GridCell<E>> result = new ArrayList<>();
        for(int i = 0; i < this.rows; ++i){
            for (int j = 0; j < this.cols; ++j){
              result.add(new GridCell<E>(new CellPosition(i, j), this.grid.get(i).get(j)));
            }
          }
        return result.iterator();
    }

    @Override
    public void set(CellPosition pos, E value) {
        this.grid.get(pos.row()).set(pos.col(), value);
    }

    @Override
    public E get(CellPosition pos) {
        E value = this.grid.get(pos.row()).get(pos.col());
        return value;
    }

    @Override
    public boolean positionIsOnGrid(CellPosition pos) {
        if (pos.col() < 0) return false;
        if (pos.row() < 0) return false;
        if (pos.row() >= this.rows) return false;
        if (pos.col() >= this.cols) return false;
        return true;
    } 
}
