package agh.ics.oop;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import static java.lang.Math.*;
import static java.util.Collections.shuffle;

public class GrassField extends AbstractWorldMap implements IWorldMap {
    private final int grassCounter;

    private final LinkedHashMap<Vector2d, Grass> grasses;

    public GrassField(int grassCounter) {
        super();
        this.grassCounter = grassCounter;
        grasses = generateGrass();
    }

    public boolean canMoveTo(Vector2d position) {
        return !isOccupiedByAnimal(position);
    }

    public boolean place(Animal newAnimal) {
        Vector2d position = newAnimal.getPosition();
        if (isOccupiedByAnimal(position))
            return false;
        animals.put(position, newAnimal);
        newAnimal.addObserver(this);
        return true;
    }

    /* pierwotny pomysł zakładał sprawdzanie, czy znajduje się na danej pozycji obiekt klasy Animal
    *  (bo Grass nie blokuje miejsca dla Animal), natomiast poniższe wyrażenie wynika z implementacji MapVisualiser
    * (musimy sprawdzać, czy jest cokolwiek, bo w przypadku sprawdzania tylko, czy jest obiektem klasy Animal nie
    * rysuje się Grass w Map Visualiser)
    */
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    public boolean isOccupiedByAnimal(Vector2d position) {
        return objectAt(position) instanceof Animal;
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

    public int[] getDimensions() {
        int maxX = 0, minX = 0, maxY = 0, minY = 0;
        List<Vector2d> positions = new ArrayList<>(animals.keySet());
        positions.addAll(new ArrayList<>(grasses.keySet()));
        for (Vector2d position : positions) {
            minX = min(minX, position.x());
            minY = min(minY, position.y());
            maxX = max(maxX, position.x());
            maxY = max(maxY, position.y());
        }
        return new int[]{minX, minY, maxX, maxY};
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
        }
        return result;
    }
}
