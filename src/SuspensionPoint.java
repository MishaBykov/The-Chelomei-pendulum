import java.awt.geom.Point2D;
import java.util.Map;

public class SuspensionPoint {
    private Map<String, Double> parameters;

    public static Point2D.Double get(Map<String, Double> parameters, double  t) {
        return new Point2D.Double(
                parameters.get("alpha") * Math.sin(parameters.get("theta") * t) * Math.sin(parameters.get("nu")),
                parameters.get("alpha") * Math.sin(parameters.get("theta") * t) * Math.cos(parameters.get("nu"))
        );
    }
}
