import java.awt.*;
import java.awt.geom.*;

public class Washer {
    private Color color;

    private Rku rku;
    private double angle;
    private double x;
    private double len;
    private Point2D.Double center;
    private Point2D.Double centerWasher;

    Washer(Rku rku, Color color) {
        this.rku = rku;
        this.color = color;
        center = new Point2D.Double();
        update();
    }

    public void update() {
        x = rku.getParameters(0);
        len = rku.getParameters(7) * 2;
        center.setLocation(rku.suspensionX(), rku.suspensionY());

        if (x <= len)
            angle = rku.getParameters(1);

        centerWasher = Setting.findTwoPoint(center, x, angle);

//                .setLocation(-Math.sin(angle) * (x) + center.getX(),
//                Math.cos(angle) * (x) + center.getY());
    }

    public Point2D.Double getCenterWasher() {
        return centerWasher;
    }

    public Color getColor() {
        return color;
    }
}
