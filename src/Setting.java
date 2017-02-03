import java.awt.geom.Point2D;

class Setting {
    private static double widthWasher = 10;
    private static double heightWasher = 10;
    private static double scale = 100;
    private static int speedDown = 10;

    Setting(){}

    public static double getScale() {
        return scale;
    }

    public static int getSpeedDown() {
        return speedDown;
    }

    public static double getHeightWasher() {
        return heightWasher;
    }

    public static double getWidthWasher() {
        return widthWasher;
    }

    public static Point2D.Double findTwoPoint(Point2D.Double one, double len, double angle) {
        return new Point2D.Double(-Math.sin(angle) * len + one.getX(),
                Math.cos(angle) * len + one.getY());
    }
}
