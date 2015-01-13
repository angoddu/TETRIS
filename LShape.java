import wheelsunh.users.*;

/**
 * Constructs an L shaped Tetrominoid.
 * @author Andrew Goddu
 *
 */
public class LShape extends Tetrominoid
{
    /**
     * Sets the color and orientation of the 4 tiles.
     */
    public LShape ( )
    {
        super ( );

        int r1 = 0, r2 = 1, r3 = 2, r4 = 2;
        int c1 = 1, c2 = 1, c3 = 2, c4 = 1;

        config.setPoints ( r1, c1, r2, c2, r3, c3, r4, c4 );

        for ( Tile tile : this )
            tile.setColor ( Specs.L_COLOR );
    }

    /**
     * Main Method for testing.
     * @param args String[]
     */
    public static void main ( String[] args )
    {
        new Frame ( );
        new LShape ( );
    }
}
