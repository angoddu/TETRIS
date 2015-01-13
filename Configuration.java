package Tetris2D;
import java.util.*;
import java.awt.*;

/**
 * Configuration.java.
 * 
 *  Represents the four row-column 2D array coordinates for the tiles 
 *  of a tetris shape. 
 *  
 * @author mlb fall 2014
 */
public class Configuration extends Vector<Point>
{

    /******************************************************************/
    /**
     * Default Constructor. Constructs an empty collection.
     */
    public Configuration ( )
    {
        // Creates an empty collection (no more code needed)
    }

    /**
     * Constructor.  8 integers representing the four row-column 
     * coordinates for the four tiles of a tetris shape.
     * 
     * @param r1 int
     * @param c1 int
     * 
     * @param r2 int
     * @param c2 int
     * 
     * @param r3 int
     * @param c3 int
     * 
     * @param r4 int
     * @param c4 int
     * 
     */
    public Configuration ( int r1, int c1, int r2, int c2, int r3,
        int c3, int r4, int c4 )
    {
        // convert the 4 pairs to 4 Points and add them to the Configuration

        setPoints ( r1, c1, r2, c2, r3, c3, r4, c4 );

    }

    /**
     * Adds the points to the super class Vector.
     * @param r1 int
     * @param c1 int
     * 
     * @param r2 int
     * @param c2 int
     * 
     * @param r3 int
     * @param c3 int
     * 
     * @param r4 int
     * @param c4 int
     */
    public void setPoints ( int r1, int c1, int r2, int c2, int r3,
        int c3, int r4, int c4 )
    {
        super.add ( new Point ( r1, c1 ) );
        super.add ( new Point ( r2, c2 ) );
        super.add ( new Point ( r3, c3 ) );
        super.add ( new Point ( r4, c4 ) );
    }

    /******************************************************************/
    /**
     * getGraphicsCoordinates. Converts the local row, column coordinates
     * of a Shape to graphics x, y coordinates.
     * 
     * @param row int  
     * @param col int
     * 
     * @return Configuration
     * 
     */
    public Configuration getBoardGraphicsCoordinates ( int row,
        int col )
    {
        // get a configuration of the board coordinates

        Configuration board = this.getBoardCoordinates ( row, col );

        // convert board coordinates to a configuration of graphics coodinates.
        // for example the x and y of the first tile are:
        // x = Specs.BOARD_X + bdc.get( 0 ).y * Specs.TILE_SIZE
        // y = Specs.BOARD_Y + bdc.get( 0 ).x * Specs.TILE_SIZE,
        Configuration graphics;

        int x1 = Specs.BOARD_X + board.get ( 0 ).y * Specs.TILE_SIZE;
        int x2 = Specs.BOARD_X + board.get ( 1 ).y * Specs.TILE_SIZE;
        int x3 = Specs.BOARD_X + board.get ( 2 ).y * Specs.TILE_SIZE;
        int x4 = Specs.BOARD_X + board.get ( 3 ).y * Specs.TILE_SIZE;

        int y1 = Specs.BOARD_Y + board.get ( 0 ).x * Specs.TILE_SIZE;
        int y2 = Specs.BOARD_Y + board.get ( 1 ).x * Specs.TILE_SIZE;
        int y3 = Specs.BOARD_Y + board.get ( 2 ).x * Specs.TILE_SIZE;
        int y4 = Specs.BOARD_Y + board.get ( 3 ).x * Specs.TILE_SIZE;

        graphics = new Configuration ( x1, y1, x2, y2, x3, y3, x4, y4 );

        return graphics;
    }

    /******************************************************************/
    /**
     * getPreviewGraphicsCoordinates. Converts the local row, column coordinates
     * of a Shape to graphics x, y coordinates.
     * 
     * @param row int  
     * @param col int
     * 
     * @return Configuration
     * 
     */
    public Configuration getPreviewGraphicsCoordinates ( int row,
        int col )
    {
        // get a configuration of the board coordinates

        Configuration board = this.getBoardCoordinates ( row, col );

        Configuration graphics;

        int x1 = Specs.PREVIEW_X + board.get ( 0 ).y
            * Specs.TILE_SIZE;
        int x2 = Specs.PREVIEW_X + board.get ( 1 ).y
            * Specs.TILE_SIZE;
        int x3 = Specs.PREVIEW_X + board.get ( 2 ).y
            * Specs.TILE_SIZE;
        int x4 = Specs.PREVIEW_X + board.get ( 3 ).y
            * Specs.TILE_SIZE;

        int y1 = Specs.PREVIEW_Y + board.get ( 0 ).x
            * Specs.TILE_SIZE;
        int y2 = Specs.PREVIEW_Y + board.get ( 1 ).x
            * Specs.TILE_SIZE;
        int y3 = Specs.PREVIEW_Y + board.get ( 2 ).x
            * Specs.TILE_SIZE;
        int y4 = Specs.PREVIEW_Y + board.get ( 3 ).x
            * Specs.TILE_SIZE;

        graphics = new Configuration ( x1, y1, x2, y2, x3, y3, x4, y4 );

        return graphics;
    }

    /******************************************************************/
    /**
     * getBoardCoordinates. Returns the board coordinates for this Shape.
     * 
     * @param r int
     * @param c int
     * 
     * @return Configuration
     * 
     */
    public Configuration getBoardCoordinates ( int r, int c )
    {
        Configuration board;

        int r1 = r + this.get ( 0 ).x, r2 = r + this.get ( 1 ).x, r3 = r
            + this.get ( 2 ).x, r4 = r + this.get ( 3 ).x;

        int c1 = c + this.get ( 0 ).y, c2 = c + this.get ( 1 ).y, c3 = c
            + this.get ( 2 ).y, c4 = c + this.get ( 3 ).y;

        board = new Configuration ( r1, c1, r2, c2, r3, c3, r4, c4 );

        return board;
    }

    /******************************************************************/
    /**
     *  Rotate clockwise.  returns a new Configuration with the coordinates
     *  transformed for a clockwise rotation.
     * 
     * @return Configuration
     */
    public Configuration rotate ( )
    {
        Configuration rotated = new Configuration ( );

        // Transform each Point: (r, c ) -> (c, 3 - r)

        for ( Point p : this )
            rotated.add ( new Point ( p.y, ( 3 - p.x ) ) );

        return rotated;
    }

    /******************************************************************/
    /**
     *   Convert to a String.
     * 
     * @return String
     */
    public String toString ( )
    {
        if ( size ( ) == 0 )
            return "Empty configuration";

        String s = "";
        for ( int i = 0; i < size ( ); i++ )
            s += ( "(" + get ( i ).x + ", " + get ( i ).y + ")  " );

        return s;
    }

    // ---------------------- testing main ------------------------------------
    /**
     * Testing main.
     * 
     * @param args String[]
     * 
     * 
     */
    public static void main ( String[] args )
    {

        Configuration c = new Configuration ( 1, 0, 1, 1, 1, 2, 2, 1 );

        // test original configuration
        try
        {
            System.out.println ( "Your config : " + c );
            System.out.println ( "It should be  (1, 0)  (1, 1)  "
                + "(1, 2)  (2, 1) \n" );
        } catch ( Exception e )
        {
            System.out.println ( "Your original config is null." );
            System.out.println ( e.getMessage ( ) );
            return;
        }

        // test rotated configuration
        try
        {
            c = c.rotate ( );
            System.out.println ( "Rotate:    " + c );
            System.out.println ( "Should be  (0, 2)  (1, 2) "
                + " (2, 2)  (1, 1) \n" );
        } catch ( Exception e )
        {
            System.out.println ( "Your rotated config is null." );
            System.out.println ( e.getMessage ( ) );
            return;
        }

        // test board configuration
        try
        {
            Configuration b = c.getBoardCoordinates ( 5, 6 );
            System.out.println ( "At Board (5,6):  " + b );
            System.out.println ( "Should be at     "
                + "(5, 8)  (6, 8)  (7, 8)  (6, 7) \n" );
        } catch ( Exception e )
        {
            System.out.println ( "Your board config is null." );
            System.out.println ( e.getMessage ( ) );
            return;
        }

        // test graphics configuration
        try
        {
            Configuration g = c.getBoardGraphicsCoordinates ( 10, 20 );
            System.out.println ( "Graphics locations:  " + g );
            System.out
                .println ( "Should be at         (640, 280)  (640, 300)"
                    + "  (640, 320)  (620, 300)  \n" );
        } catch ( Exception e )
        {
            System.out.println ( "Your graphics config is null." );
            System.out.println ( e.getMessage ( ) );
            return;
        }
    }

} // END
