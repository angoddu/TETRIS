import wheelsunh.users.*;

/**
 * Constructs an T shaped Tetrominoid.
 * @author Andrew Goddu
 *
 */
public class TShape extends Tetrominoid
{
    /**
     * Sets the color and orientation of the 4 tiles.
     */
    public TShape ( )
    {
        super ( );

        int r1 = 1, r2 = 1, r3 = 1, r4 = 2;
        int c1 = 1, c2 = 2, c3 = 3, c4 = 2;

        config.setPoints ( r1, c1, r2, c2, r3, c3, r4, c4 );

        for ( Tile tile : this )
            tile.setColor ( Specs.T_COLOR );
    }

    /**
     * Main Method for testing.
     * @param args String[]
     */
    public static void main ( String[] args )
    {
        new Frame ( );
        new TShape ( );
    }
}
