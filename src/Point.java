public class Point {
    int row, col;

    public int getRow() {
        return row;
    }


    public int getCol() {
        return col;
    }

    public Point(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean overlaps(int r, int c){
        if (row == r && col==c) return true;
        return false;
    }

}
