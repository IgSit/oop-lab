package agh.ics.oop;
import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap{
    private final int width;
    private final int height;
    private final List<Animal> animals;

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
        animals = new ArrayList<>();
    }

    public boolean canMoveTo(Vector2d position) {
        return !isOutsideTheMap(position) && !isOccupied(position);
    }

    public boolean place(Animal newAnimal) {
        Vector2d position = newAnimal.getPosition();
        if (isOccupied(position))
            return false;
        animals.add(newAnimal);
        return true;
    }

    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    public Object objectAt(Vector2d position) {
        for (Animal animal : animals) {
            Vector2d otherPosition = animal.getPosition();
            if (position.equals(otherPosition))
                return animal;
        }
        return null;
    }

    @Override
    public String toString() {
        MapVisualiser mapVisualiser = new MapVisualiser(this);
        return mapVisualiser.draw(new Vector2d(0, 0), new Vector2d(width - 1, height - 1));
    }

    private boolean isOutsideTheMap(Vector2d position) {
        return !position.precedes(new Vector2d(width - 1, height - 1)) ||
               !position.follows(new Vector2d(0, 0));
    }
}
