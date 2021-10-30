package agh.ics.oop;

public class Animal {

    private MapDirection orientation;
    private Vector2d coordinates;

    public Animal() {
        orientation = MapDirection.NORTH;
        coordinates = new Vector2d(2, 2);
    }

    public String toString() {
        return "Position: " + coordinates + ", Orientation: " + orientation;
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT: orientation = orientation.next();
            case LEFT: orientation = orientation.previous();
            case FORWARD:

        }
        }
    }

