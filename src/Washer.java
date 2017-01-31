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
        center = new Point2D.Double();
        update(null, null);
    }

    public Point2D.Double getCenterWasher() {
        return centerWasher;
    }

    public Color getColor() {
        return color;
    }

    public void setX(double x) {
        /*if (x >= 0 && x <= len)
            this.x = x;
        else
            if (x < 0)
                this.x = 0;
            else
                this.x = len;*/

        this.x = x;
    }

    public void setLen(double len) {
        this.len = len*2;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    @Override
    public void update(Observable o, Object arg) {
        setLen(rku.getParameters(7));
        setX(rku.getParameters(0));
        center.setLocation(rku.suspensionX(), rku.suspensionY());

        setAngle(rku.getParameters(1));

        centerWasher = Setting.findTwoPoint(center, x, angle);
    }
}
