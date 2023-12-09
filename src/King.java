import java.util.ArrayList;
import javax.swing.ImageIcon;

public class King extends Piece {
    private boolean hasMoved;
    private boolean canCastleKingSide;
    private boolean canCastleQueenSide;

    public King(String pieceType, int row, int col, String pieceColor, ImageIcon pieceImage, boolean isOccupied) {
        super("King", row, col, pieceColor, pieceImage, isOccupied);
        // TODO Auto-generated constructor stub
    }

    public boolean inCheck(Board board, Piece[][] chessBoard) {
        switch (getPieceColor()) {
            case "White":
                ArrayList<Piece> bPieces = board.getBlackPieces();
                for (Piece piece : bPieces) {
                    return piece.canMove(this.getRow(), this.getCol());
                }

            case "Black":
                ArrayList<Piece> wPieces = board.getWhitePieces();
                for (Piece piece : wPieces) {
                    return piece.canMove(this.getRow(), this.getCol());
                }
        }
        return false;
    }

    // Does not implement if a piece can block
    public boolean isCheckmate(Board board, Piece[][] chessBoard) {
        if (inCheck(board, chessBoard)) {
            for (int i = 0; i < chessBoard.length; i++) {
                for (int j = 0; j < chessBoard[0].length; j++) {
                    if (canMove(i, j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean canMove(Board board, Piece[][] chessBoard, int newRow, int newCol) {
        if (!(isSquareOcupied(newRow, newCol, board, chessBoard))
                || (!(this.getPieceColor().equals(chessBoard[newRow][newCol].getPieceColor())))) {
            int rowDiff = Math.abs(newRow - this.getRow());
            int colDiff = Math.abs(newCol - this.getCol());

            // Kings can move one square in any direction.
            return (rowDiff <= 1 && colDiff <= 1);
        }
        return false;
    }

    @Override
    public void move(int newRow, int newCol, Board board, Piece[][] chessBoard) {
        if (!inCheck(board, chessBoard)) {
            if (super.canMove(board, chessBoard, newRow, newCol)) {
                super.move(newRow, newCol, board, chessBoard);
            }
        }
    }

    public ArrayList<Piece> canCastle(Board board, Piece[][] chessBoard, ArrayList<Piece> possibleMoves) {
        boolean canCastleKSide = true;
        boolean canCastleQSide = true;
        // Check if the king has moved or is in check
        if (isHasMoved() || inCheck(board, chessBoard)) {
            return possibleMoves;
        }

        // Check if the rook has moved
        Piece kingsideRook = chessBoard[getRow()][0];
        Piece queensiderook = chessBoard[getRow()][7];
        if (kingsideRook == null || ((Rook) kingsideRook).isHasMoved()
                && (queensiderook == null || ((Rook) queensiderook).isHasMoved())) {
            return possibleMoves;
        }

        // Check if the squares between the king and rook are unoccupied
        // Queenside
        for (int col = getCol() + 1; col < 7; col++) {
            if (!(chessBoard[getRow()][col] instanceof EmptySquare)) {
                canCastleQSide = false;
                break;
            }
        }

        // Kingside
        for (int col = getCol() - 1; col > 0; col--) {
            if (!(chessBoard[getRow()][col] instanceof EmptySquare)) {
                canCastleKSide = false;
                break;
            }
        }

        // Check if the king does not pass through an attacked square
        if (this.getPieceColor().equals("White")) {
            ArrayList<Piece> attackingSquares = board.getBlackAttackingSquares(board, chessBoard);
            // Kingside
            if (attackingSquares.contains(chessBoard[getRow()][getCol() - 1])
                    || attackingSquares.contains(chessBoard[getRow()][getCol() - 2])) {
                return possibleMoves;
            } else if (canCastleKSide) {
                for (int i = 1; i <= 2; i++)
                    possibleMoves.add(chessBoard[getRow()][getCol() - i]);
            }
            // Queenside
            if (attackingSquares.contains(chessBoard[getRow()][getCol() + 1])
                    || attackingSquares.contains(chessBoard[getRow()][getCol() + 2])
                    || attackingSquares.contains(chessBoard[getRow()][getCol() + 3])) {
                return possibleMoves;
            } else if (canCastleQSide) {
                for (int i = 1; i <= 3; i++)
                    possibleMoves.add(chessBoard[getRow()][getCol() + i]);
            }
        }

        if (this.getPieceColor().equals("Black")) {
            ArrayList<Piece> attackingSquares = board.getWhiteAttackingSquares(board, chessBoard);
            // Kingside
            if (attackingSquares.contains(chessBoard[getRow()][getCol() - 1])
                    || attackingSquares.contains(chessBoard[getRow()][getCol() - 2])) {
                return possibleMoves;
            } else if (canCastleKSide) {
                for (int i = 1; i <= 2; i++)
                    possibleMoves.add(chessBoard[getRow()][getCol() - i]);
                this.setCanCastleKingSide(canCastleKSide);
            }
            // Queenside
            if (attackingSquares.contains(chessBoard[getRow()][getCol() + 1])
                    || attackingSquares.contains(chessBoard[getRow()][getCol() + 2])
                    || attackingSquares.contains(chessBoard[getRow()][getCol() + 3])) {
                return possibleMoves;
            } else if (canCastleQSide) {
                for (int i = 1; i <= 2; i++)
                    possibleMoves.add(chessBoard[getRow()][getCol() + i]);
                this.setCanCastleQueenSide(canCastleQSide);
            }
        }
        return possibleMoves;
    }

    public boolean isHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public boolean isCanCastleKingSide() {
        return canCastleKingSide;
    }

    public void setCanCastleKingSide(boolean canCastleKingSide) {
        this.canCastleKingSide = canCastleKingSide;
    }

    public boolean isCanCastleQueenSide() {
        return canCastleQueenSide;
    }

    public void setCanCastleQueenSide(boolean canCastleQueenSide) {
        this.canCastleQueenSide = canCastleQueenSide;
    }

}
