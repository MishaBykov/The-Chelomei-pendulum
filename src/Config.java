import java.awt.geom.Point2D;
import java.util.HashMap;

public class Config {
    private static double widthWasher = 0.1;
    private static double heightWasher = 0.1;
    private static double scale = 100;
    private static int speedDown = 25;
    private static double step = 1.0 / (speedDown * 10);

    private Config(){}

    public static double getStep() {
        return step;
    }

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
}
