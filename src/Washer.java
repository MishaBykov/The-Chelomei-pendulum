import java.awt.*;
import java.awt.geom.*;

public class Washer {
    private Point2D.Double centerWasher;

    private Color color;

    private Values values;
    private Functions functions;

    public Washer(Functions functions, Values values, double t, Color color) {
        this.functions = functions;
        this.values = values;
        this.color = color;

        update(t);
    }

    public Point2D.Double getCenterWasher() {
        return centerWasher;
    }

    public Color getColor() {
        return color;
    }

    public void setCenterWasher(Point2D.Double centerWasher) {
        this.centerWasher = centerWasher;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void update(double t) {
        centerWasher = Setting.findTwoPoint(
                functions.suspensionPoint(t),
                values.getParameter("x") + Setting.getHeightWasher()/2,
                values.getParameter("phi")
        );
    }
}
