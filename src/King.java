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
        String opponentColor = getOpponentColor();
        ArrayList<Piece> opponentPieces = board.getPiecesByColor(opponentColor);
        Boolean inCheck = false;
        for (Piece piece : opponentPieces) {
            String pieceType = piece.getPieceType();
            switch (pieceType) {
                case "Pawn":

                    Pawn tempPawn = new Pawn(pieceType, piece.getRow(), piece.getCol(),
                            piece.getPieceColor(),
                            (ImageIcon) piece.getPieceImage(), false);
                    inCheck = tempPawn.canMove(board, chessBoard, this.getRow(), this.getCol());
                    if (inCheck)
                        return inCheck;
                    break;
                case "Knight":
                    Knight tempKnight = new Knight(pieceType, piece.getRow(),
                            piece.getCol(), piece.getPieceColor(),
                            (ImageIcon) piece.getPieceImage(), false);
                    inCheck = tempKnight.canMove(board, chessBoard, this.getRow(), this.getCol());
                    if (inCheck)
                        return inCheck;
                    break;
                case "Bishop":
                    Bishop tempBishop = new Bishop(pieceType, piece.getRow(), piece.getCol(),
                            piece.getPieceColor(), (ImageIcon) piece.getPieceImage(), false);
                    inCheck = tempBishop.canMove(board, chessBoard, this.getRow(), this.getCol());
                    if (inCheck)
                        return inCheck;
                    break;
                case "Rook":
                    Rook tempRook = new Rook(pieceType, piece.getRow(), piece.getCol(),
                            piece.getPieceColor(),
                            (ImageIcon) piece.getPieceImage(), false);
                    inCheck = tempRook.canMove(board, chessBoard, this.getRow(), this.getCol());
                    if (inCheck)
                        return inCheck;
                    break;
                case "Queen":
                    Queen tempQueen = new Queen(pieceType, piece.getRow(), piece.getCol(),
                            piece.getPieceColor(), (ImageIcon) piece.getPieceImage(), false);
                    inCheck = tempQueen.canMove(board, chessBoard, this.getRow(), this.getCol());
                    if (inCheck)
                        return inCheck;
                    break;
                case "King":
                    King tempKing = new King(pieceType, piece.getRow(), piece.getCol(),
                            piece.getPieceColor(),
                            (ImageIcon) piece.getPieceImage(), false);
                    inCheck = tempKing.canMove(board, chessBoard, this.getRow(), this.getCol());
                    if (inCheck)
                        return inCheck;
                    break;
            }
        }

        return inCheck;
    }

    // Does not implement if a piece can block
    public boolean isCheckmate(Board board, Piece[][] chessBoard) {
        if (inCheck(board, chessBoard)) {
            // King is in check

            // Check if the king can escape check by moving to an empty square
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int newRow = this.getRow() + i;
                    int newCol = this.getCol() + j;

                    if (isValidMove(newRow, newCol) && canMove(board, chessBoard, newRow, newCol)) {
                        return false; // King can escape check
                    }
                }
            }

            // Check if any piece can block the check
            Move lastMove = board.getLastMove();
            Piece checkingPiece = lastMove.getPiece();

            // Check if checking piece can be blocked
            Boolean isCheckMate = false;
            if (checkingPiece instanceof Bishop || checkingPiece instanceof Rook || checkingPiece instanceof Queen) {
                int kingRow = getRow();
                int kingCol = getCol();
                int checkingRow = checkingPiece.getRow();
                int checkingCol = checkingPiece.getCol();

                // Get squares between the king and checking piece
                ArrayList<Piece> betweenSquares = board.getSquaresBetween(checkingRow, checkingCol, kingRow, kingCol);

                // Check for pieces of the same color that can move to those squares
                for (Piece piece : board.getSameColorPieces(this)) {
                    for (Piece square : betweenSquares) {
                        int squareRow = square.getRow();
                        int squareCol = square.getCol();
                        String pieceType = piece.getPieceType();

                        switch (pieceType) {
                            case "Pawn":

                                Pawn tempPawn = new Pawn(pieceType, piece.getRow(), piece.getCol(),
                                        piece.getPieceColor(),
                                        (ImageIcon) piece.getPieceImage(), false);
                                isCheckMate = !tempPawn.canMove(board, chessBoard, squareRow, squareCol);
                                break;
                            case "Knight":
                                Knight tempKnight = new Knight(pieceType, piece.getRow(),
                                        piece.getCol(), piece.getPieceColor(),
                                        (ImageIcon) piece.getPieceImage(), false);
                                isCheckMate = !tempKnight.canMove(board, chessBoard, squareRow, squareCol);
                                if (isCheckMate)
                                    return isCheckMate;
                                break;
                            case "Bishop":
                                Bishop tempBishop = new Bishop(pieceType, piece.getRow(), piece.getCol(),
                                        piece.getPieceColor(), (ImageIcon) piece.getPieceImage(), false);
                                isCheckMate = !tempBishop.canMove(board, chessBoard, squareRow, squareCol);
                                break;
                            case "Rook":
                                Rook tempRook = new Rook(pieceType, piece.getRow(), piece.getCol(),
                                        piece.getPieceColor(),
                                        (ImageIcon) piece.getPieceImage(), false);
                                isCheckMate = !tempRook.canMove(board, chessBoard, squareRow, squareCol);
                                break;
                            case "Queen":
                                Queen tempQueen = new Queen(pieceType, piece.getRow(), piece.getCol(),
                                        piece.getPieceColor(), (ImageIcon) piece.getPieceImage(), false);
                                isCheckMate = !tempQueen.canMove(board, chessBoard, squareRow, squareCol);
                                break;
                            case "King":
                                break;
                        }
                    }
                }
            }

            // Check if any piece can capture the attacking piece
            if (isCheckMate) {
                ArrayList<Piece> attackingPieces = getAttackingPieces(board, chessBoard);
                for (Piece attackingPiece : attackingPieces) {
                    for (Piece piece : board.getSameColorPieces(this)) {
                        if (piece.canMove(board, chessBoard, attackingPiece.getRow(), attackingPiece.getCol())) {
                            // If a piece can capture the attacking piece, it's not checkmate
                            return false;
                        }
                    }
                }
            }

            // If none of the conditions are met, it's checkmate
            return true;
        }

        // If the king is not in check, it's not checkmate
        return false;
    }

    // Helper method to get pieces attacking the king
    private ArrayList<Piece> getAttackingPieces(Board board, Piece[][] chessBoard) {
        ArrayList<Piece> attackingPieces = new ArrayList<>();

        for (Piece piece : board.getOpponentPieces(this)) {
            if (piece.canMove(board, chessBoard, this.getRow(), this.getCol())) {
                attackingPieces.add(piece);
            }
        }

        return attackingPieces;
    }

    // Helper method to check if the move is within the board boundaries
    private boolean isValidMove(int newRow, int newCol) {
        return newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8;
    }

    @Override
    public boolean canMove(Board board, Piece[][] chessBoard, int newRow, int newCol) {
        boolean canMove = false;
        if (!(isSquareOcupied(newRow, newCol, board, chessBoard))
                || (!(this.getPieceColor().equals(chessBoard[newRow][newCol].getPieceColor())))) {
            int rowDiff = Math.abs(newRow - getRow());
            int colDiff = Math.abs(newCol - getCol());

            // Kings can move one square in any direction.
            Boolean move = rowDiff <= 1 && colDiff <= 1;

            // Check if the move puts the king in check
            if (move) {
                ArrayList<Piece> opponentPieces = board.getOpponentPieces(this);
                for (Piece opponentPiece : opponentPieces) {
                    canMove = !opponentPiece.canMove(board, chessBoard, getRow(), getCol());
                    if (!canMove)
                        return canMove;
                }
            }
        }
        return canMove;
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
