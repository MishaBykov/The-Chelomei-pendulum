import java.awt.*;
import java.awt.geom.*;

public class Pendulum {
    private Color color;

    private double length;
    private double angle;
    private Point2D.Double onePoint;
    private Point2D.Double twoPoint;
    private Rku rku;

    public Pendulum(Rku rku, Color color) {
        this.rku = rku;
        onePoint = new Point2D.Double();
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public Point2D.Double getOnePoint() {
        return onePoint;
    }

    public Point2D.Double getTwoPoint() {
        return twoPoint;
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

    public void setOnePoint(double x, double y) {
        onePoint.setLocation(x ,y);
    }

    public void setTwoPoint(double x, double y) {
        twoPoint.setLocation(x, y);
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setLength(double length) {
        this.length = length;
    }

    /*
    public void update(Observable o, Object arg) {
        double angle = rku.getParameters(1);
        double len = rku.getParameters(7)*2;
        setOnePoint(rku.suspensionX(), rku.suspensionY());
        twoPoint = Setting.findTwoPoint(onePoint, len, angle);
    }
    */
}