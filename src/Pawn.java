import javax.swing.ImageIcon;

public class Pawn extends Piece {
    private boolean enPassent;

    public Pawn(String pieceType, int row, int col, String pieceColor, ImageIcon pieceImage, boolean isOccupied,
            boolean enPassent) {
        super("Pawn", row, col, pieceColor, pieceImage, isOccupied);
        // TODO Auto-generated constructor stub
        this.enPassent = enPassent;
    }

    @Override
    public boolean canMove(Board board, Piece[][] chessBoard, int newRow, int newCol) {
        
        // Calculate the row and column difference
        int rowDiff = Math.abs(newRow - this.getRow());
        int colDiff = Math.abs(newCol - this.getCol());

        if (!isSquareOcupied(newRow, newCol, board, chessBoard)) {
            // White pawn can only move forward by one square
            if (this.getPieceColor().equals("White")) {
                // Normal forward move
                if (colDiff == 0 && newRow == this.getRow() + 1) {
                    this.setEnPassent(false);
                    return true;
                }
                // First move option: move forward by two squares
                if (colDiff == 0 && newRow == this.getRow() + 2 && this.getRow() == 1) {
                    this.setEnPassent(true);
                    return true;
                }
            }

            // Black pawn can only move forward by one square
            if (this.getPieceColor().equals("Black")) {
                // Normal forward move
                if (colDiff == 0 && newRow == this.getRow() - 1) {
                    this.setEnPassent(false);
                    return true;
                }
                // First move option: move forward by two squares
                if (colDiff == 0 && newRow == this.getRow() - 2 && this.getRow() == 6) {
                    this.setEnPassent(true);
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
                else if (colDiff == 1 && newRow == this.getRow() && chessBoard[newRow][newCol] instanceof Pawn
                        && ((Pawn) chessBoard[newRow][newCol]).isEnPassent()) {
                    return true;
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

    public boolean isEnPassent() {
        return enPassent;
    }

    public void setEnPassent(boolean enPassent) {
        this.enPassent = enPassent;
    }
}