import java.awt.*;
import java.awt.geom.*;
import java.util.HashMap;

public class Washer {
    private Point2D.Double centerWasher;

    private Color color;

    private Functions functions;
    private Values values;
    private HashMap<String, Double> variables;
    private boolean fSystem;

    public Washer(Functions functions, Values values, double t, Color color) {
        fSystem = functions.getNameVariables().equals("system");
        this.functions = functions;
        variables = values.getVariables(functions.getNameVariables());
        this.color = color;

        this.values = values;

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
        fSystem = functions.getNameVariables().equals("system");
        this.functions = functions;
        variables = values.getVariables(functions.getNameVariables());
    }

    public void update(double t) {
        if (fSystem) {
            centerWasher = Setting.findTwoPoint(
                    functions.suspensionPoint(t),
                    variables.get("x") + Setting.getHeightWasher() / 2,
                    variables.get("phi")
            );
        }
        else {
            setCenterWasher(variables.get("x"), variables.get("y"));
        }
    }
}
