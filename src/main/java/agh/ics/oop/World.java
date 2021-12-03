package agh.ics.oop;


import java.util.Arrays;

public class World {
    public static void main(String[] args){
        try {
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        }
        catch(IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            System.exit(1);
        }
    }

    public static String[] fixInput(String[] args) {
        int n = args.length;
        String[] fixed = new String[n];
        int i = 0;
        for (String arg : args) {
            if (arg.equals("f") || arg.equals("b") || arg.equals("r") || arg.equals("l"))
                fixed[i++] = arg;
        }
        return Arrays.copyOfRange(fixed, 0, i);
    }

    public static Direction[] enumerate(String[] args){
        Direction[] enumerate = new Direction[args.length];
        for (int i = 0; i < args.length; i++){
            enumerate[i] = switch (args[i]){
                case "f" -> Direction.FORWARD;
                case "b" -> Direction.BACKWARD;
                case "l" -> Direction.LEFT;
                default -> Direction.RIGHT;
            };
        }
        return enumerate;
    }

    public static void run(Direction[] args){
        for (Direction arg : args) {
            switch (arg) {
                case LEFT -> System.out.println("Zwierzak skręca w lewo");
                case RIGHT -> System.out.println("Zwierzak skręca w prawo");
                case FORWARD -> System.out.println("Zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("Zwierzak idzie do tyłu");

            }
        }
    }

    }