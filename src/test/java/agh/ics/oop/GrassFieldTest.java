package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {

    @Test
    void canMoveTo() {
        GrassField map = new GrassField(10);
        map.clearGrass();
        int[][] coordinates = {{1, 2}, {3, 3}, {2, 4}};
        for (int i = 0; i < 3; i++) {
            Vector2d position = new Vector2d(coordinates[i][0], coordinates[i][1]);
            map.addGrass(new Grass(position));
        }
        Animal a1 = new Animal(map, new Vector2d(2, 2));
        Animal a2 = new Animal(map, new Vector2d(3, 2));

        map.place(a1);
        map.place(a2);

        assertTrue(map.canMoveTo(new Vector2d(3, 3)));
        assertTrue(map.canMoveTo(new Vector2d(1, 1)));
        assertFalse(map.canMoveTo(new Vector2d(2, 2)));
    }

    @Test
    void place() {
        GrassField map = new GrassField(10);
        map.clearGrass();
        int[][] coordinates = {{1, 2}, {3, 3}, {2, 4}};
        for (int i = 0; i < 3; i++) {
            Vector2d position = new Vector2d(coordinates[i][0], coordinates[i][1]);
            map.addGrass(new Grass(position));
        }
        Animal a1 = new Animal(map, new Vector2d(1, 2));
        Animal a2 = new Animal(map, new Vector2d(3, 2));
        Animal a3 = new Animal(map, new Vector2d(3, 2));

        assertTrue(map.place(a1));
        assertTrue(map.place(a2));
        assertThrows(IllegalArgumentException.class, () -> map.place(a3));
    }

    @Test
    void isOccupied() {
        GrassField map = new GrassField(10);
        map.clearGrass();
        int[][] coordinates = {{1, 2}, {3, 3}, {2, 4}};
        for (int i = 0; i < 3; i++) {
            Vector2d position = new Vector2d(coordinates[i][0], coordinates[i][1]);
            map.addGrass(new Grass(position));
        }
        Animal a1 = new Animal(map, new Vector2d(2, 2));
        Animal a2 = new Animal(map, new Vector2d(3, 2));

        map.place(a1);
        map.place(a2);

        assertTrue(map.isOccupied(new Vector2d(2, 2)));
        assertTrue(map.isOccupied(new Vector2d(2, 4)));
        assertFalse(map.isOccupied(new Vector2d(3, 4)));
    }

    @Test
    void objectAt() {
        GrassField map = new GrassField(10);
        map.clearGrass();
        int[][] coordinates = {{1, 2}, {3, 3}, {2, 4}};
        for (int i = 0; i < 3; i++) {
            Vector2d position = new Vector2d(coordinates[i][0], coordinates[i][1]);
            map.addGrass(new Grass(position));
        }
        Animal a1 = new Animal(map, new Vector2d(2, 2));
        Animal a2 = new Animal(map, new Vector2d(3, 3));

        map.place(a1);
        map.place(a2);

        assertEquals(map.objectAt(new Vector2d(3, 3)), a2);
        assertEquals(map.objectAt(new Vector2d(2, 2)), a1);
        assertTrue(map.objectAt(new Vector2d(2, 4)) instanceof Grass);
        assertNull(map.objectAt(new Vector2d(4, 4)));
    }
}