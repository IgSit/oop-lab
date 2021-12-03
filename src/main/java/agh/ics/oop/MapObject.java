package agh.ics.oop;

import java.util.HashSet;

public abstract class MapObject {
    protected Vector2d position;
    protected final HashSet<IPositionChangeObserver> observers;

    public MapObject(Vector2d position) {
        this.position = position;
        observers = new HashSet<>();
    }

    public abstract boolean canShareSpace();

    public Vector2d getPosition() {
        return position;
    }

    public void addObserver(IPositionChangeObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer) {
        observers.remove(observer);
    }

    protected void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        for (IPositionChangeObserver observer : observers) {
            observer.positionChanged(oldPosition, newPosition);
        }
    }
}
