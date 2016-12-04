import java.awt.geom.Point2D;

public class Setting {
    private double widthWasher = 10;
    private double heightWasher = 10;
    private double scale;
    private int speed;

    Setting(double scale, int speed){
        this.scale = scale;
        this.speed = speed;
    }

    public double getScale() {
        return scale;
    }

    public int getSpeed() {
        return speed;
    }

    public double getHeightWasher() {
        return heightWasher;
    }

    public double getWidthWasher() {
        return widthWasher;
    }

    public static Point2D.Double findTwoPoint(Point2D.Double one, double len, double angle) {
        return new Point2D.Double(-Math.sin(angle) * len + one.getX(),
                Math.cos(angle) * len + one.getY());
    }
}
