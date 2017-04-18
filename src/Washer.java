import java.awt.*;
import java.awt.geom.*;
import java.util.Map;

public class Washer {
    private Point2D.Double centerWasher;

    private Color color;

    private Map<String, Double> variables;
    private Map<String, Double> parameters;
    private Boolean crashSystem;

    public Washer(CrashSystem crashSystem, Functions functions, Values values, double t, Color color) {
        this.crashSystem = crashSystem.isCrash();
        variables = values.getVariables(functions.getNameVariables());
        this.parameters = values.getParameters();
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

    public void setFunctions(Values values, Functions functions) {
        variables = values.getVariables(functions.getNameVariables());
    }


    public void update(double t) {
        if (crashSystem) {
            centerWasher = Tools.findTwoPoint(
                    Tools.suspensionPoint(parameters, t),
                    variables.get("x") + Config.getHeightWasher() / 2,
                    variables.get("phi")
            );
        }
        else {
            setCenterWasher(variables.get("x"), variables.get("y"));
        }
    }
}
