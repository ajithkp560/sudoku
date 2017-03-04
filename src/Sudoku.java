/*
 *
 * Coded By AJITH KP and Jhelai Sahadevan
 *
 * Greets to Amsteck Arts & Science College BCA & BSc Physics
 *
 * (c) AJITH KP [ https://www.facebook.com/ajithkp560 ] (c)
 *
 * http://www.TerminalCoders.BlogSpot.in
 *
 */
import javax.swing.*;
public class Sudoku extends JFrame
{
    public static void main(String[] args)
    {
        SudokuSolverUI ff=new SudokuSolverUI();
        JFrame frame = new JFrame("Sudoku Solver By Ajith Kp");
        frame.add(ff);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(620, 320);
        frame.setVisible(true);
    }
}