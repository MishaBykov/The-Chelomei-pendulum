import java.awt.*;
import java.awt.geom.*;

class Pendulum {
    private Color color;

    private Point2D.Double onePoint;
    private Point2D.Double twoPoint;
    private Rku rku;

    Pendulum(Rku rku, Color color){
        this.rku = rku;
        onePoint = new Point2D.Double();
        twoPoint = new Point2D.Double();
        this.color = color;
        update();
    }

    public void update(){
        double angle = rku.getParameters(1);
        double len = rku.getParameters(7);
        onePoint.setLocation(rku.suspensionX(), rku.suspensionY());
        twoPoint.setLocation(-Math.sin(angle)*len*2 + onePoint.getX(),
                Math.cos(angle)*len*2 + onePoint.getY());
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

}
/*
    private Point2D.Double toPendulum(Point2D.Double one, double len, double angle) {
        return new Point2D.Double(-Math.sin(angle) * setting.getScale() * len + one.getX(),
                -Math.cos(angle) * setting.getScale() * len + one.getY());
*/
