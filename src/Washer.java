import java.awt.*;
import java.awt.geom.*;

public class Washer {
    private Color color;

    private Parameters parameters;
    private Functions functions;
    private Rku rku;
    private double angle;
    private double x;
    private Point2D.Double center;
    private Point2D.Double centerWasher;

    public Washer(Functions functions,Parameters parameters, Rku rku,Color color) {
        this.functions = functions;
        this.parameters = parameters;
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
        setX(parameters.get("x"));
        center.setLocation(functions.suspensionX(rku.getT()), functions.suspensionY(rku.getT()));

        setAngle(parameters.get("phi"));

        centerWasher = Setting.findTwoPoint(center, x + Setting.getHeightWasher()/2, angle);
    }
}
