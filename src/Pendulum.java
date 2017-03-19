import java.awt.*;
import java.awt.geom.*;

public class Pendulum {
    private Color color;

    private double length;
    private double angle;
    private Point2D.Double suspensionPoint;
    private Functions functions;
    private Values values;

    public Pendulum(Functions functions, Values values, double t, Color color) {
        this.functions = functions;
        this.values = values;
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
        setAngle(values.getParameter("phi"));
        setLength(values.getParameter("l"));
        setSuspensionPoint(functions.suspensionPoint(t));
    }
}