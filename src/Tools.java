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


}
