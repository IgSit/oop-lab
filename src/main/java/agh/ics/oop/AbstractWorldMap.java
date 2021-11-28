package agh.ics.oop;


import java.util.LinkedHashMap;

abstract public class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
    protected final LinkedHashMap<Vector2d, Animal> animals;

    protected AbstractWorldMap() {
        animals = new LinkedHashMap<>();
    }

    protected abstract int[] getDimensions();

    @Override
    public String toString() {
        int[] dimensions = getDimensions();
        Vector2d lowerLeft = new Vector2d(dimensions[0], dimensions[1]);
        Vector2d upperRight = new Vector2d(dimensions[2], dimensions[3]);
        MapVisualiser mapVisualiser = new MapVisualiser(this);
        return mapVisualiser.draw(lowerLeft, upperRight);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Animal animal = animals.remove(oldPosition);
        animals.put(newPosition, animal);
    }
}
