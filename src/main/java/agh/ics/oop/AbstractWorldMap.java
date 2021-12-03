package agh.ics.oop;


import java.util.LinkedHashMap;

abstract public class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
    protected final LinkedHashMap<Vector2d, Animal> animals;
    protected final MapVisualiser mapVisualiser;

    protected AbstractWorldMap() {
        animals = new LinkedHashMap<>();
        mapVisualiser = new MapVisualiser(this);
    }

    protected abstract Vector2d getLowerLeft();
    protected abstract Vector2d getUpperRight();

    @Override
    public String toString() {
        return mapVisualiser.draw(this.getLowerLeft(), this.getUpperRight());
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Animal animal = animals.remove(oldPosition);
        animals.put(newPosition, animal);
    }

    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    public boolean place(Animal newAnimal) {
        Vector2d position = newAnimal.getPosition();
        if (canPlace(position)) {
            animals.put(position, newAnimal);
            newAnimal.addObserver(this);
            return true;
        }
        throw new IllegalArgumentException("Position " + position + " is already occupied");
    }

    public boolean canPlace(Vector2d position) {
        MapObject mapObject = (MapObject) this.objectAt(position);
        if (mapObject == null)
            return true;
        else
            return mapObject.canShareSpace();
    }
}
