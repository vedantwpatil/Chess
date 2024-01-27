import javax.swing.ImageIcon;

public class Bishop extends Piece {

    public Bishop(String pieceType, int row, int col, String pieceColor, ImageIcon pieceImage, boolean isOccupied) {
        super("Bishop", row, col, pieceColor, pieceImage, isOccupied);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean canMove(Board board, Piece[][] chessBoard, int newRow, int newCol) {
        if (!(isSquareOcupied(newRow, newCol, board, chessBoard))
                || (!(this.getPieceColor().equals(chessBoard[newRow][newCol].getPieceColor())))) {
            // Calculate the row and column differences between the current and target
            // positions
            int rowDiff = Math.abs(newRow - getRow());
            int colDiff = Math.abs(newCol - getCol());

            // A bishop can move diagonally. Therefore, it must move an equal number of
            // squares
            // in both row and column directions (i.e., |rowDiff| == |colDiff|).
            if (rowDiff == colDiff) {
                return true;
            }

            // If the row and column differences are not equal, it's an invalid move.
            return false;
        }

        return false;
    }

    @Override
    public void move(int newRow, int newCol, Board board, Piece[][] chessBoard) {
        if (super.canMove(board, chessBoard, newRow, newCol)) {
            super.move(newRow, newCol, board, chessBoard);
        }
    }
}