package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class SimulationEngineTest {

    @Test
    void run1() {
        MoveDirection[] directions = new MoveDirection[]
                {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT};
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        SimulationEngine engine = new SimulationEngine(directions, map, positions);

        engine.run();

        List<Animal> animals = engine.getAnimals();

        Animal a0 = animals.get(0);
        Animal a1 = animals.get(1);

        Vector2d pos0 = a0.getPosition();
        MapDirection orient0 = a0.getOrientation();
        Vector2d pos1 = a1.getPosition();
        MapDirection orient1 = a1.getOrientation();

        Vector2d ePos0 = new Vector2d(2, 3);
        MapDirection eOrient0 = MapDirection.EAST;
        Vector2d ePos1 = new Vector2d(3, 3);
        MapDirection eOrient1 = MapDirection.WEST;

        assertEquals(pos0, ePos0);
        assertEquals(orient0, eOrient0);
        assertEquals(pos1, ePos1);
        assertEquals(orient1, eOrient1);

    }
    @Test
    void run2() {
        MoveDirection[] directions = new MoveDirection[]
                {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT,
                MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.RIGHT,
                MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD};
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        SimulationEngine engine = new SimulationEngine(directions, map, positions);

        engine.run();

        List<Animal> animals = engine.getAnimals();

        Animal a0 = animals.get(0);
        Animal a1 = animals.get(1);

        Vector2d pos0 = a0.getPosition();
        MapDirection orient0 = a0.getOrientation();
        Vector2d pos1 = a1.getPosition();
        MapDirection orient1 = a1.getOrientation();

        Vector2d ePos0 = new Vector2d(2, 0);
        MapDirection eOrient0 = MapDirection.SOUTH;
        Vector2d ePos1 = new Vector2d(3, 4);
        MapDirection eOrient1 = MapDirection.NORTH;

        assertEquals(pos0, ePos0);
        assertEquals(orient0, eOrient0);
        assertEquals(pos1, ePos1);
        assertEquals(orient1, eOrient1);

    }
}