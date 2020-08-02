import java.util.ArrayList;

public class Snake {
    private int length, startinglength;
    private int GridRLoc, GridCLoc, x, y;
    private ArrayList<Point> snakeCoordinates = new ArrayList<Point>();

    public Snake(int startinglength, int r, int c, int pixelsize) {
        this.startinglength = startinglength;
        this.length = startinglength;
        this.GridRLoc = r * pixelsize;
        this.GridCLoc = r * pixelsize;
        for (int i = 0; i < startinglength; i++) {
            Point p = new Point(r, c - i);
            snakeCoordinates.add(p);
        }
    }


    public int getScore() {
        return getLength() - startinglength;
    }

    public int getLength() {
        return snakeCoordinates.size();
    }


    public boolean moveSnakeHorizontally(int x) {
        Point frontOfSnake = snakeCoordinates.get(0);
        Point moveSnake = new Point(frontOfSnake.getRow(), frontOfSnake.getCol() + x);
        for (int i = 0; i < snakeCoordinates.size(); i++) {
            Point snakeCoordinate = snakeCoordinates.get(i);
            if (moveSnake.getRow() == snakeCoordinate.getRow() && moveSnake.getCol() == snakeCoordinate.getCol()) {
                return false;
            }
        }
        snakeCoordinates.add(0, new Point(frontOfSnake.getRow(), frontOfSnake.getCol() + x));
        snakeCoordinates.remove(snakeCoordinates.size() - 1);
        return true;
    }

    public boolean moveSnakeVertically(int y) {
        Point frontOfSnake = snakeCoordinates.get(0);
        Point moveSnake = new Point(frontOfSnake.getRow() + y, frontOfSnake.getCol());
        for (int i = 0; i < snakeCoordinates.size(); i++) {
            Point snakeCoordinate = snakeCoordinates.get(i);
            if (moveSnake.getRow()==snakeCoordinate.getRow() && moveSnake.getCol()==snakeCoordinate.getCol()) {
                return false;
            }
        }
        snakeCoordinates.add(0, new Point(frontOfSnake.getRow() + y, frontOfSnake.getCol()));
        snakeCoordinates.remove(snakeCoordinates.size() - 1);
        return true;
    }

    public ArrayList<Point> getCoordinatesOfSnake() {
        return snakeCoordinates;
    }
}




