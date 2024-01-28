import javax.swing.ImageIcon;

public class Queen extends Piece {

    public Queen(String pieceType, int row, int col, String pieceColor, ImageIcon pieceImage, boolean isOccupied) {
        super("Queen", row, col, pieceColor, pieceImage, isOccupied);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean canMove(Board board, Piece[][] chessBoard, int newRow, int newCol) {
        if (!(isSquareOcupied(newRow, newCol, board, chessBoard))
                || (!(this.getPieceColor().equals(chessBoard[newRow][newCol].getPieceColor())))) {
            int rowDiff = Math.abs(newRow - getRow());
            int colDiff = Math.abs(newCol - getCol());

            // Queens can move horizontally, vertically, or diagonally.
            return newRow == getRow() || newCol == getCol() || rowDiff == colDiff;
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