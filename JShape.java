import wheelsunh.users.*;

/**
 * Constructs an J shaped Tetrominoid.
 * @author Andrew Goddu
 *
 */
public class JShape extends Tetrominoid
{
    /**
     * Sets the color and orientation of the 4 tiles.
     */
    public JShape ( )
    {
        super ( );

        int r1 = 0, r2 = 1, r3 = 2, r4 = 2;
        int c1 = 2, c2 = 2, c3 = 1, c4 = 2;

        config.setPoints ( r1, c1, r2, c2, r3, c3, r4, c4 );

        for ( Tile tile : this )
            tile.setColor ( Specs.J_COLOR );
    }

    /**
     * Main Method for testing.
     * @param args String[]
     */
    public static void main ( String[] args )
    {
        new Frame ( );
        new JShape ( );
    }
}
