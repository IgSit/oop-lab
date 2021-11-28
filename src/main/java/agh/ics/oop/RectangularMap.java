package agh.ics.oop;

public class RectangularMap extends AbstractWorldMap implements IWorldMap{
    private final int width;
    private final int height;

    public RectangularMap(int width, int height) {
        super();
        this.width = width;
        this.height = height;
    }

    public boolean canMoveTo(Vector2d position) {
        return !isOutsideTheMap(position) && !isOccupied(position);
    }

    public boolean place(Animal newAnimal) {
        Vector2d position = newAnimal.getPosition();
        if (isOccupied(position))
            return false;
        animals.put(position, newAnimal);
        newAnimal.addObserver(this);
        return true;
    }

    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    public Object objectAt(Vector2d position) {
        if (animals.containsKey(position))
            return animals.get(position);
        return null;
    }

    public int[] getDimensions() {
        return new int[] {0, 0, width - 1, height - 1};
    }

    private boolean isOutsideTheMap(Vector2d position) {
        return !position.precedes(new Vector2d(width - 1, height - 1)) ||
               !position.follows(new Vector2d(0, 0));
    }
}
