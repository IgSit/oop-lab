package agh.ics.oop;

public class OptionsParser {

    public MoveDirection[] parse(String[] movements) {
        int n = movements.length;
        String[] fixed = new String[n];
        int i = 0;
        for (String arg : movements) {
            if (arg.equals("f") || arg.equals("forward") || arg.equals("b") || arg.equals("backward") ||
                arg.equals("r") || arg.equals("right") || arg.equals("l") || arg.equals("left"))
                fixed[i++] = arg;
        }

        MoveDirection[] result = new MoveDirection[i];
        for (int k = 0; k < i; k++){
            result[k] = switch (fixed[k]) {
                case "f", "forward" -> MoveDirection.FORWARD;
                case "b", "backward" -> MoveDirection.BACKWARD;
                case "r", "right" -> MoveDirection.RIGHT;
                default -> MoveDirection.LEFT;
            }
            ;
            }
        return result;
        }

}

