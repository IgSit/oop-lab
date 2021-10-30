package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {

    @Test
    void next() {
        MapDirection ex1 = MapDirection.EAST;
        ex1 = ex1.next();
        assertEquals(MapDirection.SOUTH, ex1);

        MapDirection ex2 = MapDirection.SOUTH;
        ex2 = ex2.next();
        assertEquals(MapDirection.WEST, ex2);

        MapDirection ex3 = MapDirection.WEST;
        ex3 = ex3.next();
        assertEquals(MapDirection.NORTH, ex3);

        MapDirection ex4 = MapDirection.NORTH;
        ex4 = ex4.next();
        assertEquals(MapDirection.EAST, ex4);
    }

    @Test
    void previous() {
        MapDirection ex1 = MapDirection.EAST;
        ex1 = ex1.previous();
        assertEquals(MapDirection.NORTH, ex1);

        MapDirection ex2 = MapDirection.SOUTH;
        ex2 = ex2.previous();
        assertEquals(MapDirection.EAST, ex2);

        MapDirection ex3 = MapDirection.WEST;
        ex3 = ex3.previous();
        assertEquals(MapDirection.SOUTH, ex3);

        MapDirection ex4 = MapDirection.NORTH;
        ex4 = ex4.previous();
        assertEquals(MapDirection.WEST, ex4);
    }
}