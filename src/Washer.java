import java.awt.*;
import java.awt.geom.*;

public class Washer {
    private Color color;

    private Functions functions;
    private Rku rku;
    private double angle;
    private double x;
    private Point2D.Double center;
    private Point2D.Double centerWasher;

    public Washer(Functions functions, Rku rku,Color color) {
        this.functions = functions;
        this.rku = rku;
        this.color = color;
        center = new Point2D.Double();

        update();
    }

    public Point2D.Double getCenterWasher() {
        return centerWasher;
    }

    public Color getColor() {
        return color;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void update() {
        setX(functions.getParameter("x"));
        center.setLocation(functions.suspensionX(rku.getT()), functions.suspensionY(rku.getT()));

        setAngle(functions.getParameter("phi"));

        centerWasher = Setting.findTwoPoint(center, x, angle);
    }
}
