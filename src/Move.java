public class Move {
    private Piece piece;
    private int currentRow;
    private int currentCol;
    private int pastRow;
    private int pastCol;

    public Move(Piece piece, int currentRow, int currentCol, int pastRow, int pastCol) {
        this.piece = piece;
        this.currentRow = currentRow;
        this.currentCol = currentCol;
        this.pastRow = pastRow;
        this.pastCol = pastCol;
    }

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

    public int getCurrentRow() {
        return currentRow;
    }

    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }

    public int getCurrentCol() {
        return currentCol;
    }

    public void setCurrentCol(int currentCol) {
        this.currentCol = currentCol;
    }

}
