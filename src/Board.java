import java.util.*;

import javax.swing.ImageIcon;

public class Board {
    private Piece[][] chessBoard;
    private ArrayList<Piece> whitePieces;
    private ArrayList<Piece> blackPieces;
    private Stack<Move> moves;

    // Pawn image
    ImageIcon wPawn = new ImageIcon(
            "src/images/Chess_plt60.png");
    ImageIcon bPawn = new ImageIcon(
            "src/images/Chess_pdt60.png");

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

    // King image
    ImageIcon wKing = new ImageIcon(
            "src/images/Chess_klt60.png");
    ImageIcon bKing = new ImageIcon(
            "src/images/Chess_kdt60.png");

    // Sets up the default start position
    public Board() {
        chessBoard = new Piece[8][8];
        moves = new Stack<>();
        whitePieces = new ArrayList<>();

        // Add white pieces to the ArrayList
        whitePieces.add(new Rook("Rook", 0, 0, "White", wRook, true));
        whitePieces.add(new Knight("Knight", 0, 1, "White", wKnight, true));
        whitePieces.add(new Bishop("Bishop", 0, 2, "White", wBishop, true));
        whitePieces.add(new Queen("Queen", 0, 3, "White", wQueen, true));
        whitePieces.add(new King("King", 0, 4, "White", wKing, true));
        whitePieces.add(new Bishop("Bishop", 0, 5, "White", wBishop, true));
        whitePieces.add(new Knight("Knight", 0, 6, "White", wKnight, true));
        whitePieces.add(new Rook("Rook", 0, 7, "White", wRook, true));

        for (int col = 0; col < 8; col++) {
            whitePieces.add(new Pawn("Pawn", 1, col, "White", wPawn, true));
        }

        blackPieces = new ArrayList<>();

        // Add black pieces to the ArrayList
        blackPieces.add(new Rook("Rook", 7, 0, "Black", bRook, true));
        blackPieces.add(new Knight("Knight", 7, 1, "Black", bKnight, true));
        blackPieces.add(new Bishop("Bishop", 7, 2, "Black", bBishop, true));
        blackPieces.add(new Queen("Queen", 7, 3, "Black", bQueen, true));
        blackPieces.add(new King("King", 7, 4, "Black", bKing, true));
        blackPieces.add(new Bishop("Bishop", 7, 5, "Black", bBishop, true));
        blackPieces.add(new Knight("Knight", 7, 6, "Black", bKnight, true));
        blackPieces.add(new Rook("Rook", 7, 7, "Black", bRook, true));

        for (int col = 0; col < 8; col++) {
            blackPieces.add(new Pawn("Pawn", 6, col, "Black", bPawn, true));
        }

        // Initialize the white pieces
        chessBoard[0][0] = new Rook("Rook", 0, 0, "White", wRook, true);
        chessBoard[0][1] = new Knight("Knight", 0, 1, "White", wKnight, true);
        chessBoard[0][2] = new Bishop("Bishop", 0, 2, "White", wBishop, true);
        chessBoard[0][3] = new Queen("Queen", 0, 3, "White", wQueen, true);
        chessBoard[0][4] = new King("King", 0, 4, "White", wKing, true);
        chessBoard[0][5] = new Bishop("Bishop", 0, 5, "White", wBishop, true);
        chessBoard[0][6] = new Knight("Knight", 0, 6, "White", wKnight, true);
        chessBoard[0][7] = new Rook("Rook", 0, 7, "White", wRook, true);

        for (int col = 0; col < 8; col++) {
            chessBoard[1][col] = new Pawn("Pawn", 1, col, "White", wPawn, true);
        }

        // Initialize the black pieces
        chessBoard[7][0] = new Rook("Rook", 7, 0, "Black", bRook, true);
        chessBoard[7][1] = new Knight("Knight", 7, 1, "Black", bKnight, true);
        chessBoard[7][2] = new Bishop("Bishop", 7, 2, "Black", bBishop, true);
        chessBoard[7][3] = new Queen("Queen", 7, 3, "Black", bQueen, true);
        chessBoard[7][4] = new King("King", 7, 4, "Black", bKing, true);
        chessBoard[7][5] = new Bishop("Bishop", 7, 5, "Black", bBishop, true);
        chessBoard[7][6] = new Knight("Knight", 7, 6, "Black", bKnight, true);
        chessBoard[7][7] = new Rook("Rook", 7, 7, "Black", bRook, true);

        for (int col = 0; col < 8; col++) {
            chessBoard[6][col] = new Pawn("Pawn", 6, col, "Black", bPawn, true);
        }

        // Initialize the empty squares
        for (int row = 2; row <= 5; row++) {
            for (int col = 0; col < 8; col++) {
                chessBoard[row][col] = new EmptySquare("Empty Square", row, col, "Empty Square", null, false);
            }
        }
    }

    public Piece findPiece(ArrayList<Piece> pieces, int row, int col) {
        for (Piece piece : pieces) {
            if ((piece.getCol() == col) && (piece.getRow() == row))
                return piece;
        }
        return null;
    }

    public ArrayList<Piece> getWhiteAttackingSquares(Board board, Piece[][] currentChessBoard) {
        ArrayList<Piece> wPieces = getWhitePieces();
        ArrayList<Piece> possibleMoves = new ArrayList<Piece>();

        for (Piece piece : wPieces) {
            possibleMoves.addAll(piece.getPossibleMoves(board, currentChessBoard));
        }
        return possibleMoves;
    }

    public ArrayList<Piece> getBlackAttackingSquares(Board board, Piece[][] currentChessBoard) {
        ArrayList<Piece> bPieces = getBlackPieces();
        ArrayList<Piece> possibleMoves = new ArrayList<Piece>();

        for (Piece piece : bPieces) {
            possibleMoves.addAll(piece.getPossibleMoves(board, currentChessBoard));
        }
        return possibleMoves;
    }

    public Piece[][] getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(Piece[][] chessBoard) {
        this.chessBoard = chessBoard;
    }

    public ArrayList<Piece> getWhitePieces() {
        return whitePieces;
    }

    public void setWhitePieces(ArrayList<Piece> whitePieces) {
        this.whitePieces = whitePieces;
    }

    public ArrayList<Piece> getBlackPieces() {
        return blackPieces;
    }

    public void setBlackPieces(ArrayList<Piece> blackPieces) {
        this.blackPieces = blackPieces;
    }

    public Stack<Move> getMoves() {
        return moves;
    }

    public void setMoves(Stack<Move> moves) {
        this.moves = moves;
    }

    public Move getLastMove() {
        if (moves.size() > 0) {
            return moves.peek();
        }
        return null;
    }

    public void addMove(Piece piece, int sourceRow, int sourceCol) {
        // Don't need image and occupied information so we make a new piece object to
        // add but we need where the piece moved from and the square its moving to
        Move Piece = new Move(piece, sourceRow, sourceCol);
        moves.add(Piece);
    }
}
