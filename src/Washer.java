import java.awt.*;
import java.awt.geom.*;
import java.util.Observable;
import java.util.Observer;

class Washer implements Observer{
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
        center = new Point2D.Double(rku.suspensionX(), rku.suspensionY());
        update(null, null);
    }

    public Point2D.Double getCenterWasher() {
        return centerWasher;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public void update(Observable o, Object arg) {
        x = rku.getParameters(0);
        len = rku.getParameters(7) * 2;
        center.setLocation(rku.suspensionX(), rku.suspensionY());

        if (x <= len)
            angle = rku.getParameters(1);

        centerWasher = Setting.findTwoPoint(center, x, angle);
    }
}
