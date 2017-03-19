import java.awt.*;
import java.awt.geom.*;

public class Pendulum {
    private Color color;

    private double length;
    private double angle;
    private Point2D.Double suspensionPoint;
    private Functions functions;
    private Parameters parameters;

    public Pendulum(Functions functions, Parameters parameters, double t, Color color) {
        this.functions = functions;
        this.parameters = parameters;
        suspensionPoint = new Point2D.Double();

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

    public void setSuspensionPoint(double x, double y) {
        suspensionPoint.setLocation(x ,y);
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

    public void update(double t) {
        setAngle(parameters.get("phi"));
        setLength(parameters.get("l"));
        setSuspensionPoint(functions.suspensionPoint(t));
    }
}