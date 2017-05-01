import java.util.HashMap;
import java.util.Map;

public class Config {
    private static String nameSystem = "s";
    private static String nameWasher= "w";

    private static String systemOrder = "so";
    private static String washerOrder = "wo";

    private static double widthWasher = 0.1;
    private static double heightWasher = 0.1;
    private static double scale = 100;
    private static int speedDown = 10;
    private static double step = 1.0 / (speedDown * 10);

    private static Map<String, Double> scaleSlider;
    private static Map<String, String> nameLabels;

    static {
        scaleSlider = new HashMap<String, Double>();
        scaleSlider.put("x",        1.0);
        scaleSlider.put("phi",      1.0);
        scaleSlider.put("dotX",     1.0);
        scaleSlider.put("dotPhi",   1.0);
        scaleSlider.put("I0",       1.0);
        scaleSlider.put("I1",       1.0);
        scaleSlider.put("m",        1.0);
        scaleSlider.put("l",        1.0);
        scaleSlider.put("k1",       1.0);
        scaleSlider.put("k2",       1.0);
        scaleSlider.put("M",        1.0);
        scaleSlider.put("g",        1.0);
        scaleSlider.put("alpha",    1.0);
        scaleSlider.put("nu",       1.0);
        scaleSlider.put("theta",    1.0);

        nameLabels = new HashMap<String, String>();
        nameLabels.put("x",        "Координата шайбы");
        nameLabels.put("phi",      "Угол");
        nameLabels.put("dotX",     "Ускорение шайбы");
        nameLabels.put("dotPhi",   "Угловое ускорение");

        nameLabels.put("I0",       "Момент ин-ции ст-ня без шайбы");
        nameLabels.put("I1",       "Собственный момент инерции шайбы");
        nameLabels.put("m",        "Масса шайбы");
        nameLabels.put("l",        "расстояние от центра массы стержня до точки подвеса");
        nameLabels.put("k1",       "Коэфиециент трения всей системы");
        nameLabels.put("k2",       "Коэфиециент трения шайбы");
        nameLabels.put("M",        "Масса стержня");
        nameLabels.put("g",        "ускорение свободного падения");
        nameLabels.put("alpha",    "Амплетуда колебaний");
        nameLabels.put("nu",       "Частота колебaний");
        nameLabels.put("theta",    "Угол колебaний");
    }

    private Config(){}

    public static Map<String, Double> getScaleSlider(){
        return scaleSlider;
    }

    public static Map<String, String> getNameLabels(){
        return nameLabels;
    }

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

    public static String getNameSystem() {
        return nameSystem;
    }

    public static String getNameWasher() {
        return nameWasher;
    }

    public static String getOrderSystem() {
        return systemOrder;
    }

    public static String getOrderWasher() {
        return washerOrder;
    }


}

/*
long i = 0;
for (Map.Entry<Integer, Integer> pair : map.entrySet()) {
    i += pair.getKey() + pair.getValue();
}
*/