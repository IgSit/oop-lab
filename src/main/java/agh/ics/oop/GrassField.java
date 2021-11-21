package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.*;
import static java.util.Collections.shuffle;

public class GrassField extends AbstractWorldMap implements IWorldMap {
    private final int grassCounter;
    private final List<Animal> animals;
    private final List<Grass> grasses;

    public GrassField(int grassCounter) {
        this.grassCounter = grassCounter;
        animals = new ArrayList<>();
        grasses = generateGrass();
    }

    public boolean canMoveTo(Vector2d position) {
        return !isOccupiedByAnimal(position);
    }

    public boolean place(Animal newAnimal) {
        Vector2d position = newAnimal.getPosition();
        if (isOccupiedByAnimal(position))
            return false;
        animals.add(newAnimal);
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
        for (Animal animal : animals) {
            Vector2d otherPosition = animal.getPosition();
            if (position.equals(otherPosition))
                return animal;
        }
        for (Grass grass : grasses) {
            Vector2d otherPosition = grass.getPosition();
            if (position.equals(otherPosition))
                return grass;
        }
        return null;
    }

    public void clearGrass() {   // na potrzeby testów
        grasses.clear();
    }   // na potrzeby testów

    public void addGrass(Grass grass) {   // na potrzeby testów
        if (!isOccupied(grass.getPosition()))
            grasses.add(grass);
    }

    public int[] getDimensions() {
        int maxX = 0, minX = 0, maxY = 0, minY = 0;
        for (Animal animal : animals) {
            Vector2d position = animal.getPosition();
            maxX = max(maxX, position.x());
            minX = min(minX, position.x());
            maxY = max(maxY, position.y());
            minY = min(minY, position.y());
        }
        for (Grass grass : grasses) {
            Vector2d position = grass.getPosition();
            maxX = max(maxX, position.x());
            minX = min(minX, position.x());
            maxY = max(maxY, position.y());
            minY = min(minY, position.y());
        }
        return new int[]{minX, minY, maxX, maxY};
    }

    private List<Grass> generateGrass() {
        int maxRange = (int) round(sqrt(grassCounter * 10));
        List<Grass> allCombinations = new ArrayList<>();
        for (int i = 0; i <= maxRange; i++) {
            for (int j = 0; j <= maxRange; j++) {
                Vector2d position = new Vector2d(i, j);
                allCombinations.add(new Grass(position));
            }
        }
        shuffle(allCombinations);
        return allCombinations.subList(0, grassCounter);
    }

}
