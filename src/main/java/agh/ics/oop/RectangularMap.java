package agh.ics.oop;

public class RectangularMap extends AbstractWorldMap implements IWorldMap{
    private final int width;
    private final int height;
    private final Vector2d lowerLeft;
    private final Vector2d upperRight;

    public RectangularMap(int width, int height) {
        super();
        this.width = width;
        this.height = height;
        lowerLeft = new Vector2d(0 ,0);
        upperRight = new Vector2d(width - 1, height - 1);
    }

    public Vector2d getLowerLeft() {
        return lowerLeft;
    }

    public Vector2d getUpperRight() {
        return upperRight;
    }

    public boolean canMoveTo(Vector2d position) {
        return !isOutsideTheMap(position) && !isOccupied(position);
    }

    public Object objectAt(Vector2d position) {
        if (animals.containsKey(position))
            return animals.get(position);
        return null;
    }

    private boolean isOutsideTheMap(Vector2d position) {
        return !position.precedes(upperRight) ||
               !position.follows(lowerLeft);
    }
}
