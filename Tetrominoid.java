package Tetris2D;
import wheelsunh.users.*;
import java.awt.Color;
import java.util.*;

/**
 * Creates a base class for all Tetris shapes.
 * 
 * Has all needed methods
 * 
 * @author Andrew Goddu
 *
 */
public class Tetrominoid extends Vector<Tile>
{
    protected Configuration config;
    protected int row = 0, col = 0;

    /**
     * Creates blank tetrominoid.
     */
    public Tetrominoid ( )
    {
        for ( int i = 0; i < 4; i++ )
        {
            Tile tile = new Tile ( );
            tile.setColor ( Color.GRAY );
            tile.setLocation ( 0, 0 );
            super.add ( tile );
        }

        config = new Configuration ( );
    }

    /**
     * Sets the row and columns.
     * @param r int Row
     * @param c int Column
     */
    public void setRC ( int r, int c )
    {
        row = r;
        col = c;
    }

    /**
     * Draws the tiles on the board.
     */
    public void drawBoard ( )
    {
        // get a graphics configuration from the current configuration
        Configuration graphics = config.getBoardGraphicsCoordinates (
            row, col );

        if ( graphics == null )
            return;

        // Set the locaion of each tile in "tiles"
        for ( int i = 0; i < this.size ( ); i++ )
        {
            Tile tile = this.get ( i );
            tile.setLocation ( graphics.get ( i ) );
        }
    }

    /**
     * Draws the tiles in the preview box.
     */
    public void drawPreview ( )
    {
        // get a graphics configuration from the current configuration
        Configuration graphics = config
            .getPreviewGraphicsCoordinates ( row, col );

        if ( graphics == null )
            return;

        // Set the locaion of each tile in "tiles"
        for ( int i = 0; i < this.size ( ); i++ )
        {
            Tile tile = this.get ( i );
            tile.setLocation ( graphics.get ( i ) );
        }
    }

    /**
     * Rotates all four tiles on the 4X4 grid.
     */
    public void rotate ( )
    {
        if ( config == null )
            return;

        // rotate the config
        config = config.rotate ( );

        // call the draw method
        drawBoard ( );
    }

    /**
     * Moves all the tiles down one block.
     */
    public void fall ( )
    {
        // add one to the row
        row++;

        // call the draw method
        drawBoard ( );
    }

    /**
     * Shifts the tetrominoid to the right.
     */
    public void shiftRight ( )
    {
        col++;
        drawBoard ( );
    }

    /**
     * Shifts the tetrominoid to the left.
     */
    public void shiftLeft ( )
    {
        col--;
        drawBoard ( );
    }

    /**
     * gets the maximun row the shape can go before hitting the bottom.
     * @return max int
     */
    public int getMaxRow ( )
    {
        int max = - 1;

        // calculate and return the maximum row coordinate
        // for the current configuration

        for ( int i = 0; i < config.size ( ); i++ )
        {
            int r = config.getBoardCoordinates ( row, col ).get ( i ).x
                - row;
            if ( r > max )
                max = r;
        }

        return max;
    }

    /**
     * Gets the max row from a specific configuration.
     * Mainly used to test the rotated configuration.
     * @param cfg Configuration Specific tile Config
     * @return max int
     */
    public int getMaxRow ( Configuration cfg )
    {
        int max = - 1;

        // calculate and return the maximum row coordinate
        // for the current configuration

        for ( int i = 0; i < cfg.size ( ); i++ )
        {
            int r = cfg.getBoardCoordinates ( row, col ).get ( i ).x
                - row;
            if ( r > max )
                max = r;
        }

        return max;
    }

    /**
     * Gets the max column the shape can go on the board.
     * @return max int
     */
    public int getMaxCol ( )
    {
        int max = - 4;

        for ( int i = 0; i < config.size ( ); i++ )
        {
            int c = config.getBoardCoordinates ( row, col ).get ( i ).y
                - col;
            if ( c > max )
                max = c;
        }

        return max;
    }

    /**
     * Gets the max column from a specific configuration.
     * Mainly used to test the rotated configuration.
     * @param cfg Configuration Specific tile Config
     * @return max int
     */
    public int getMaxCol ( Configuration cfg )
    {
        int max = - 4;

        for ( int i = 0; i < cfg.size ( ); i++ )
        {
            int c = cfg.getBoardCoordinates ( row, col ).get ( i ).y
                - col;
            if ( c > max )
                max = c;
        }

        return max;
    }

    /**
     * Gets the min column the shape can go on the board.
     * @return min int
     */
    public int getMinCol ( )
    {
        int min = Specs.BOARD_COLS;

        for ( int i = 0; i < config.size ( ); i++ )
        {
            int c = config.getBoardCoordinates ( row, col ).get ( i ).y
                - col;
            if ( c < min )
                min = c;
        }

        return min;
    }

    /**
     * Gets the min column from a specific configuration.
     * Mainly used to test the rotated configuration.
     * @param cfg Configuration Specific tile Config
     * @return max int
     */
    public int getMinCol ( Configuration cfg )
    {
        int min = Specs.BOARD_COLS;

        for ( int i = 0; i < cfg.size ( ); i++ )
        {
            int c = cfg.getBoardCoordinates ( row, col ).get ( i ).y
                - col;
            if ( c < min )
                min = c;
        }

        return min;
    }

    /**
     * Returns the current row.
     * @return row int
     */
    public int getRow ( )
    {
        return row;
    }

    /**
     * Returns the current column.
     * @return col int
     */
    public int getCol ( )
    {
        return col;
    }

    /**
     * Returns the current configuration.
     * @return config Configuration
     */
    public Configuration getConfig ( )
    {
        return config;
    }
}
