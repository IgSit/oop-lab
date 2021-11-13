package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    void move() {
        RectangularMap map1 = new RectangularMap(5, 5);
        Animal rover1 = new Animal(map1, new Vector2d(2, 2));

        MoveDirection[] moveDirections1 = new  MoveDirection[]
                {MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD};

        for (MoveDirection direction : moveDirections1)
            rover1.move(direction);

        Vector2d resPos1 = rover1.getPosition();
        MapDirection resOrient1 = rover1.getOrientation();

        Vector2d expectedPos1 = new Vector2d(4, 2);
        MapDirection expectedOrient1 = MapDirection.EAST;

        assertEquals(expectedPos1, resPos1);
        assertEquals(expectedOrient1, resOrient1);

        // -------------------------------------------------

        RectangularMap map2 = new RectangularMap(5, 5);
        Animal rover2 = new Animal(map2, new Vector2d(2, 2));

        MoveDirection[] moveDirections2 = new  MoveDirection[]
                {MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.FORWARD};

        for (MoveDirection direction : moveDirections2)
            rover2.move(direction);

        Vector2d resPos2 = rover2.getPosition();
        MapDirection resOrient2 = rover2.getOrientation();

        Vector2d expectedPos2 = new Vector2d(0, 0);
        MapDirection expectedOrient2 = MapDirection.SOUTH;

        assertEquals(expectedPos2, resPos2);
        assertEquals(expectedOrient2, resOrient2);

        // --------------------------------------------------

        RectangularMap map3 = new RectangularMap(5, 5);
        Animal rover3 = new Animal(map3, new Vector2d(2, 2));

        MoveDirection[] moveDirections3 = new  MoveDirection[]
                {MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.RIGHT,
                MoveDirection.BACKWARD};

        for (MoveDirection direction : moveDirections3)
            rover3.move(direction);

        Vector2d resPos3 = rover3.getPosition();
        MapDirection resOrient3 = rover3.getOrientation();

        Vector2d expectedPos3 = new Vector2d(1, 4);
        MapDirection expectedOrient3 = MapDirection.EAST;

        assertEquals(expectedPos3, resPos3);
        assertEquals(expectedOrient3, resOrient3);
    }
}