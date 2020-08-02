public class Apple {
    int row, col;
    public Apple(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
    public void randomlyPlaceApple() {
        Point rand = randomPointLocation();
        while (Main.smallGrid[rand.getRow()][rand.getCol()] !=Main.SAFE) {
            rand = randomPointLocation();
        }
        setRow(rand.getRow());
        setCol(rand.getCol());
    }

    public Point randomPointLocation() {
        int r = (int) (Math.random()*Main.SideLength);
        int c = (int) (Math.random()*Main.SideLength);
        return new Point(r,c);
    }
}

