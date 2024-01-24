import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Pawn extends Piece {

    public Pawn(String pieceType, int row, int col, String pieceColor, ImageIcon pieceImage, boolean isOccupied) {
        super("Pawn", row, col, pieceColor, pieceImage, isOccupied);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean canMove(Board board, Piece[][] chessBoard, int newRow, int newCol) {

        Move lastMove = board.getLastMove();

        // Calculate the row and column difference
        int rowDiff = Math.abs(newRow - this.getRow());
        int colDiff = Math.abs(newCol - this.getCol());

        if (!isSquareOcupied(newRow, newCol, board, chessBoard)) {
            // White pawn can only move forward by one square
            if (this.getPieceColor().equals("White")) {
                // Normal forward move
                if (colDiff == 0 && newRow == this.getRow() + 1) {
                    return true;
                }
                // First move option: move forward by two squares
                if (colDiff == 0 && newRow == this.getRow() + 2 && this.getRow() == 1) {
                    return true;
                }
            }

            // Black pawn can only move forward by one square
            if (this.getPieceColor().equals("Black")) {
                // Normal forward move
                if (colDiff == 0 && newRow == this.getRow() - 1) {
                    return true;
                }
                // First move option: move forward by two squares
                if (colDiff == 0 && newRow == this.getRow() - 2 && this.getRow() == 6) {
                    return true;
                }
            }
        } else {

            // If the square is occupied, check if we can capture the piece
            if (!(this.getPieceColor().equals(chessBoard[newRow][newCol].getPieceColor()))) {
                // Normal Captures
                if (this.getPieceColor().equals("White")) {
                    if (colDiff == 1 && rowDiff == 1 && newRow == this.getRow() + 1) {
                        return true;
                    }
                }
                if (this.getPieceColor().equals("Black")) {
                    if (colDiff == 1 && rowDiff == 1 && newRow == this.getRow() - 1) {
                        return true;
                    }
                }
                // Enpassent
                if (lastMove != null) {
                    if (lastMove.getPiece() instanceof Pawn && (colDiff == 1 && newRow == this.getRow())) {
                        int moveRowDiff = lastMove.getPiece().getRow() - lastMove.getPastRow();
                        if (moveRowDiff == 2) {
                            return true;
                        }
                    }
                }
            }
        }

        return false; // If none of the conditions are met, the pawn cannot move to the new position
    }

    @Override
    public void move(int newRow, int newCol, Board board, Piece[][] chessBoard) {
        if (super.canMove(board, chessBoard, newRow, newCol)) {
            super.move(newRow, newCol, board, chessBoard);
        }
    }

    public String promote() {
        // Knight image
        ImageIcon wKnight = new ImageIcon(
                "src/images/Chess_nlt60.png");
        ImageIcon bKnight = new ImageIcon(
                "src/images/Chess_ndt60.png");

        // Bishop image
        ImageIcon wBishop = new ImageIcon(
                "src/images/Chess_blt60.png");
        ImageIcon bBishop = new ImageIcon(
                "src/images/Chess_bdt60.png");

        // Rook image
        ImageIcon wRook = new ImageIcon(
                "src/images/Chess_rlt60.png");
        ImageIcon bRook = new ImageIcon(
                "src/images/Chess_rdt60.png");

        // Queen image
        ImageIcon wQueen = new ImageIcon(
                "src/images/Chess_qlt60.png");
        ImageIcon bQueen = new ImageIcon(
                "src/images/Chess_qdt60.png");

        Object[] options = { "Queen", "Rook", "Bishop", "Knight" };
        int choice = JOptionPane.showOptionDialog(null, "Choose a piece to promote your pawn to:",
                "Pawn Promotion", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);

        // Handle the user's choice
        if (this.getPieceColor().equals("White")) {
            switch (choice) {
                case 0:
                    // Queen
                    // Update the piece type and set the new piece image
                    this.setPieceType("Queen");
                    this.setPieceImage(wQueen);
                    return this.getPieceType();
                case 1:
                    // Rook
                    // Update the piece type and set the new piece image
                    this.setPieceType("Rook");
                    this.setPieceImage(wRook);
                    return this.getPieceType();
                case 2:
                    // Bishop
                    // Update the piece type and set the new piece image
                    this.setPieceType("Bishop");
                    this.setPieceImage(wBishop);
                    return this.getPieceType();
                case 3:
                    // Knight
                    // Update the piece type and set the new piece image
                    this.setPieceType("Knight");
                    this.setPieceImage(wKnight);
                    return this.getPieceType();
                default:
                    // Default to Queen if no valid choice
                    this.setPieceType("Queen");
                    this.setPieceImage(wQueen);
                    return this.getPieceType();
            }
        }
        if (this.getPieceColor().equals("Black")) {
            switch (choice) {
                case 0:
                    // Queen
                    // Update the piece type and set the new piece image
                    this.setPieceType("Queen");
                    this.setPieceImage(bQueen);
                    return this.getPieceType();
                case 1:
                    // Rook
                    // Update the piece type and set the new piece image
                    this.setPieceType("Rook");
                    this.setPieceImage(bRook);
                    return this.getPieceType();
                case 2:
                    // Bishop
                    // Update the piece type and set the new piece image
                    this.setPieceType("Bishop");
                    this.setPieceImage(bBishop);
                    return this.getPieceType();
                case 3:
                    // Knight
                    // Update the piece type and set the new piece image
                    this.setPieceType("Knight");
                    this.setPieceImage(bKnight);
                    return this.getPieceType();
                default:
                    // Default to Queen if no valid choice
                    this.setPieceType("Queen");
                    this.setPieceImage(bQueen);
                    return this.getPieceType();
            }
        }
        return null;
    }
}