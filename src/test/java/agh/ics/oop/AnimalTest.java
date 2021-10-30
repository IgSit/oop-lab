package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    void moveApi() {
        Animal rover1 = new Animal();
        String[] commands1 = new String[] {"r", "f", "forward", "f"};

        rover1.moveApi(commands1);
        Vector2d resPos1 = rover1.getCoordinates();
        MapDirection resOrient1 = rover1.getOrientation();

        Vector2d expectedPos1 = new Vector2d(4, 2);
        MapDirection expectedOrient1 = MapDirection.EAST;

        assertEquals(expectedPos1, resPos1);
        assertEquals(expectedOrient1, resOrient1);

        // -------------------------------------------------

        Animal rover2 = new Animal();
        String[] commands2 = new String[] {"backward", "left", "forward", "f", "f", "l", "f", "f"};

        rover2.moveApi(commands2);
        Vector2d resPos2 = rover2.getCoordinates();
        MapDirection resOrient2 = rover2.getOrientation();

        Vector2d expectedPos2 = new Vector2d(0, 0);
        MapDirection expectedOrient2 = MapDirection.SOUTH;

        assertEquals(expectedPos2, resPos2);
        assertEquals(expectedOrient2, resOrient2);

        // --------------------------------------------------

        Animal rover3 = new Animal();
        String[] commands3 = new String[] {"f", "f", "f", "right", "dean", "b", "beer"};

        rover3.moveApi(commands3);
        Vector2d resPos3 = rover3.getCoordinates();
        MapDirection resOrient3 = rover3.getOrientation();

        Vector2d expectedPos3 = new Vector2d(1, 4);
        MapDirection expectedOrient3 = MapDirection.EAST;

        assertEquals(expectedPos3, resPos3);
        assertEquals(expectedOrient3, resOrient3);
    }
}