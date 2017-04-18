import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

public class Tools {
    public static Point2D.Double suspensionPoint(Map<String, Double> parameters, double  t) {
        return new Point2D.Double(
                parameters.get("alpha") * Math.sin(parameters.get("theta") * t) * Math.sin(parameters.get("nu")),
                parameters.get("alpha") * Math.sin(parameters.get("theta") * t) * Math.cos(parameters.get("nu"))
        );
    }

    public static Point2D.Double findTwoPoint(Point2D.Double onePoint, double length, double angle) {
        return new Point2D.Double(-Math.sin(angle) * length + onePoint.getX(),
                Math.cos(angle) * length + onePoint.getY());
    }

    public static Map<String, Double> getScaleSlider(){
        Map<String, Double> result = new HashMap<String, Double>();
        result.put("x", 1.0);
        result.put("phi", 1.0);
        result.put("dotX", 1.0);
        result.put("dotPhi", 1.0);
        result.put("I0", 1.0);
        result.put("I1", 1.0);
        result.put("m", 1.0);
        result.put("l", 1.0);
        result.put("k1", 1.0);
        result.put("k2", 1.0);
        result.put("M", 1.0);
        result.put("g", 1.0);
        result.put("alpha", 1.0);
        result.put("nu", 1.0);
        result.put("theta", 1.0);
        return result;
    }
}
