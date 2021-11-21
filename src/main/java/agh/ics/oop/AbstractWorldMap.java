package agh.ics.oop;


abstract public class AbstractWorldMap implements IWorldMap{

    protected abstract int[] getDimensions();

    @Override
    public String toString() {
        int[] dimensions = getDimensions();
        Vector2d lowerLeft = new Vector2d(dimensions[0], dimensions[1]);
        Vector2d upperRight = new Vector2d(dimensions[2], dimensions[3]);
        MapVisualiser mapVisualiser = new MapVisualiser(this);
        return mapVisualiser.draw(lowerLeft, upperRight);
    }
}
