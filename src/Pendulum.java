import java.awt.*;
import java.awt.geom.*;
import java.util.HashMap;

public class Pendulum {
    private Color color;

    private double length;
    private double angle;
    private Point2D.Double suspensionPoint;
    private Functions functions;
    private HashMap<String,Double> variables;
    private HashMap<String,Double> parameters;

    private Values values;

    public Pendulum(Functions functions, Values values, double t, Color color) {
        this.functions = functions;
        variables = values.getVariables(functions.getNameVariables());
        parameters = values.getParameters();

        this.values = values;

        this.color = color;

        suspensionPoint = new Point2D.Double();
        update(t);
    }

    public Color getColor() {
        return color;
    }

    public Point2D.Double getSuspensionPoint() {
        return suspensionPoint;
    }

    public double getAngle() {
        return angle;
    }

    public double getLength() {
        return length;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setSuspensionPoint(Point2D.Double point) {
        suspensionPoint.setLocation(point);
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setFunctions(Functions functions) {
        this.functions = functions;
        variables = values.getVariables(functions.getNameVariables());
    }

    public void update(double t) {
        setAngle(variables.get("phi"));
        setLength(parameters.get("l"));
        setSuspensionPoint(functions.suspensionPoint(t));
    }
}