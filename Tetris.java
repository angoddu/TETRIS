import wheelsunh.users.*;
import java.util.*;
import java.awt.Point;
import java.awt.event.*;

/**
 * Tetris.java.
 * Creates a fully functional tetris game
 * @author Andrew Goddu
 *
 */
public class Tetris implements Animator, KeyListener
{
    private Tile[][] board;
    private Tetrominoid shape;
    private Tetrominoid nextShape;
    private Random gen;
    private AnimationTimer timer;
    private TextBox infoPanel;

    private int rowsCleared = 0;
    private int gameScore = 0;
    private int gameLevel = 1;
    private int delay = 1000;
    private boolean gameOver = false;
    private boolean pause = false;

    /**
     * Makes the board and new shape and then starts the timer.
     */
    public Tetris ( )
    {
        board = new Tile[Specs.BOARD_ROWS][Specs.BOARD_COLS];
        gen = new Random ( );
        makeBoard ( );

        shape = getNewShape ( );
        shape.setRC ( 0, 3 );
        shape.drawBoard ( );
        nextShape = getNewShape ( );
        nextShape.drawPreview ( );

        infoPanel = new TextBox ( );
        infoPanel.setLocation ( Specs.TEXTBOX_X, Specs.TEXTBOX_Y );

        timer = new AnimationTimer ( delay, this );
        timer.start ( );

        setInfoText ( );
    }

    /**
     * Makes the board.
     */
    public void makeBoard ( )
    {
        for ( int r = 0; r < Specs.BOARD_ROWS; r++ )
        {
            for ( int c = 0; c < Specs.BOARD_COLS; c++ )
            {
                int x = Specs.BOARD_X + c * Specs.TILE_SIZE;
                int y = Specs.BOARD_Y + r * Specs.TILE_SIZE;

                board[r][c] = new Tile ( );
                board[r][c].setColor ( Specs.BOARD_COLOR );
                board[r][c].setLocation ( x, y );
            }
        }
    }

    /**
     * Creates a new random Tetrominoid.
     * Sets it to the preview window
     * 
     * @return Tetrominoid
     */
    public Tetrominoid getNewShape ( )
    {
        Tetrominoid tet;

        int shapeNumber = gen.nextInt ( 7 ) + 1;

        switch ( shapeNumber )
        {
            case 1:
                tet = new JShape ( );
                break;
            case 2:
                tet = new LShape ( );
                break;
            case 3:
                tet = new IShape ( );
                break;
            case 4:
                tet = new ZShape ( );
                break;
            case 5:
                tet = new SShape ( );
                break;
            case 6:
                tet = new OShape ( );
                break;
            case 7:
                tet = new TShape ( );
                break;
            default:
                tet = new Tetrominoid ( );
        }

        return tet;
    }

    /**
     * Sets the info panel text.
     */
    public void setInfoText ( )
    {
        infoPanel.setText ( "TETRIS" + "\nLEVEL: " + gameLevel
            + "\nROWS: " + rowsCleared + "\nSCORE: " + gameScore );
    }

    /**
     * locks the shape into the board.
     */
    public void lockShape ( )
    {
        Configuration temp = shape.getConfig ( ).getBoardCoordinates (
            shape.getRow ( ), shape.getCol ( ) );

        for ( int i = 0; i < temp.size ( ); i++ )
        {
            int r = temp.get ( i ).x;
            int c = temp.get ( i ).y;
            board[r][c] = shape.get ( i );
        }
    }

    /**
     * Loops through the board and checks if the board has any full rows.
     * Calls clear if there are
     */
    public void checkClear ( )
    {
        int simClear = 0;

        for ( int r = 0; r < Specs.BOARD_ROWS; r++ )
        {
            boolean[] filledRow = new boolean[Specs.BOARD_COLS];

            for ( int c = 0; c < Specs.BOARD_COLS; c++ )
            {
                if ( board[r][c].getColor ( ) != Specs.BOARD_COLOR )
                {
                    filledRow[c] = true;
                } else
                    filledRow[c] = false;
            }

            boolean allTrue = true;
            for ( boolean bool : filledRow )
            {
                if ( ! bool )
                    allTrue = false;
            }

            if ( allTrue )
            {
                clear ( r );
                simClear++;
                rowsCleared++;
                checkLevel ( );
            }
        }

        if ( simClear != 0 )
        {
            score ( simClear );
            setInfoText ( );
        }
    }

    /**
     * Clears the row and moves everything down.
     * @param row int Row to clear
     */
    public void clear ( int row )
    {
        for ( int r = row; r >= 0; r-- )
        {
            for ( int c = 0; c < Specs.BOARD_COLS; c++ )
            {
                if ( r - 1 >= 0 )
                    board[r][c].setColor ( board[r - 1][c]
                        .getColor ( ) );
                else
                    board[r][c].setColor ( Specs.BOARD_COLOR );
            }
        }
    }

    /**
     * score.
     * Takes the number of rows cleared and adds points
     * to your score accordingly
     * @param rows int Number of rows cleared at once
     */
    public void score ( int rows )
    {
        switch ( rows )
        {
            case 1:
                gameScore += 40;
                break;
            case 2:
                gameScore += 100;
                break;
            case 3:
                gameScore += 300;
                break;
            case 4:
                gameScore += 1200;
                break;
        }
    }

    /**
     * Checks the number of rows cleared and sets the timer accordingly.
     */
    public void checkLevel ( )
    {
        if ( rowsCleared % 10 == 0 && rowsCleared != 0 )
        {
            delay = ( 4 * delay ) / 5;

            if ( delay <= 100 )
            {
                delay = 100;
            }

            timer.setDelay ( delay );
            gameLevel++;
        }
    }

    /**
     * Checks if the game is over.
     */
    public void checkGameOver ( )
    {
        Configuration nextConfig = nextShape.getConfig ( )
            .getBoardCoordinates ( 0, 3 );

        for ( Point p : nextConfig )
        {
            if ( board[p.x][p.y].getColor ( ) != Specs.BOARD_COLOR )
            {
                gameOver = true;
            }
        }
    }

    /**
     * Checks if there is room to fall.
     * @return canFall boolean
     */
    public boolean canFall ( )
    {
        int max = shape.getMaxRow ( ) + shape.getRow ( );
        boolean canFall = max < Specs.BOARD_ROWS - 1;

        return canFall;// still room to fall
    }

    /**
     * Checks if there is room to shift to the right.
     * @return canShiftRight boolean
     */
    public boolean canShiftRight ( )
    {
        int max = shape.getMaxCol ( ) + shape.getCol ( );
        boolean canShiftRight = max < Specs.BOARD_COLS - 1;

        return canShiftRight;
    }

    /**
     * Checks if there is room to shift to the left.
     * @return canShiftLeft boolean
     */
    public boolean canShiftLeft ( )
    {
        int min = shape.getMinCol ( ) + shape.getCol ( );
        boolean canShiftLeft = min > 0;

        return canShiftLeft;
    }

    /**
     * Checks if there is room to rotate.
     * @return canRotate boolean
     */
    public boolean canRotate ( )
    {
        boolean canRotate = true;

        Configuration rotated = shape.getConfig ( ).rotate ( );

        int minCol = shape.getMinCol ( rotated ) + shape.getCol ( );
        int maxCol = shape.getMaxCol ( rotated ) + shape.getCol ( );
        int maxRow = shape.getMaxRow ( rotated ) + shape.getRow ( );

        if ( maxCol >= Specs.BOARD_COLS || minCol < 0
            || maxRow >= Specs.BOARD_ROWS )
            canRotate = false;

        return canRotate;
    }

    /**
     * Checks weather or not the shape will collide with another previous one.
     * @param type int the type of movement
     *        1 - fall, 2 - left, 3 - right, 4 - rotate
     * @return willCollide boolean
     */
    public boolean willCollide ( int type )
    {

        boolean willCollide = false;
        boolean[][] boardFill = new boolean[Specs.BOARD_ROWS][Specs.BOARD_COLS];

        for ( int r = 0; r < Specs.BOARD_ROWS; r++ )
        {
            for ( int c = 0; c < Specs.BOARD_COLS; c++ )
            {
                if ( board[r][c].getColor ( ) != Specs.BOARD_COLOR )
                {
                    boardFill[r][c] = true;
                } else
                    boardFill[r][c] = false;
            }
        }

        int row = shape.getRow ( );
        int col = shape.getCol ( );

        Configuration config = shape.getConfig ( );

        switch ( type )
        {
            case 1:
                row++;
                break;
            case 2:
                col--;
                break;
            case 3:
                col++;
                break;
            case 4:
                config = config.rotate ( );
                break;
            default:
                System.out.println ( "Invalid type: willCollide("
                    + type + ")" );
        }

        config = config.getBoardCoordinates ( row, col );

        for ( Point p : config )
        {
            if ( boardFill[p.x][p.y] )
                willCollide = true;
        }

        return willCollide;
    }

    /**
     * Advances the game.
     */
    public void advance ( )
    {
        lockShape ( );
        checkClear ( );
        checkGameOver ( );
        shape = nextShape;
        shape.setRC ( 0, 3 );
        shape.drawBoard ( );

        if ( gameOver )
        {
            timer.stop ( );
            infoPanel.setText ( "TETRIS" + "\nLEVEL: " + gameLevel
                + "\nROWS: " + rowsCleared + "\nScore: " + gameScore
                + "\nGAME OVER" + "\nPress 'R' to restart" );
        } else
        {
            nextShape = getNewShape ( );
            nextShape.drawPreview ( );
        }
    }

    /**
     * Pauses the animation.
     */
    public void pause ( )
    {

        pause = ! pause;

        if ( pause )
        {
            timer.stop ( );
            infoPanel.setText ( "TETRIS" + "\nLEVEL: " + gameLevel
                + "\nROWS: " + rowsCleared + "\nSCORE: " + gameScore
                + "\nPAUSED" );
        } else
        {
            timer.start ( );
            setInfoText ( );
        }
    }

    /**
     * Starts a new game.
     */
    public void reset ( )
    {
        lockShape ( );
        for ( int r = 0; r < Specs.BOARD_ROWS; r++ )
            for ( int c = 0; c < Specs.BOARD_COLS; c++ )
                board[r][c].setColor ( Specs.BOARD_COLOR );

        gameScore = 0;
        rowsCleared = 0;
        gameLevel = 1;
        gameOver = false;
        setInfoText ( );

        delay = 1000;
        timer.setDelay ( delay );

        shape = getNewShape ( );
        shape.setRC ( 0, 3 );
        shape.drawBoard ( );
        nextShape = getNewShape ( );
        nextShape.drawPreview ( );

        timer.start ( );
    }

    /**
     * Drops the shape or makes a new one.
     */
    public void animate ( )
    {
        if ( canFall ( ) && ! willCollide ( 1 ) )
            shape.fall ( );
        else
            advance ( );
    }

    /**
     * User controls.
     * 
     * @param e MouseEvent
     */
    public void keyPressed ( KeyEvent e )
    {
        int code = e.getKeyCode ( );
        if ( ! gameOver )
        {

            if ( code == KeyEvent.VK_SPACE )
            {
                pause ( );
            }

            if ( ! pause )
            {
                switch ( code )
                {
                    case KeyEvent.VK_DOWN:
                        if ( canFall ( ) && ! willCollide ( 1 ) )
                            shape.fall ( );
                        else
                        {
                            advance ( );
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        if ( canShiftLeft ( ) && ! willCollide ( 2 ) )
                            shape.shiftLeft ( );
                        break;
                    case KeyEvent.VK_RIGHT:
                        if ( canShiftRight ( ) && ! willCollide ( 3 ) )
                            shape.shiftRight ( );
                        break;
                    case KeyEvent.VK_UP:
                        if ( canRotate ( ) && ! willCollide ( 4 ) )
                            shape.rotate ( );
                        break;
                }
            }
        } else
        {
            if ( code == KeyEvent.VK_R )
                reset ( );
        }
    }

    /**
     * Unneeded.
     * 
     * @param e MouseEvent
     */
    public void keyTyped ( KeyEvent e )
    {
        // Unneeded
    }

    /**
     * Unneeded.
     * 
     * @param e MouseEvent
     */
    public void keyReleased ( KeyEvent e )
    {
        // Unneeded

    }

    /**
     * Starts the game and adds a key listener.
     * @param args String[]
     */
    public static void main ( String[] args )
    {
        Frame frame = new Frame ( );
        Tetris shape = new Tetris ( );

        frame.addKeyListener ( shape );
    }
}