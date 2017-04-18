import java.awt.*;
import java.awt.geom.*;
import java.util.Map;

public class Pendulum {
    private Functions functions;
    private Values values;
    private Color color;

    private double length;
    private double angle;
    private Point2D.Double suspensionPoint;

    public Pendulum(Functions functions, Values values, double t, Color color) {
        this.functions = functions;
        this.values = values;
        this.color = color;

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
        suspensionPoint = point;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void update(double t) {
        setAngle(values.getVariable(functions.getNameVariables(), "phi"));
        setLength(values.getParameters().get("l"));
        setSuspensionPoint(Tools.suspensionPoint(values.getParameters(), t));
    }
}