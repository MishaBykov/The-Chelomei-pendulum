import java.awt.*;
import java.awt.geom.*;
import java.util.Map;

public class Washer {
    private Point2D.Double centerWasher;

    private Color color;

    private Values values;
    private Map<String, Double> variables;
    private Boolean crashSystem;
    private SuspensionPoint updateSuspensionPoint;

    public Washer(CrashSystem crashSystem, String nameVariables, SuspensionPoint suspensionPoint, Values values, double t, Color color) {
        this.crashSystem = crashSystem.isCrash();
        variables = values.getVariables(nameVariables);
        this.color = color;
        updateSuspensionPoint = suspensionPoint;
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

    public void setCenterWasher(double x, double y) {
        centerWasher.setLocation(x ,y);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void update(double t) {
        if (crashSystem) {
            centerWasher = Config.findTwoPoint(
                    updateSuspensionPoint.get(t),
                    variables.get("x") + Config.getHeightWasher() / 2,
                    variables.get("phi")
            );
        }
        else {
            setCenterWasher(variables.get("x"), variables.get("y"));
        }
    }
}
