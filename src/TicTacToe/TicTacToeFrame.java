package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class TicTacToeFrame extends JFrame {
    private JPanel mainPnl = new JPanel();
    private static JPanel gamePnl = new JPanel();
    private JPanel titlePnl = new JPanel();
    private JPanel cmdPnl = new JPanel();
    private JLabel titleLbl;
    private static TicTacToeTile[][] board = new TicTacToeTile[3][3];
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String player = "X";
    JButton clearBtn;
    JButton quitBtn;

    boolean finished = false;
    static boolean playing = true;
    Scanner in = new Scanner(System.in);
    static int moveCnt = 0;
     static int row = -1;
     static int col = -1;
    static final int MOVES_FOR_WIN = 5;
    static final int MOVES_FOR_TIE = 7;
    static TicTacToeTile buttonClicked;

    public TicTacToeFrame(){

        player = "X";
        moveCnt = 0;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Tic Tac Toe!");
        add(mainPnl);

        mainPnl.setLayout(new BorderLayout());

        createTitlePanel();
        mainPnl.add(titlePnl,BorderLayout.NORTH);

        createGamePanel();
        mainPnl.add(gamePnl,BorderLayout.CENTER);

        createCmdPanel();
        mainPnl.add(cmdPnl,BorderLayout.SOUTH);


        pack();
        setVisible(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int)(.75*screenSize.width),(int)(.75*screenSize.height));
        setLocationRelativeTo(null);
    }

private static void gamePlayer(){
        row = buttonClicked.getRow();
        col = buttonClicked.getCol();

        moveCnt++;

        if (moveCnt >= MOVES_FOR_WIN) {
            if (isWin(player)) {
                JOptionPane.showMessageDialog(gamePnl, "Player " + player + " wins!");
                clearBoard();
                //System.out.println("Player " + player + " wins!");
            }
        }
        if (moveCnt >= MOVES_FOR_TIE) {
            if (isTie()) {
                JOptionPane.showMessageDialog(gamePnl, "It's a Tie!");
                clearBoard();
                //System.out.println("It's a Tie!");
            }
        }
        if (player.equals("X")) {
            player = "O";
        } else {
            player = "X";
        }
}

private static boolean isWin(String player)
{
    if(isColWin(player) || isRowWin(player) || isDiagnalWin(player))
    {
        return true;
    }

    return false;
}
private static boolean isColWin(String player)
{
    // checks for a col win for specified player
    for(int col=0; col < COL; col++)
    {
        if(board[0][col].getText().equals(player) &&
                board[1][col].getText().equals(player) &&
                board[2][col].getText().equals(player))
        {
            return true;
        }
    }
    return false; // no col win
}
private static boolean isRowWin(String player)
{
    // checks for a row win for the specified player
    for(int row=0; row < ROW; row++)
    {
        if(board[row][0].getText().equals(player) &&
                board[row][1].getText().equals(player) &&
                board[row][2].getText().equals(player))
        {
            return true;
        }
    }
    return false; // no row win
}
private static boolean isDiagnalWin(String player)
{
    // checks for a diagonal win for the specified player

    if(board[0][0].getText().equals(player) &&
            board[1][1].getText().equals(player) &&
            board[2][2].getText().equals(player) )
    {
        return true;
    }
    if(board[0][2].getText().equals(player) &&
            board[1][1].getText().equals(player) &&
            board[2][0].getText().equals(player) )
    {
        return true;
    }
    return false;
}

// checks for a tie before board is filled.
// check for the win first to be efficient
private static boolean isTie()
{
    boolean xFlag = false;
    boolean oFlag = false;
    // Check all 8 win vectors for an X and O so
    // no win is possible
    // Check for row ties
    for(int row=0; row < ROW; row++)
    {
        if(board[row][0].getText().equals("X") ||
                board[row][1].getText().equals("X") ||
                board[row][2].getText().equals("X"))
        {
            xFlag = true; // there is an X in this row
        }
        if(board[row][0].getText().equals("O") ||
                board[row][1].getText().equals("O") ||
                board[row][2].getText().equals("O"))
        {
            oFlag = true; // there is an O in this row
        }

        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a row win
        }

        xFlag = oFlag = false;

    }
    // Now scan the columns
    for(int col=0; col < COL; col++)
    {
        if(board[0][col].getText().equals("X") ||
                board[1][col].getText().equals("X") ||
                board[2][col].getText().equals("X"))
        {
            xFlag = true; // there is an X in this col
        }
        if(board[0][col].getText().equals("O") ||
                board[1][col].getText().equals("O") ||
                board[2][col].getText().equals("O"))
        {
            oFlag = true; // there is an O in this col
        }

        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a col win
        }
    }
    // Now check for the diagonals
    xFlag = oFlag = false;

    if(board[0][0].getText().equals("X") ||
            board[1][1].getText().equals("X") ||
            board[2][2].getText().equals("X") )
    {
        xFlag = true;
    }
    if(board[0][0].getText().equals("O") ||
            board[1][1].getText().equals("O") ||
            board[2][2].getText().equals("O") )
    {
        oFlag = true;
    }
    if(! (xFlag && oFlag) )
    {
        return false; // No tie can still have a diag win
    }
    xFlag = oFlag = false;

    if(board[0][2].getText().equals("X") ||
            board[1][1].getText().equals("X") ||
            board[2][0].getText().equals("X") )
    {
        xFlag =  true;
    }
    if(board[0][2].getText().equals("O") ||
            board[1][1].getText().equals("O") ||
            board[2][0].getText().equals("O") )
    {
        oFlag =  true;
    }
    if(! (xFlag && oFlag) )
    {
        return false; // No tie can still have a diag win
    }

    // Checked every vector so I know I have a tie
    return true;
}

//Panel Methods


    public void createTitlePanel(){
        titleLbl = new JLabel("Tic Tac Toe!");
        titleLbl.setFont(new Font("Impact", Font.BOLD, 48));
        titleLbl.setHorizontalTextPosition(JLabel.CENTER);
        titleLbl.setVerticalTextPosition(JLabel.BOTTOM);
        titlePnl.add(titleLbl);
    }
    public void createGamePanel(){
        gamePnl = new JPanel(new GridLayout(3,3));

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = new TicTacToeTile(row, col);
                board[row][col].setText(" ");
                board[row][col].setFont(new Font("Impact", Font.BOLD,60));
                gamePnl.add(board[row][col]);
                board[row][col].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() != null) {
                            buttonClicked = (TicTacToeTile) e.getSource();
                            if (buttonClicked.getText().equals(" ")) {
                                buttonClicked.setText(player);
                                gamePlayer();
                            } else{
                                JOptionPane.showMessageDialog(gamePnl,"Whoops-a-daisy! Looks like you've summoned the ghost of tic-tac-toe past with that choice. Try again with something on this plane of existence!","Invalid Move!",JOptionPane.ERROR_MESSAGE);
                            }

                        }
                    }
                });
            }
        }
    }

    public void createCmdPanel(){
        cmdPnl.setLayout(new GridLayout(1,2));
        clearBtn = new JButton("Clear");
        quitBtn = new JButton("Quit");
        clearBtn.setFont(new Font("Impact",Font.PLAIN,20));
        quitBtn.setFont(new Font("Impact",Font.PLAIN,20));
        cmdPnl.add(clearBtn);
        cmdPnl.add(quitBtn);

        clearBtn.addActionListener(e -> {
            clearBoard();
        });
        quitBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(gamePnl, "Catch you on the flip side, where we'll battle it out again in the realm of Xs and Os. Adios, tic-tac-toe amigo!","Good Bye!",JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        });
    }

    public static void clearBoard(){
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col].setText(" ");
            }
        }
    }
}
