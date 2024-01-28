public class Move {
    private Piece piece;
    private int pastRow;
    private int pastCol;
    private Piece pieceCaptured;

    public Move(Piece piece, int pastRow, int pastCol, Piece pieceCaptured) {
        this.piece = piece;
        this.pastRow = pastRow;
        this.pastCol = pastCol;
        this.pieceCaptured = pieceCaptured;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getPastRow() {
        return pastRow;
    }

    public void setPastRow(int pastRow) {
        this.pastRow = pastRow;
    }

    public int getPastCol() {
        return pastCol;
    }

    public void setPastCol(int pastCol) {
        this.pastCol = pastCol;
    }

    public int getCurrentRow() {
        return piece.getRow();
    }

    public void setCurrentRow(int currentRow) {
        piece.setRow(currentRow);
    }

    public int getCurrentCol() {
        return piece.getCol();
    }

    public void setCurrentCol(int currentCol) {
        piece.setCol(currentCol);
    }

    public Piece getPieceCaptured() {
        return pieceCaptured;
    }

    public void setPieceCaptured(Piece pieceCaptured) {
        this.pieceCaptured = pieceCaptured;
    }

}
