import java.awt.geom.Point2D;
import java.util.Map;

public class GetSuspensionPoint {
    private Map<String, Double> parameters;

    public GetSuspensionPoint(Map<String, Double> parameters) {
        this.parameters = parameters;
    }

    public Point2D.Double get(double t) {
        return new Point2D.Double(
                parameters.get("alpha") * Math.sin(parameters.get("theta") * t) * Math.sin(parameters.get("nu")),
                parameters.get("alpha") * Math.sin(parameters.get("theta") * t) * Math.cos(parameters.get("nu"))
        );
    }
}
