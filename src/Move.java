public class Move {
    private Piece piece;
    private int pastRow;
    private int pastCol;

    public Move(Piece piece, int pastRow, int pastCol) {
        this.piece = piece;
        this.pastRow = pastRow;
        this.pastCol = pastCol;
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

}
