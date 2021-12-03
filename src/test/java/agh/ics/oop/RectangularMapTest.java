package agh.ics.oop;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RectangularMapTest {
    final int width = 5;
    final int height = 5;

    @Test
    void canMoveToTest() {
        RectangularMap map = new RectangularMap(width, height);

        assertTrue(map.canMoveTo(new Vector2d(width-1, height-1)));
        assertFalse(map.canMoveTo(new Vector2d(-1, 0)));
        assertFalse(map.canMoveTo(new Vector2d(0, -1)));
        assertFalse(map.canMoveTo(new Vector2d(width, 0)));
        assertFalse(map.canMoveTo(new Vector2d(0, height)));
    }

    @Test
    void placeTest() {
        RectangularMap map = new RectangularMap(width, height);
        Vector2d position = new Vector2d(2, 2);

        assertTrue(map.place(new Animal(map, position)));
        assertThrows(IllegalArgumentException.class, () -> map.place(new Animal(map, position)));
        assertTrue(map.place(new Animal(map, new Vector2d(width - 1, height - 1))));
    }

    @Test
    void isOccupiedTest() {
        RectangularMap map = new RectangularMap(width, height);
        Vector2d position = new Vector2d(2, 2);

        Animal animal = new Animal(map, position);
        map.place(animal);

        assertTrue(map.isOccupied(animal.getPosition()));
        assertFalse(map.isOccupied(new Vector2d(0, 0)));
    }

    @Test
    void objectAtTest() {
        RectangularMap map = new RectangularMap(width, height);
        Vector2d position = new Vector2d(2, 2);
        Animal animal = new Animal(map, position);
        map.place(animal);

        assertEquals(animal, map.objectAt(animal.getPosition()));
        assertNull(map.objectAt(new Vector2d(width - 1, height - 1)));
    }
}
