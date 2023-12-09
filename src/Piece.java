import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.JComponent;

public class Piece extends JComponent {
    private Piece pieceObject;
    private String pieceType;
    private int row;
    private int col;
    private String pieceColor;
    private boolean isOccupied;
    private Icon pieceImage;

    public Piece(String pieceType, int row, int col, String pieceColor, Icon pieceImage, boolean isOccupied) {
        this.pieceType = pieceType;
        this.row = row;
        this.col = col;
        this.pieceColor = pieceColor;
        this.pieceImage = pieceImage;
        this.isOccupied = isOccupied;
    }

    // Same color pieces
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Piece otherPiece = (Piece) obj;
        return pieceColor == otherPiece.pieceColor;
    }

    public boolean canMove(int newRow, int newCol) {
        return false;
    }

    public boolean canMove(Board board, Piece[][] chessBoard, int i, int j) {
        if (!(chessBoard[i][j].isOccupied())) {
            return canMove(i, j);
        }
        return false;
    }

    public ArrayList<Piece> getPossibleMoves(Board board, Piece[][] chessBoard) {
        ArrayList<Piece> allPossibleMoves = new ArrayList<Piece>();
        for (int i = 0; i < chessBoard.length; i++) {
            for (int k = 0; k < chessBoard[0].length; k++) {
                if (canMove(board, chessBoard, i, k)) {
                    allPossibleMoves.add(chessBoard[i][k]);
                }
            }
        }
        return allPossibleMoves;
    }

    public void move(int targetRow, int targetCol, Board board, Piece[][] chessBoard) {

        ArrayList<Piece> wPieces = board.getWhitePieces();
        ArrayList<Piece> bPieces = board.getBlackPieces();
        int sourceRow = this.getRow();
        int sourceCol = this.getCol();

        switch (getPieceType()) {
            case "White":
                Piece arrayListPieceW = wPieces.get(wPieces.indexOf(pieceObject));
                arrayListPieceW.setRow(targetRow);
                arrayListPieceW.setCol(targetCol);
                wPieces.remove(pieceObject);
                wPieces.add(arrayListPieceW);
                break;

            case "Black":
                Piece arrayListPieceB = bPieces.get(wPieces.indexOf(pieceObject));
                arrayListPieceB.setRow(targetRow);
                arrayListPieceB.setCol(targetCol);
                bPieces.remove(pieceObject);
                bPieces.add(arrayListPieceB);
                break;
        }
        chessBoard[targetRow][targetCol] = chessBoard[sourceRow][sourceCol];
        chessBoard[targetRow][targetCol].setRow(targetRow);
        chessBoard[targetRow][targetCol].setCol(targetCol);
        chessBoard[targetRow][targetCol].setOccupied(true);
        board.setChessBoard(chessBoard);
    }

    public void capture(int newRow, int newCol, Board board, Piece[][] chessBoard) {

        ArrayList<Piece> wPieces = board.getWhitePieces();
        ArrayList<Piece> bPieces = board.getBlackPieces();

        if (getPieceColor() != chessBoard[newRow][newCol].getPieceColor()) {
            switch (getPieceColor()) {
                case "White":

                    for (int i = 0; i < bPieces.size(); i++)
                        if (bPieces.get(i).getRow() == getRow() && bPieces.get(i).getCol() == getCol())
                            bPieces.remove(i);

                    for (int i = 0; i < wPieces.size(); i++) {
                        if (wPieces.get(i).equals(chessBoard[getRow()][getCol()])) {
                            wPieces.get(i).setRow(newRow);
                            wPieces.get(i).setCol(newCol);
                        }
                    }

                case "Black":

                    for (int i = 0; i < wPieces.size(); i++)
                        if (wPieces.get(i).getRow() == getRow() && wPieces.get(i).getCol() == getCol())
                            wPieces.remove(i);

                    for (int i = 0; i < bPieces.size(); i++) {
                        if (bPieces.get(i).equals(chessBoard[getRow()][getCol()])) {
                            bPieces.get(i).setRow(newRow);
                            bPieces.get(i).setCol(newCol);
                        }
                    }
            }
            chessBoard[getRow()][getCol()] = null;
            chessBoard[newRow][newCol].setPieceColor(getPieceColor());
            chessBoard[newRow][newCol].setPieceImage(getPieceImage());
            chessBoard[newRow][newCol].setPieceType(getPieceType());
            board.setChessBoard(chessBoard);
            board.setBlackPieces(bPieces);
            board.setWhitePieces(wPieces);
        }
    }

    public ArrayList<Piece> possibleMoves(Piece pieceObject, Board board, Piece[][] chessBoard) {
        ArrayList<Piece> legalMoves = new ArrayList<Piece>();
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard[0].length; j++) {
                if (pieceObject.canMove(board, chessBoard, i, j)) {

                    // Check to see if the piece is a sliding piece then check to see if the path is
                    // free
                    if (pieceObject instanceof Bishop) {
                        if (checkDiagonals(board, chessBoard, pieceObject, i, j)) {
                            // Create a new Piece object representing the move
                            Piece move = new Piece(getPieceType(), i, j, getPieceColor(), getPieceImage(), isOccupied);
                            legalMoves.add(move);
                        }
                    }
                    if (pieceObject instanceof Rook) {
                        if (checkHorizontals(board, chessBoard, pieceObject, i, j)
                                || checkVerticals(board, chessBoard, pieceObject, i, j)) {
                            Piece move = new Piece(getPieceType(), i, j, getPieceColor(), getPieceImage(), isOccupied);
                            legalMoves.add(move);
                        }
                    }
                    if (pieceObject instanceof Queen) {
                        if (checkDiagonals(board, chessBoard, pieceObject, i, j)
                                || checkVerticals(board, chessBoard, pieceObject, i, j)
                                || checkHorizontals(board, chessBoard, pieceObject, i, j)) {
                            Piece move = new Piece(getPieceType(), i, j, getPieceColor(), getPieceImage(), isOccupied);
                            legalMoves.add(move);
                        }
                    } else if (pieceObject instanceof Pawn || pieceObject instanceof King
                            || pieceObject instanceof Knight) {
                        Piece move = new Piece(getPieceType(), i, j, getPieceColor(), getPieceImage(), isOccupied);
                        legalMoves.add(move);
                    }
                }
            }
        }
        return legalMoves;
    }

    public boolean checkDiagonals(Board board, Piece[][] chessBoard, Piece pieceObject, int targetR, int targetC) {
        int deltaX = Math.abs(targetR - this.getRow());
        int deltaY = Math.abs(targetC - this.getCol());

        // Check if the move is diagonal (deltaX must equal deltaY)
        if (deltaX != deltaY) {
            return false;
        }

        int rDirection = (targetR > this.getRow()) ? 1 : -1;
        int cDirection = (targetC > this.getCol()) ? 1 : -1;

        // Check each square along the diagonal path
        for (int i = 1; i < deltaX; i++) {
            int r = this.getRow() + i * rDirection;
            int c = this.getCol() + i * cDirection;

            // If there is a piece on any square along the diagonal path, return false
            if (!(chessBoard[r][c] instanceof EmptySquare)) {
                return false;
            }
        }

        // If the loop completes without finding any pieces in the path, it's clear
        return true;
    }

    // Check if there are any pieces blocking the horizontal path for a piece
    public boolean checkHorizontals(Board board, Piece[][] chessBoard, Piece pieceObject, int targetR, int targetC) {
        if (targetR != this.getRow()) {
            return false; // The move is not horizontal
        }

        int minCol = Math.min(targetC, this.getCol());
        int maxCol = Math.max(targetC, this.getCol());

        // Check each square along the horizontal path
        for (int col = minCol + 1; col < maxCol; col++) {
            if (!(chessBoard[this.getRow()][col] instanceof EmptySquare)) {
                return false; // There's a piece in the way
            }
        }

        // If the loop completes without finding any pieces in the path, it's clear
        return true;
    }

    public boolean checkVerticals(Board board, Piece[][] chessBoard, Piece pieceObject, int targetR, int targetC) {
        if (targetC != this.getCol()) {
            return false; // The move is not vertical
        }

        int minRow = Math.min(targetR, this.getRow());
        int maxRow = Math.max(targetR, this.getRow());

        // Check each square along the vertical path
        for (int row = minRow + 1; row < maxRow; row++) {
            if (!(chessBoard[row][this.getCol()] instanceof EmptySquare)) {
                return false; // There's a piece in the way
            }
        }

        // If the loop completes without finding any pieces in the path, it's clear
        return true;
    }

    public String getPieceType() {
        return pieceType;
    }

    public void setPieceType(String pieceType) {
        this.pieceType = pieceType;
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

    public String getPieceColor() {
        return pieceColor;
    }

    public void setPieceColor(String pieceColor) {
        this.pieceColor = pieceColor;
    }

    public Icon getPieceImage() {
        return pieceImage;
    }

    public void setPieceImage(Icon pieceImage) {
        this.pieceImage = pieceImage;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public boolean isSquareOcupied(int row, int col, Board board, Piece[][] chessBoard) {
        return chessBoard[row][col].isOccupied();
    }

    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public Piece getPieceObject() {
        return pieceObject;
    }

    public void setPieceObject(Piece pieceObject) {
        this.pieceObject = pieceObject;
    }

    @Override
    public String toString() {
        return "Piece [pieceObject=" + pieceObject + ", pieceType=" + pieceType + ", row=" + row + ", col=" + col
                + ", pieceColor=" + pieceColor + ", isOccupied=" + isOccupied + ", pieceImage=" + pieceImage + "]";
    }

}
