package Tetris2D;
import java.awt.event.*;

/**
 * Chapter 7: AnimationTimer.java.
 * A subclass of Timer that can be used for animation.
 * It also serves as an example of the code for an "event source" object.
 * Version 2 of 2
 * 
 * @author mb
 */
public class AnimationTimer extends javax.swing.Timer
{
    private Animator _animator; // peer object

    /**
     * Animates an object.
     * @param anInterval int Time interval in milliseconds
     * @param a Animator An anamatable object
     */
    public AnimationTimer ( int anInterval, Animator a )
    {
        super ( anInterval, null );
        _animator = a;
        this.addActionListener ( new AnimationListener ( ) );
    }

    /**
     * Private class used to animate the object.
     * @author mb
     *
     */
    private class AnimationListener implements ActionListener
    {
        /**
         * calls animate.
         * 
         * @param e ActionEvent
         */
        public void actionPerformed ( ActionEvent e )
        {
            _animator.animate ( );
        }
    }
}
