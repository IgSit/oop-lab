package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void parse() {
        OptionsParser parser = new OptionsParser();

        String[] args2 = {"backward", "left", "l", "prawo"};

        assertThrows(IllegalArgumentException.class, () -> parser.parse(args2));
    }
}