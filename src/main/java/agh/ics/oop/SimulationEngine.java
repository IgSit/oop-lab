package agh.ics.oop;
import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine{
    private final MoveDirection[] moveDirections;
    private final IWorldMap map;
    private final  List<Animal> animals;

    public List<Animal> getAnimals() {
        return animals;
    }

    public SimulationEngine(MoveDirection[] moveDirections, IWorldMap map, Vector2d[] positions) {
        this.moveDirections = moveDirections;
        this.map = map;
        animals = new ArrayList<>();

        for (Vector2d position : positions) {
            Animal animal = new Animal(map, position);
           if (map.place(animal))
                animals.add(animal);
        }
    }

    public void run() {
        int animalsCount = animals.size();
        for (int i = 0; i < moveDirections.length; i++) {
            Animal movedAnimal = animals.get(i % animalsCount);
            movedAnimal.move(moveDirections[i]);
            // System.out.println(map);
        }
    }
}
