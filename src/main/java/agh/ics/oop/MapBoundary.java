package agh.ics.oop;

import java.util.ArrayList;

public class MapBoundary implements IPositionChangeObserver{
    private Vector2d lowerLeft;
    private Vector2d upperRight;
    private final ArrayList<Vector2d> positions;

    public MapBoundary() {
        lowerLeft = new Vector2d(0, 0);
        upperRight = new Vector2d(0, 0);
        positions = new ArrayList<>();
    }

    public Vector2d getLowerLeft() {
        return lowerLeft;
    }

    public Vector2d getUpperRight() {
        return upperRight;
    }


    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        positions.remove(oldPosition);
        positions.add(newPosition);

        if (oldPosition.x() == lowerLeft.x() || oldPosition.y() == lowerLeft.y()) {
            if (newPosition.precedes(lowerLeft)) // tylko zwiększamy limit mapy
                lowerLeft = newPosition;
            else
                if (oldPosition.equals(lowerLeft)) // musimy szukać lowerLeft, bo to on się ruszył
                    updateLowerLeft();
                else // jakiś inny element "wyszedł" poza obecną mapę, wystarczy wziąć lewy dolny róg z obydwu
                    lowerLeft = lowerLeft.lowerLeft(newPosition);

        }
        else if (oldPosition.x() == upperRight.x() || oldPosition.y() == upperRight.y()) {
            if (newPosition.follows(upperRight))  // znowu tylko zwiększamy mapę
                upperRight = newPosition;
            else
            if (oldPosition.equals(upperRight)) // musimy szukać upperRight, bo to on się ruszył
                updateUpperRight();
            else // jakiś inny element "wyszedł" poza obecną mapę, wystarczy wziąć prawy górny róg z obydwu
                upperRight = upperRight.upperRight(newPosition);
            }
    }

    public void addMapObject(MapObject object) {
        Vector2d newObjectPosition = object.getPosition();
        positions.add(newObjectPosition);
        lowerLeft = lowerLeft.lowerLeft(newObjectPosition);
        upperRight = upperRight.upperRight(newObjectPosition);
    }

    private void updateLowerLeft() {
        lowerLeft = positions.get(0);
        for (Vector2d position: positions) {
            lowerLeft = lowerLeft.lowerLeft(position);
        }
    }

    private void updateUpperRight() {
        upperRight = positions.get(0);
        for (Vector2d position: positions) {
            upperRight = upperRight.upperRight(position);
        }
    }
}
