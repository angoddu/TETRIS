package Tetris2D;

import wheelsunh.users.Frame;
import wheelsunh.users.*;

/**
 * Constructs an I shaped Tetrominoid.
 * @author Andrew Goddu
 *
 */
public class IShape extends Tetrominoid
{
    /**
     * Sets the color and orientation of the 4 tiles.
     */
    public IShape ( )
    {
        super ( );

        int r1 = 0, r2 = 1, r3 = 2, r4 = 3;
        int c1 = 2, c2 = 2, c3 = 2, c4 = 2;

        config.setPoints ( r1, c1, r2, c2, r3, c3, r4, c4 );

        for ( Tile tile : this )
            tile.setColor ( Specs.I_COLOR );
    }

    /**
     * Main Method for testing.
     * @param args String[]
     */
    public static void main ( String[] args )
    {
        new Frame ( );
        new IShape ( );
    }
}
