package cinema;

public class CinemaRequest {
    private int row;
    private int column;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public CinemaRequest(int row, int column) {
        this.row = row;
        this.column = column;
    }
}
