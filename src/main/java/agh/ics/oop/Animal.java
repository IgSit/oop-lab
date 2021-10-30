package agh.ics.oop;

public class Animal {

    private MapDirection orientation;
    private Vector2d coordinates;

    public MapDirection getOrientation() {
        return orientation;
    }

    public Vector2d getCoordinates() {
        return coordinates;
    }

    public Animal() {
        orientation = MapDirection.NORTH;
        coordinates = new Vector2d(2, 2);
    }

    public String toString() {
        return "Pozycja: " + coordinates + ", Orientacja: " + orientation;
    }

    private Vector2d fixCoordinates() {
        coordinates = coordinates.lowerLeft(new Vector2d(coordinates.x(), 4));  // wyjście od góry
        coordinates = coordinates.lowerLeft(new Vector2d(4, coordinates.y()));  // wyjście z prawej
        coordinates = coordinates.upperRight(new Vector2d(coordinates.x(), 0)); // wyjście z dołu
        coordinates = coordinates.upperRight(new Vector2d(0, coordinates.y())); // wyjście z lewej
        return coordinates;
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> orientation = orientation.next();
            case LEFT -> orientation = orientation.previous();
            case FORWARD -> {
                Vector2d forward = orientation.toUnitVector();
                coordinates = coordinates.add(forward);
                coordinates = fixCoordinates();
            }
            case BACKWARD -> {
                Vector2d backward = orientation.toUnitVector();
                coordinates = coordinates.subtract(backward);
                coordinates = fixCoordinates();
            }
        }
    }
    public void moveApi(String[] args) {
        OptionsParser parser = new OptionsParser();
        MoveDirection[] manual = parser.parse(args);
        for (MoveDirection step : manual) this.move(step);
    }

}

