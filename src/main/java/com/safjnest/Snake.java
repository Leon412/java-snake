package com.safjnest;

import java.util.LinkedList;
import java.util.Deque;

import com.safjnest.App.Direction;

import javafx.scene.paint.Color;

public class Snake {
    public static final Color COLOR = Color.WHITE;
    public static final Color DEAD_COLOR = Color.RED;

    private LinkedList<Point> points;
    private Grid grid;

    private Direction oldDir;
    private Deque<Direction> directionQueue;

    public Deque<Direction> getDirectionQueue() {
        return directionQueue;
    }

    Snake(Grid grid) {
        this.grid = grid;
        this.points = new LinkedList<>();
        this.oldDir = Direction.RIGHT;
        this.directionQueue = new LinkedList<>();
        points.add(new Point(grid.getRows() / 2, grid.getCols() / 2));
    }

    public LinkedList<Point> getPoints() {
        return points;
    }

    public Point getTail() {
        return points.getFirst();
    }

    public Point getHead() {
        return points.getLast();
    }

    public boolean isIntersecting() {
        for(int i = 0; i < points.size()-1; i++) {
            if(points.get(i).equals(getHead())) {
                return true;
            }
        }
        return false;
    }

    public boolean isSafe() {
        return !isIntersecting();
    }

    public void addPoint() {
        int x = getHead().getX();
        int y = getHead().getY();
        Direction dir = oldDir;

        if (!directionQueue.isEmpty()) {
            dir = directionQueue.poll();
        }
        switch (dir) {
            case UP:
                y--;
                break;
            case DOWN:
                y++;
                break;
            case LEFT:
                x--;
                break;
            case RIGHT:
                x++;
                break;
            default:
                throw new IllegalArgumentException();
        }

        oldDir = dir;
        points.add(grid.wrap(new Point(x, y)));
    }

    public void move() {
        addPoint();
        points.removeFirst();
    }

    public void grow() {
        addPoint();
    }

    public boolean checkMove(Direction move) {
        if (points.size() > 1) {
            Direction dir = directionQueue.isEmpty() ? oldDir : directionQueue.peekLast();
            
            switch (dir) {
                case UP:
                    if (move == Direction.DOWN) return false;
                    break;
                case DOWN:
                    if (move == Direction.UP) return false;
                    break;
                case LEFT:
                    if (move == Direction.RIGHT) return false;
                    break;
                case RIGHT:
                    if (move == Direction.LEFT) return false;
                    break;
                default:
                    break;
            }
        }
        return true;
    }

    public void setMove(Direction move) {
        if (checkMove(move)) {
            if (directionQueue.size() < 2) {
                directionQueue.add(move);
            }
        }
    }
}
