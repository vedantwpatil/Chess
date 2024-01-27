import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class Game {
    private static final int TILE_SIZE = 80;
    private static final int WIDTH = 8 * TILE_SIZE;
    private static final int HEIGHT = 8 * TILE_SIZE;
    private PieceButton sourceButton = null;
    private PieceButton targetButton = null;
    private boolean whiteTurn = true;
    private boolean blackTurn = false;
    private JFrame frame;
    public Board board = new Board();
    Piece[][] chessBoard = board.getChessBoard();
    private Component[][] buttonList = new Component[8][8];

    public Game() {
        frame = new JFrame("Chess");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(8, 8));
        frame.getContentPane().setLayout(new GridLayout(8, 8));

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

        Color darkSquare = new Color(118, 150, 86);
        Color lightSquare = new Color(238, 238, 210);

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Color color = ((row + col) % 2 == 0) ? lightSquare : darkSquare;
                PieceButton piece = new PieceButton();
                PieceListener pieceListener = new PieceListener();

                // White
                if (row == 0 || row == 1) {
                    piece.setPieceColor("White");
                    if (row == 0 && (col == 0 || col == 7)) {
                        piece.setPieceType("Rook");
                        piece.setIcon(wRook);
                    }
                    if (row == 0 && (col == 1 || col == 6)) {
                        piece.setPieceType("Knight");
                        piece.setIcon(wKnight);
                    }
                    if (row == 0 && (col == 2 || col == 5)) {
                        piece.setPieceType("Bishop");
                        piece.setIcon(wBishop);
                    }
                    if (row == 0 && col == 4) {
                        piece.setPieceType("Queen");
                        piece.setIcon(wQueen);
                    }
                    if (row == 0 && col == 3) {
                        piece.setPieceType("King");
                        piece.setIcon(wKing);
                    }
                    if (row == 1) {
                        piece.setPieceType("Pawn");
                        piece.setIcon(wPawn);
                        piece.setEnPassent(false);
                    }
                }

                // Black
                if (row == 6 || row == 7) {
                    piece.setPieceColor("Black");
                    if (row == 7 && (col == 0 || col == 7)) {
                        piece.setPieceType("Rook");
                        piece.setIcon(bRook);
                    }
                    if (row == 7 && (col == 1 || col == 6)) {
                        piece.setPieceType("Knight");
                        piece.setIcon(bKnight);
                    }
                    if (row == 7 && (col == 2 || col == 5)) {
                        piece.setPieceType("Bishop");
                        piece.setIcon(bBishop);
                    }
                    if (row == 7 && col == 4) {
                        piece.setPieceType("Queen");
                        piece.setIcon(bQueen);
                    }
                    if (row == 7 && col == 3) {
                        piece.setPieceType("King");
                        piece.setIcon(bKing);
                    }
                    if (row == 6) {
                        piece.setPieceType("Pawn");
                        piece.setIcon(bPawn);
                        piece.setEnPassent(false);
                    }
                }
                piece.setRow(row);
                piece.setCol(col);
                piece.setSquareColor(color);
                piece.setOpaque(true);
                piece.setBorder(null);
                piece.addActionListener(pieceListener);
                // piece.addMouseMotionListener(pieceListener);
                piece.setBackground(color);
                piece.setOccupied(true);
                buttonList[row][col] = piece;
                frame.add(piece);
            }
        }
        frame.repaint();
        frame.setVisible(true);

    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Piece[][] getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(Piece[][] chessBoard) {
        this.chessBoard = chessBoard;
    }

    public Component[][] getButtonList() {
        return buttonList;
    }

    public void setButtonList(Component[][] buttonList) {
        this.buttonList = buttonList;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public static int getTileSize() {
        return TILE_SIZE;
    }

    public static int getWidth() {
        return WIDTH;
    }

    public static int getHeight() {
        return HEIGHT;
    }

    public PieceButton getSourceButton() {
        return sourceButton;
    }

    public void setSourceButton(PieceButton sourceButton) {
        this.sourceButton = sourceButton;
    }

    public PieceButton getTargetButton() {
        return targetButton;
    }

    public void setTargetButton(PieceButton targetButton) {
        this.targetButton = targetButton;
    }

    public boolean isWhiteTurn() {
        return whiteTurn;
    }

    public void setWhiteTurn(boolean whiteTurn) {
        this.whiteTurn = whiteTurn;
    }

    public boolean isBlackTurn() {
        return blackTurn;
    }

    public void setBlackTurn(boolean blackTurn) {
        this.blackTurn = blackTurn;
    }

    public Board getBoard() {
        return board;
    }

    public class PieceButton extends JButton {
        private int row;
        private int col;
        private String pieceType;
        private String pieceColor;
        private ImageIcon pieceImage;
        private Color squareColor;
        private boolean isOccupied;
        private boolean isEnPassent;

        public PieceButton() {
            setPreferredSize(new Dimension(900, 900));
            setOpaque(true);
            setBorderPainted(false);
        }

        public PieceButton(int row, int col, String pieceColor, String pieceType,
                ImageIcon pieceImage) {
            this.row = row;
            this.col = col;
            this.pieceType = pieceType;
            this.pieceColor = pieceColor;
            this.pieceImage = pieceImage;
        }

        /**
         * Evaluates if a piece type is the same as another piece type
         * Example, if current piece is a pawn and the other piece is a
         * pawn, it returns true.
         * 
         * @param piece
         * @return true or false
         */
        public boolean pieceEvaluation(Piece piece) {
            String pieceString = getPieceType().substring(0, 1);
            if (pieceString.equals(piece.getPieceType().substring(0, 1))) {
                return true;
            }
            return false;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getCol() {
            return col;
        }

        public void setCol(int col) {
            this.col = col;
        }

        public String getPieceType() {
            return pieceType;
        }

        public void setPieceType(String pieceType) {
            this.pieceType = pieceType;
        }

        public ImageIcon getPieceImage() {
            return pieceImage;
        }

        public void setPieceIcon(ImageIcon pieceImage) {
            this.pieceImage = pieceImage;
        }

        public String getPieceColor() {
            return pieceColor;
        }

        public void setPieceColor(String pieceColor) {
            this.pieceColor = pieceColor;
        }

        public Color getSquareColor() {
            return squareColor;
        }

        public void setSquareColor(Color squareColor) {
            this.squareColor = squareColor;
        }

        public void setPieceImage(ImageIcon pieceImage) {
            this.pieceImage = pieceImage;
        }

        public boolean isOccupied() {
            return isOccupied;
        }

        public void setOccupied(boolean isOccupied) {
            this.isOccupied = isOccupied;
        }

        public boolean isEnPassent() {
            return isEnPassent;
        }

        public void setEnPassent(boolean isEnPassent) {
            this.isEnPassent = isEnPassent;
        }

    }

    public class PieceListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            PieceButton buttonClicked = (PieceButton) e.getSource();
            Color darkSquare = new Color(118, 150, 86);
            Color lightSquare = new Color(238, 238, 210);
            Color canMoveToSquare = new Color(255, 137, 115);
            Color selectedSquare = new Color(255, 234, 181);

            // Check for checkmate
            ArrayList<Piece> wPieces = board.getWhitePieces();
            ArrayList<Piece> bPieces = board.getBlackPieces();

            if (whiteTurn) {
                for (Piece piece : wPieces) {
                    if (piece instanceof King) {
                        King tempKing = (King) piece;
                        if (tempKing.isCheckmate(board, chessBoard)) {
                            endGame("Black");
                        }
                    }
                }
            }

            if (blackTurn) {
                for (Piece piece : bPieces) {
                    if (piece instanceof King) {
                        King tempKing = (King) piece;
                        if (tempKing.isCheckmate(board, chessBoard)) {
                            endGame("White");
                        }
                    }
                }
            }

            // Check if the button clicked is the source button
            if (buttonClicked == sourceButton) {
                // Reset the source button
                sourceButton = null;
            } else if (sourceButton == null && ((buttonClicked.getPieceColor().equals("White") && whiteTurn)
                    || (buttonClicked.getPieceColor().equals("Black") && blackTurn))) {
                // Set the source button
                sourceButton = buttonClicked;
            } else if (buttonClicked.getPieceColor() == sourceButton.getPieceColor()) {
                // If you click another piece of the same color, that is now the new source
                // piece
                resetBoardColors();
                sourceButton = buttonClicked;
                targetButton = null;
            } else if (buttonClicked.getBackground().equals(canMoveToSquare) && sourceButton != null) {
                // The source button is already set, so the button clicked must be the target
                // button
                targetButton = buttonClicked;

                // Call the movePiece method passing the source and target buttons as arguments
                movePiece(board, sourceButton, buttonClicked, canMoveToSquare);
                // Reset the source and target buttons
                sourceButton = null;
                targetButton = null;
            }

            // Highlights selected piece/square

            highlightSelectedSquare(buttonClicked, selectedSquare, lightSquare, darkSquare);

            if ((buttonClicked.getPieceColor().equals("White") && whiteTurn)
                    || (buttonClicked.getPieceColor().equals("Black") && blackTurn)) {

                // Highlights legal moves for selected pieces
                String pieceType = buttonClicked.getPieceType();
                switch (pieceType) {
                    case "Pawn":

                        // EnPassent
                        if (sourceButton != null && targetButton != null) {
                            int rowDiff = Math.abs(sourceButton.getRow() - targetButton.getRow());
                            if (rowDiff == 2) {
                                buttonClicked.setEnPassent(true);
                            }
                        }

                        Pawn tempPawn = new Pawn(pieceType, buttonClicked.getRow(), buttonClicked.getCol(),
                                buttonClicked.getPieceColor(),
                                buttonClicked.getPieceImage(), false);
                        highlightValidMoves(tempPawn, buttonClicked, canMoveToSquare);
                        break;
                    case "Knight":
                        Knight tempKnight = new Knight(pieceType, buttonClicked.getRow(),
                                buttonClicked.getCol(), buttonClicked.getPieceColor(),
                                buttonClicked.getPieceImage(), false);
                        highlightValidMoves(tempKnight, buttonClicked, canMoveToSquare);
                        break;
                    case "Bishop":
                        Bishop tempBishop = new Bishop(pieceType, buttonClicked.getRow(), buttonClicked.getCol(),
                                buttonClicked.getPieceColor(), buttonClicked.getPieceImage(), false);
                        highlightValidMoves(tempBishop, buttonClicked, canMoveToSquare);
                        break;
                    case "Rook":
                        Rook tempRook = new Rook(pieceType, buttonClicked.getRow(), buttonClicked.getCol(),
                                buttonClicked.getPieceColor(),
                                buttonClicked.getPieceImage(), false);
                        highlightValidMoves(tempRook, buttonClicked, canMoveToSquare);
                        break;
                    case "Queen":
                        Queen tempQueen = new Queen(pieceType, buttonClicked.getRow(), buttonClicked.getCol(),
                                buttonClicked.getPieceColor(), buttonClicked.getPieceImage(), false);
                        highlightValidMoves(tempQueen, buttonClicked, canMoveToSquare);
                        break;
                    case "King":
                        King tempKing = new King(pieceType, buttonClicked.getRow(), buttonClicked.getCol(),
                                buttonClicked.getPieceColor(),
                                buttonClicked.getPieceImage(), false);
                        highlightValidMoves(tempKing, buttonClicked, canMoveToSquare);
                        break;
                    case "Empty Square":
                        break;
                }
            }

            if (sourceButton == null)
                resetBoardColors();

            frame.repaint();
        }

        // Reset the background color of a button
        private void resetButtonColors(PieceButton buttonClicked) {

            // Color darkSquare = new Color(118, 150, 86);
            // Color lightSquare = new Color(238, 238, 210);

            for (Component component : buttonClicked.getParent().getComponents()) {
                if (component instanceof PieceButton) {
                    PieceButton pieceButton = (PieceButton) component;
                    pieceButton.setBackground(pieceButton.getSquareColor());
                }
            }
        }

        public void highlightValidMoves(Piece piece, PieceButton buttonClicked, Color canMoveToSquare) {

            // Get the parent container
            Container parentContainer = buttonClicked.getParent();

            resetButtonColors(buttonClicked);

            // Highlight squares the piece can move to
            ArrayList<Piece> possibleMoves = piece.possibleMoves(piece, board, chessBoard);

            // Enpassant
            if (piece instanceof Pawn) {
                Pawn testPawn = (Pawn) piece;
                ArrayList<Piece> wPieces = board.getWhitePieces();
                ArrayList<Piece> bPieces = board.getBlackPieces();

                if (testPawn.getPieceColor().equals("White")) {
                    for (Piece blackPawn : bPieces) {
                        if (blackPawn instanceof Pawn) {
                            if (board.getLastMove() != null) {
                                Move lastMove = board.getLastMove();

                                int colDiff = Math.abs(testPawn.getCol() - blackPawn.getCol());

                                // Enpassent
                                if (colDiff == 1 && lastMove.getPiece() instanceof Pawn
                                        && blackPawn.getRow() == testPawn.getRow()) {
                                    Piece move = new Piece("Pawn", blackPawn.getRow(), blackPawn.getCol() - 1, "White",
                                            blackPawn.getPieceImage(), true);
                                    possibleMoves.add(move);
                                }
                            }
                        }
                    }
                }
                if (testPawn.getPieceColor().equals("Black")) {
                    for (Piece whitePawn : wPieces) {
                        if (whitePawn instanceof Pawn) {
                            if (board.getLastMove() != null) {
                                Move lastMove = board.getLastMove();

                                if (whitePawn.getRow() == 3 && Math.abs(whitePawn.getCol() - piece.getCol()) == 1) {
                                    Piece move = new Piece("Pawn", whitePawn.getRow(), whitePawn.getCol() + 1, "White",
                                            whitePawn.getPieceImage(), true);
                                    possibleMoves.add(move);
                                }
                                // Enpassent
                                int colDiff = Math.abs(testPawn.getCol() - whitePawn.getCol());

                                if (colDiff == 1 && lastMove.getPiece() instanceof Pawn
                                        && whitePawn.getRow() == testPawn.getRow()) {
                                    Piece move = new Piece("Pawn", whitePawn.getRow(), whitePawn.getCol() - 1, "White",
                                            whitePawn.getPieceImage(), true);
                                    possibleMoves.add(move);
                                }
                            }
                        }
                    }
                }
            }

            // Castling
            if (piece instanceof King) {
                King testKing = (King) piece;
                possibleMoves.addAll(testKing.canCastle(board, chessBoard, possibleMoves));
            }

            // Normal Moves
            for (Piece move : possibleMoves) {
                int newRow = move.getRow();
                int newCol = move.getCol();
                if (!(move.isOccupied())) {
                    PieceButton square = (PieceButton) parentContainer.getComponent(newRow * 8 + newCol);
                    square.setBackground(canMoveToSquare);
                }
            }
        }

        public void highlightSelectedSquare(PieceButton buttonClicked, Color selectedSquare, Color lightSquare,
                Color darkSquare) {
            // Deselect the already selected piece
            if (buttonClicked.getSquareColor().equals(selectedSquare)) {
                buttonClicked.setSquareColor(
                        (buttonClicked.getRow() + buttonClicked.getCol()) % 2 == 0 ? lightSquare : darkSquare);
            } else {
                buttonClicked.setSquareColor(selectedSquare);
            }

            // Reset the background color of all squares
            resetButtonColors(buttonClicked);
        }

        public void movePiece(Board board, PieceButton sourceButton, PieceButton targetButton,
                Color canMoveToSquare) {

            // Castling Logic
            if (sourceButton.getPieceType().equals("King")
                    && (Math.abs(sourceButton.getCol() - targetButton.getCol()) > 1)) {
                PieceButton rookSourceButtonKSide = (Game.PieceButton) buttonList[sourceButton
                        .getRow()][0];
                PieceButton rookSourceButtonQSide = (Game.PieceButton) buttonList[sourceButton
                        .getRow()][7];
                int colDif = sourceButton.getCol() - targetButton.getCol();

                // Check if we are castling kingside or queenside
                if (colDif < 0) {
                    PieceButton rookTargetButtonQSide = (Game.PieceButton) buttonList[sourceButton
                            .getRow()][rookSourceButtonQSide.getCol() - 3];

                    movePieceLogic(board, rookSourceButtonQSide, rookTargetButtonQSide);
                } else {
                    PieceButton rookTargetButtonKSide = (Game.PieceButton) buttonList[sourceButton
                            .getRow()][rookSourceButtonKSide.getCol() + 2];

                    movePieceLogic(board, rookSourceButtonKSide, rookTargetButtonKSide);
                }
            }
            // Promotion logic
            if (sourceButton.getPieceType().equals("Pawn")
                    && (targetButton.getRow() == 7 || targetButton.getRow() == 0)) {
                movePiecePromotionLogic(board, sourceButton, targetButton);
            }
            // Normal move
            else {
                movePieceLogic(board, sourceButton, targetButton);
            }
        }

        public void movePiecePromotionLogic(Board board, PieceButton sourceButton, PieceButton targetButton) {
            // Board is somehow null
            ArrayList<Piece> wPieces = board.getWhitePieces();
            ArrayList<Piece> bPieces = board.getBlackPieces();

            // Get the chess piece information from the source button
            String pieceType = sourceButton.getPieceType();
            String pieceColor = sourceButton.getPieceColor();

            // Get coordinates
            int targetRow = targetButton.getRow();
            int targetCol = targetButton.getCol();
            int sourceRow = sourceButton.getRow();
            int sourceCol = sourceButton.getCol();

            // Promotes the pawn
            Pawn testPawn = new Pawn(pieceType, targetRow, targetCol, pieceColor, sourceButton.getPieceImage(), true);
            testPawn.promote();

            // Update the target button with the piece information
            targetButton.setPieceType(testPawn.getPieceType());
            targetButton.setPieceColor(pieceColor);
            targetButton.setIcon(testPawn.getPieceImage());

            // Update the chessBoard array
            chessBoard[targetRow][targetCol] = chessBoard[sourceRow][sourceCol];
            chessBoard[targetRow][targetCol].setRow(targetRow);
            chessBoard[targetRow][targetCol].setCol(targetCol);
            chessBoard[targetRow][targetCol].setOccupied(true);

            // Update the chessBoard arrayList

            // Add the new piece
            Piece newLocPiece = new Piece(testPawn.getPieceType(), targetRow, targetCol, pieceColor,
                    testPawn.getPieceImage(), true);

            if (pieceColor.equals("White")) {
                board.findPiece(wPieces, sourceRow, sourceCol).setRow(targetRow);
                board.findPiece(wPieces, targetRow, sourceCol).setCol(targetCol);
            }
            if (pieceColor.equals("Black")) {
                board.findPiece(bPieces, sourceRow, sourceCol).setRow(targetRow);
                board.findPiece(bPieces, targetRow, sourceCol).setCol(targetCol);
            }

            chessBoard[targetRow][targetCol].setPieceObject(newLocPiece);

            // If there was a capture, remove the old piece
            if (pieceColor.equals("White"))
                if (board.findPiece(bPieces, targetRow, targetCol) != null)
                    bPieces.remove(board.findPiece(bPieces, sourceRow, sourceCol));
            if (pieceColor.equals("Black"))
                if (board.findPiece(wPieces, targetRow, targetCol) != null)
                    wPieces.remove(board.findPiece(wPieces, sourceRow, sourceCol));

            // Update the arrayLists
            board.setWhitePieces(wPieces);
            board.setBlackPieces(bPieces);

            // Update move history
            board.addMove(newLocPiece, sourceRow, sourceCol);

            // Clear the source button
            sourceButton.setPieceType(null);
            sourceButton.setPieceColor(null);
            sourceButton.setIcon(null);
            chessBoard[sourceRow][sourceCol] = new EmptySquare("Empty Square", sourceRow, sourceCol, "Empty", null,
                    false);
            chessBoard[sourceRow][sourceCol].setPieceObject(null);

            // Reset the background color of all squares
            resetButtonColors(sourceButton);

            // Update the turn
            whiteTurn = !whiteTurn;
            blackTurn = !blackTurn;
        }

        public void movePieceLogic(Board board, PieceButton sourceButton, PieceButton targetButton) {

            // Board is somehow null
            ArrayList<Piece> wPieces = board.getWhitePieces();
            ArrayList<Piece> bPieces = board.getBlackPieces();

            // Get the chess piece information from the source button
            String pieceType = sourceButton.getPieceType();
            String pieceColor = sourceButton.getPieceColor();
            Icon pieceImage = sourceButton.getIcon();

            // Get coordinates
            int targetRow = targetButton.getRow();
            int targetCol = targetButton.getCol();
            int sourceRow = sourceButton.getRow();
            int sourceCol = sourceButton.getCol();

            // Update the target button with the piece information
            targetButton.setPieceType(pieceType);
            targetButton.setPieceColor(pieceColor);
            targetButton.setIcon(pieceImage);

            // Update the chessBoard array
            chessBoard[targetRow][targetCol] = chessBoard[sourceRow][sourceCol];
            chessBoard[targetRow][targetCol].setRow(targetRow);
            chessBoard[targetRow][targetCol].setCol(targetCol);
            chessBoard[targetRow][targetCol].setOccupied(true);

            // Update the chessBoard arrayList

            // Add the new piece
            Piece newLocPiece = new Piece(pieceType, targetRow, targetCol, pieceColor, pieceImage, true);

            if (pieceColor.equals("White")) {
                board.findPiece(wPieces, sourceRow, sourceCol).setRow(targetRow);
                board.findPiece(wPieces, targetRow, sourceCol).setCol(targetCol);
            }
            if (pieceColor.equals("Black")) {
                board.findPiece(bPieces, sourceRow, sourceCol).setRow(targetRow);
                board.findPiece(bPieces, targetRow, sourceCol).setCol(targetCol);
            }

            chessBoard[targetRow][targetCol].setPieceObject(newLocPiece);

            // If there was a capture, remove the old piece
            if (pieceColor.equals("White"))
                if (board.findPiece(bPieces, targetRow, targetCol) != null)
                    bPieces.remove(board.findPiece(bPieces, sourceRow, sourceCol));
            if (pieceColor.equals("Black"))
                if (board.findPiece(wPieces, targetRow, targetCol) != null)
                    wPieces.remove(board.findPiece(wPieces, sourceRow, sourceCol));

            // Update the arrayLists
            board.setWhitePieces(wPieces);
            board.setBlackPieces(bPieces);

            // Update move history
            board.addMove(newLocPiece, sourceRow, sourceCol);

            // Clear the source button
            sourceButton.setPieceType(null);
            sourceButton.setPieceColor(null);
            sourceButton.setIcon(null);
            chessBoard[sourceRow][sourceCol] = new EmptySquare("Empty Square", sourceRow, sourceCol, "Empty", null,
                    false);
            chessBoard[sourceRow][sourceCol].setPieceObject(null);

            // Reset the background color of all squares
            resetButtonColors(sourceButton);

            // Update the turn
            whiteTurn = !whiteTurn;
            blackTurn = !blackTurn;
        }

        public void resetBoardColors() {
            Color darkSquare = new Color(118, 150, 86);
            Color lightSquare = new Color(238, 238, 210);
            Component[] squares = frame.getContentPane().getComponents();

            for (Component square : squares) {
                if (square instanceof PieceButton) {
                    PieceButton pieceButton = (PieceButton) square;
                    Color color = ((pieceButton.getRow() + pieceButton.getCol()) % 2 == 0) ? lightSquare : darkSquare;
                    pieceButton.setBackground(color);

                    // Needed for debugging
                    pieceButton.getRow();
                    pieceButton.getCol();
                    frame.repaint();
                }
            }
        }

        private void endGame(String winner) {
            String message = "Game Over!\n" + winner + " Wins";
            int option = JOptionPane.showOptionDialog(frame, message, "Game Over", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, new Object[] { "New Game", "Exit" }, "New Game");

            if (option == 0) {
                // Start a new game
                frame.dispose();
                new Game();
            } else {
                // Exit the program
                frame.dispose();
            }
        }

    }

    public static void main(String[] args) {
        new Game();
    }
}