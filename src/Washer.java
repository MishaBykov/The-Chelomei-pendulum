import java.awt.*;
import java.awt.geom.*;
import java.util.Map;

public class Washer {
    private Point2D.Double centerWasher;
    private Color color;

    private Values values;
    private Map<String, Double> parameters;
    String nameVariables;
    private CrashSystem crashSystem;

    public Washer(CrashSystem crashSystem, Functions functions, Values values, double t, Color color) {
        this.crashSystem = crashSystem;
        this.values = values;
        this.parameters = values.getParameters();
        nameVariables = functions.getNameVariables();
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

    public void setCenterWasher(double x, double y) {
        centerWasher.setLocation(x ,y);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setFunctions(Functions functions) {
        nameVariables = functions.getNameVariables();
    }


    public void update(double t) {
        if (crashSystem.isCrash()) {
            setCenterWasher(values.getVariable(nameVariables, "x"), values.getVariable(nameVariables, "y"));
        }
        else {
            centerWasher = Tools.findTwoPoint(
                    Tools.suspensionPoint(parameters, t),
                    values.getVariable(nameVariables, "x") + Config.getHeightWasher() / 2,
                    values.getVariable(nameVariables, "phi")
            );
        }
    }
}
