package agh.ics.oop;

public class Grass extends MapObject{

    public Grass(Vector2d position) {
        super(position);
    }

    public boolean canShareSpace() {
        return true;
    }

    @Override
    public String toString() {
        return "*";
    }
}
