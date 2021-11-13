package agh.ics.oop;

public class Animal {

    private MapDirection orientation;
    private Vector2d position;
    private final IWorldMap map;

    public MapDirection getOrientation() {
        return orientation;
    }

    public Vector2d getPosition() {
        return position;
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        orientation = MapDirection.NORTH;
        position = initialPosition;
    }

    public String toString() {
        return switch (orientation) {
            case EAST -> "E";
            case SOUTH -> "S";
            case WEST -> "W";
            case NORTH -> "N";
        };
    }

    private Vector2d fixCoordinates() {
        position = position.lowerLeft(new Vector2d(position.x(), 4));  // wyjście od góry
        position = position.lowerLeft(new Vector2d(4, position.y()));  // wyjście z prawej
        position = position.upperRight(new Vector2d(position.x(), 0)); // wyjście z dołu
        position = position.upperRight(new Vector2d(0, position.y())); // wyjście z lewej
        return position;
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> orientation = orientation.next();
            case LEFT -> orientation = orientation.previous();
            case FORWARD, BACKWARD -> {
                Vector2d unitVector = orientation.toUnitVector();
                if (direction == MoveDirection.BACKWARD)
                    unitVector = unitVector.opposite();
                Vector2d newPosition = position.add(unitVector);
                if (map.canMoveTo(newPosition))
                    position = newPosition;
            }
        }
    }
}

