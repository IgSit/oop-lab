package agh.ics.oop;

public record Vector2d(int x, int y) {

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    boolean precedes(Vector2d other) {
        return x <= other.x && y <= other.y;
    }

    boolean follows(Vector2d other) {
        return x >= other.x && y >= other.y;
    }

    public Vector2d upperRight(Vector2d other) {
        return new Vector2d(Math.max(x, other.x), Math.max(y, other.y));
    }

    public Vector2d lowerLeft(Vector2d other) {
        return new Vector2d(Math.min(x, other.x), Math.min(y, other.y));
    }

    public Vector2d add(Vector2d other) {
        return new Vector2d(x + other.x, y + other.y);
    }

    public Vector2d subtract(Vector2d other) {
        return new Vector2d(x - other.x, y - other.y);
    }

    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!(other instanceof Vector2d))
            return false;
        return x == ((Vector2d) other).x && y == ((Vector2d) other).y;
    }

    public Vector2d opposite() {
        return new Vector2d(-x, -y);
    }

    public static void main(String[] args) {

    }
}
