
import javax.swing.ImageIcon;

public class Rook extends Piece {
    private boolean hasMoved;

    public Rook(String pieceType, int row, int col, String pieceColor, ImageIcon pieceImage, boolean isOccupied) {
        super("Rook", row, col, pieceColor, pieceImage, isOccupied);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean canMove(Board board, Piece[][] chessBoard, int newRow, int newCol) {
        if (!(isSquareOcupied(newRow, newCol, board, chessBoard))
                || (!(this.getPieceColor().equals(chessBoard[newRow][newCol].getPieceColor())))) {
            // Rooks can move either horizontally or vertically, but not diagonally.
            return newRow == this.getRow() || newCol == this.getCol();
        }
        return false;
    }

    @Override
    public void move(int newRow, int newCol, Board board, Piece[][] chessBoard) {
        if (super.canMove(board, chessBoard, newRow, newCol)) {
            super.move(newRow, newCol, board, chessBoard);
        }
    }

    public boolean isHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

}
