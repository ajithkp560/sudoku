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
import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.*;

public class SudokuSolverUI extends JPanel
{
    public JTextField view[][];
    public int[][] a = new int[9][9];
    public int pG=0;
    public JPanel Xpanel,gridPanel, upView,superPanel, nPanel;
    private JButton button;
    public String str;
    private JLabel left;
    private final JProgressBar progressX=new JProgressBar();
    public JTextField[][] field = new JTextField[9][9];
    public SudokuSolverUI()
    {
        superPanel=new JPanel(new FlowLayout());
        gridPanel=new JPanel(new GridLayout(9,9));
        gridPanel.setSize(400,400);
        upView=new JPanel(new GridLayout(9,9));
        upView.setSize(400,400);
        view=new JTextField[9][9];
        left=new JLabel();
        left.setText("<html><b><font color='red'>WWW.TERMINALCODERS.BLOGSPOT.IN</font></b></html>");
        add(left, BorderLayout.SOUTH);
        gridPanel.setBorder(BorderFactory.createMatteBorder(5,5,5,5, Color.BLACK));
        upView.setBorder(BorderFactory.createMatteBorder(5,5,5,5, Color.BLACK));
        gridPanel.setBackground(Color.BLACK);
        upView.setBackground(Color.BLACK);
        Xpanel=new JPanel(new GridLayout(1,1));
        Xpanel.setBorder(BorderFactory.createMatteBorder(5,5,5,5, Color.GREEN));
        button=new JButton("PLAY >>");
        Xpanel.add(button);
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                a[i][j]=0;
                view[i][j]=new JTextField();
                field[i][j]=new JTextField();
                field[i][j].setColumns(2);
                view[i][j].setColumns(2);
                view[i][j].setEditable(false);
                if((i<3 && j<3) || (i>2 && i<6 && j>2 && j<6) || (i<3 && j<9 && j>5) || (j<3 && i<9 && i>5) || (i>5 && i<9 && j>5 && j<9))
                {
                    field[i][j].setBackground(Color.YELLOW);
                    view[i][j].setBackground(Color.YELLOW);
                }
                else
                {
                    field[i][j].setBackground(Color.PINK);
                    view[i][j].setBackground(Color.PINK);
                }
                field[i][j].setDocument(new NumericalDocument());
                gridPanel.add(field[i][j]);
                upView.add(view[i][j]);
            }
        }
        superPanel.add(gridPanel,BorderLayout.WEST);
        superPanel.add(Xpanel);
        superPanel.add(upView,BorderLayout.EAST);
        nPanel=new JPanel();
        nPanel.setBorder(BorderFactory.createMatteBorder(5,0,5,0, Color.darkGray));
        nPanel.add(progressX, BorderLayout.AFTER_LINE_ENDS);
        progressX.setBackground(Color.WHITE);
        add(superPanel);
        add(nPanel, BorderLayout.SOUTH);
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                button.setEnabled(false);
                progressX.setValue(0);
                progressX.setStringPainted(true);
                for(int i=0;i<9;i++)
                {
                    for(int j=0;j<9;j++)
                    {
                        try
                        {
                            a[i][j]=Integer.parseInt(field[i][j].getText());
                        }
                        catch(NumberFormatException e)
                        {
                            a[i][j]=0;
                        }
                    }
                }
                try
                {
                    solve(0, 0);
                }
                catch (Exception ex)
                {
                }
            }
        });
    }

    public static class NumericalDocument extends PlainDocument
    {
        String numbers = "123456789";
        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException
        {
            if (getLength() == 0 && str.length() == 1 && numbers.contains(str))
            {
                super.insertString(offs, str,(AttributeSet) a);
            }
            else
            {
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }
    protected boolean checkRow(int i,int num)
    {
        for(int j=0;j<9;j++)
        {
            if(a[i][j]==num)
            {
                return false;
            }
        }
        return true;
    }
    protected boolean checkCol(int j,int num)
    {
        for(int i=0;i<9;i++)
        {
            if(a[i][j]==num)
            {
                return false;
            }
        }
        return true;
    }
    protected boolean checkBox(int i, int j, int num)
    {
        i=(i/3)*3;
        j=(j/3)*3;
        for(int r=0;r<3;r++)
        {
            for(int c=0;c<3;c++)
            {
                if(a[i+r][j+c]==num)
                {
                    return false;
                }
            }
        }
        return true;
    }
    public void solve(int i, int j) throws Exception
    {
        if(i>8)
        {
            button.setEnabled(true);
            progressX.setValue(100);
            JOptionPane.showMessageDialog(null, "Execution Completed!!!\nSudoku Solved!!!");
        }
        if(a[i][j]!=0)
        {
            next(i,j);
        }
        else
        {
            for(int num=1;num<10;num++)
            {
                if(checkRow(i,num) && checkCol(j,num) && checkBox(i,j,num))
                {
                    a[i][j]=num;
                    XView();
                    next(i,j);
                }
            }
            a[i][j]=0;
            XView();
        }
    }
    public void next(int i, int j) throws Exception
    {
        if(j<8)
        {
            solve(i, j+1);
            progressX.setValue(pG+11);
        }
        else
        {
            solve(i+1,0);
        }
    }
    protected void XView()
    {
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                if(a[i][j]!=0)
                {
                    view[i][j].setText(String.valueOf(a[i][j]));
                }
                else
                {
                    view[i][j].setText("");
                }
            }
        }
    }
}
