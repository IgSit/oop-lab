package agh.ics.oop;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import static java.lang.Math.*;
import static java.util.Collections.shuffle;

public class GrassField extends AbstractWorldMap implements IWorldMap {
    private final int grassCounter;
    private final MapBoundary mapBoundary;
    private final LinkedHashMap<Vector2d, Grass> grasses;

    public GrassField(int grassCounter) {
        super();
        this.grassCounter = grassCounter;
        mapBoundary = new MapBoundary();
        grasses = generateGrass();
    }

    public Vector2d getLowerLeft() {
        return mapBoundary.getLowerLeft();
    }

    public Vector2d getUpperRight() {
        return mapBoundary.getUpperRight();
    }

    public boolean canMoveTo(Vector2d position) {
        return canPlace(position);
    }

    @Override
    public boolean place(Animal newAnimal) {
        super.place(newAnimal);
        newAnimal.addObserver(mapBoundary);
        mapBoundary.addMapObject(newAnimal);
        return true;
    }

    /* Poniższa kolejność gwarantuje, że przy rysowaniu mapy w przypadku Animal i Grass na tej samej pozycji
    *  narysowany obiekt klasy Animal (patrz implementacja MapVisualiser)
    */
    public Object objectAt(Vector2d position) {
        if (animals.containsKey(position))
            return animals.get(position);
        if (grasses.containsKey(position))
            return grasses.get(position);
        return null;
    }

    public void clearGrass() {   // na potrzeby testów
        grasses.clear();
    }

    public void addGrass(Grass grass) {   // na potrzeby testów
        if (!isOccupied(grass.getPosition()))
            grasses.put(grass.getPosition(), grass);
    }

    private LinkedHashMap<Vector2d, Grass> generateGrass() {
        int maxRange = (int) round(sqrt(grassCounter * 10));
        List<Grass> allCombinations = new ArrayList<>();
        for (int i = 0; i <= maxRange; i++) {
            for (int j = 0; j <= maxRange; j++) {
                Vector2d position = new Vector2d(i, j);
                allCombinations.add(new Grass(position));
            }
        }
        shuffle(allCombinations);
        allCombinations = allCombinations.subList(0, grassCounter);
        LinkedHashMap<Vector2d, Grass> result = new LinkedHashMap<>();
        for (Grass grass : allCombinations) {
            result.put(grass.getPosition(), grass);
            grass.addObserver(mapBoundary);
            mapBoundary.addMapObject(grass);
        }
        return result;
    }
}
