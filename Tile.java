package Tetris2D;

import java.awt.Color;
import wheelsunh.users.*;

/**
 * Created a tetrominoid tile.
 * @author Andrew Goddu
 *
 */
public class Tile extends Rectangle
{
    /**
     * Constructor. 
     * Created a square with black frame
     * of size Specs.TILE_SIZE
     */
    public Tile ( )
    {
        super ( );
        setSize ( Specs.TILE_SIZE, Specs.TILE_SIZE );
        setFrameColor ( Color.BLACK );
    }

    /**
     * Only sets the fill color.
     * 
     * @param c Color
     */
    public void setColor ( Color c )
    {
        setFillColor ( c );
    }

    /**
     * Returns the fill color of the tile.
     * @return Color
     */
    public Color getColor ( )
    {
        return super.getFillColor ( );
    }
}
