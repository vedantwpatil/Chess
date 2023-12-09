import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChessBoard implements ActionListener {

    private final JFrame frame;
    private final JPanel boardPanel;
    private JButton[][] squares;

    private int selectedRow;
    private int selectedCol;

    public ChessBoard() {
        frame = new JFrame("Chess Board");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boardPanel = new JPanel();
        boardPanel.setLayout(new java.awt.GridLayout(8, 8));
        frame.add(boardPanel);

        squares = new JButton[8][8];
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                squares[row][col] = new JButton();
                squares[row][col].addActionListener(this);
                boardPanel.add(squares[row][col]);
            }
        }

        initializeBoard();
        frame.setVisible(true);
    }

    private void initializeBoard() {
        // Set initial colors and labels for the squares
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if ((row + col) % 2 == 0) {
                    squares[row][col].setBackground(Color.WHITE);
                } else {
                    squares[row][col].setBackground(Color.GRAY);
                }
                squares[row][col].setText("");
            }
        }

        // Add piece labels
        squares[0][0].setText("R");
        squares[0][1].setText("N");
        squares[0][2].setText("B");
        squares[0][3].setText("Q");
        squares[0][4].setText("K");
        squares[0][5].setText("B");
        squares[0][6].setText("N");
        squares[0][7].setText("R");

        for (int col = 0; col < 8; col++) {
            squares[1][col].setText("P");
            squares[6][col].setText("P");
        }

        squares[7][0].setText("r");
        squares[7][1].setText("n");
        squares[7][2].setText("b");
        squares[7][3].setText("q");
        squares[7][4].setText("k");
        squares[7][5].setText("b");
        squares[7][6].setText("n");
        squares[7][7].setText("r");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        // Find the clicked button's position in the squares array
        int clickedRow = -1;
        int clickedCol = -1;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (squares[row][col] == clickedButton) {
                    clickedRow = row;
                    clickedCol = col;
                    break;
                }
            }
            if (clickedRow != -1) {
                break;
            }
        }

        if (selectedRow == -1 && selectedCol == -1) {
            // No square is currently selected
            if (!clickedButton.getText().equals("")) {
                // Select the clicked button if it contains a piece
                selectedRow = clickedRow;
                selectedCol = clickedCol;
                clickedButton.setBackground(Color.YELLOW);
            }
        } else {
            // A square is already selected
            if (selectedRow == clickedRow && selectedCol == clickedCol) {
                // Deselect the currently selected square
                selectedRow = -1;
                selectedCol = -1;
                clickedButton.setBackground(getSquareColor(clickedRow, clickedCol));
            } else {
                // Move the selected piece to the clicked square
                JButton selectedButton = squares[selectedRow][selectedCol];
                clickedButton.setText(selectedButton.getText());
                clickedButton.setBackground(selectedButton.getBackground());
                selectedButton.setText("");
                selectedButton.setBackground(getSquareColor(selectedRow, selectedCol));
                selectedRow = -1;
                selectedCol = -1;
            }
        }
    }

    private Color getSquareColor(int row, int col) {
        if ((row + col) % 2 == 0) {
            return Color.WHITE;
        } else {
            return Color.GRAY;
        }
    }

    public static void main(String[] args) {
        new ChessBoard();
    }
}
