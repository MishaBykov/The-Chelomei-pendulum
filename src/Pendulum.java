import java.awt.*;
import java.awt.geom.*;
import java.util.Observable;
import java.util.Observer;

class Pendulum implements Observer{
    private Color color;

    private Point2D.Double onePoint;
    private Point2D.Double twoPoint;
    private Rku rku;

    Pendulum(Rku rku, Color color) {
        this.rku = rku;
        onePoint = new Point2D.Double();
        twoPoint = Setting.findTwoPoint(onePoint, rku.getParameters(7)*2, rku.getParameters(1));
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

    @Override
    public void update(Observable o, Object arg) {
        double angle = rku.getParameters(1);
        double len = rku.getParameters(7);
        onePoint.setLocation(rku.suspensionX(), rku.suspensionY());
        twoPoint = Setting.findTwoPoint(onePoint, len*2, angle);
    }
}