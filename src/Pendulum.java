import java.awt.*;
import java.awt.geom.*;
import java.util.Observable;
import java.util.Observer;

import Estimation.*;

public class Pendulum implements Observer{
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

        update(null, null);
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

    public void setColor(Color color) {
        this.color = color;
    }

    public void setOnePoint(double x, double y) {
        onePoint.setLocation(x ,y);
    }

    public void setTwoPoint(double x, double y) {
        twoPoint.setLocation(x, y);
    }

    @Override
    public void update(Observable o, Object arg) {
        double angle = rku.getParameters(1);
        double len = rku.getParameters(7);
        setOnePoint(rku.suspensionX(), rku.suspensionY());
        twoPoint = Setting.findTwoPoint(onePoint, len*2, angle);
    }
}