import processing.core.*;

import java.util.ArrayList;

public class Main extends PApplet {
    static public void main(String args[]) {
        PApplet.main(new String[]{"Main"});
    }

    public static int SideLength, PixelSize, Center;
    public static int[][] smallGrid;
    Snake snake;
    Apple apple;
    int SnakeOriginalSize;
    boolean down, up, left, right;
    public static final int SAFE = 0;
    public static final int APPLE = 1;
    public static final int SNAKE = 2;
    boolean alive = true;
    PFont f;

    public void setup() {
        size(600, 600);
        SideLength = 20;
        PixelSize = 30;
        SnakeOriginalSize = 3;
        Center = (SideLength / 2) - 1;
        smallGrid = new int[SideLength][SideLength];

        int SideLengthInLocation = SideLength * PixelSize;
        fill(20, 255, 10);
        rect(0, 0, SideLengthInLocation, SideLengthInLocation);
        for (int r = 0; r < SideLength; r++) {
            for (int c = 0; c < SideLength; c++) {
                rect(PixelSize * c, PixelSize * r, PixelSize, PixelSize);
            }
        }

        apple = new Apple(Center, Center + 3);
        snake = new Snake(SnakeOriginalSize, Center, Center, PixelSize);
        right = true;

        f = createFont("Cambria", 20, true);
    }

    public void draw() {
        if (alive == true) {
            ArrayList<Point> snakeCoordinates = snake.getCoordinatesOfSnake();
            Point frontOfSnake = snakeCoordinates.get(0);

            if (down == true) {
                if (frontOfSnake.getRow() + 1 == apple.getRow() && frontOfSnake.getCol() == apple.getCol()) {
                    snakeCoordinates.add(0, new Point(apple.getRow(), apple.getCol()));
                } else {
                    boolean snakeMoved = snake.moveSnakeVertically(1);
                    if (!snakeMoved) alive = false;
                }
            }
            if (up == true) {
                if (frontOfSnake.getRow() - 1 == apple.getRow() && frontOfSnake.getCol() == apple.getCol()) {
                    snakeCoordinates.add(0, new Point(apple.getRow(), apple.getCol()));
                } else {
                    boolean snakeMoved = snake.moveSnakeVertically(-1);
                    if (!snakeMoved) alive = false;
                }
            }
            if (left == true) {
                if (frontOfSnake.getRow() == apple.getRow() && frontOfSnake.getCol() - 1 == apple.getCol()) {
                    snakeCoordinates.add(0, new Point(apple.getRow(), apple.getCol()));
                } else {
                    boolean snakeMoved = snake.moveSnakeHorizontally(-1);
                    if (!snakeMoved) alive = false;
                }
            }
            if (right == true) {
                if (frontOfSnake.getRow() == apple.getRow() && frontOfSnake.getCol() + 1 == apple.getCol()) {
                    snakeCoordinates.add(0, new Point(apple.getRow(), apple.getCol()));
                } else {
                    boolean snakeMoved = snake.moveSnakeHorizontally(1);
                    if (!snakeMoved) alive = false;
                }
            }
            updateSmallGridWithSnakeAndAppleCoordinates();
            drawBigGridWithDataFromSmallData();
            delay(120);
        } else {
            textFont(f, 36);
            fill(255, 255, 255);
            text("Score: " + snake.getScore(), 250, 300);
        }
    }

    public void updateSmallGridWithSnakeAndAppleCoordinates() {
        for (int r = 0; r < smallGrid.length; r++) {
            for (int c = 0; c < smallGrid[r].length; c++) {
                smallGrid[r][c] = SAFE;
            }
        }

        ArrayList<Point> snakeCoordinates = snake.getCoordinatesOfSnake();
        if (outOfBounds(snakeCoordinates.get(0))) {
            alive = false;
            return;
        }
        for (int i = 0; i < snakeCoordinates.size(); i++) {
            Point p = snakeCoordinates.get(i);
            smallGrid[p.getRow()][p.getCol()] = SNAKE;
        }
        if (snakeCoordinates.get(0).overlaps(apple.getRow(), apple.getCol())) {
            apple.randomlyPlaceApple();
        }
        smallGrid[apple.getRow()][apple.getCol()] = APPLE;
    }

    public boolean outOfBounds(Point p) {
        if (p.getCol() >= SideLength || p.getCol() < 0) return true;
        if (p.getRow() >= SideLength || p.getRow() < 0) return true;
        return false;
    }

    public void drawBigGridWithDataFromSmallData() {
        if (alive) {
            for (int r = 0; r < smallGrid.length; r++) {
                for (int c = 0; c < smallGrid[r].length; c++) {
                    int state = smallGrid[r][c];
                    if (state == SAFE) {
                        fill(138, 240, 65);
                        rect(c * PixelSize, r * PixelSize, PixelSize, PixelSize);
                    }
                    if (state == APPLE) {
                        fill(219, 64, 64);
                        rect(c * PixelSize, r * PixelSize, PixelSize, PixelSize);
                    }
                    if (state == SNAKE) {
                        fill(50, 142, 199);
                        rect(c * PixelSize, r * PixelSize, PixelSize, PixelSize);
                    }
                }
            }
        }
    }

    public void keyPressed() {
        if (key == 'a' && !right) {
            left = true;
            right = false;
            up = false;
            down = false;
        }
        if (key == 'd' && !left) {
            left = false;
            right = true;
            up = false;
            down = false;
        }
        if (key == 's' && !up) {
            left = false;
            right = false;
            up = false;
            down = true;
        }
        if (key == 'w' && !down) {
            left = false;
            right = false;
            up = true;
            down = false;
        }
    }

}


