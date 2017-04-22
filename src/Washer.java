import java.awt.*;
import java.awt.geom.*;
import java.util.Map;

public class Washer {
    private Point2D.Double centerWasher;
    private Color color;

    private Values values;
    private Map<String, Double> parameters;
    private String nameVariables;
    private UpdateFunction updateFunction;
    private UpdateFromWasher updateFromWasher = new UpdateFromWasher();
    private UpdateFromSystem updateFromSystem = new UpdateFromSystem();

    public Washer(Values values, double t, Color color) {
        nameVariables = Config.getNameSystem();
        this.values = values;
        this.parameters = values.getParameters();
        this.color = color;
        toggleUpdate(CrashSystem.getInstance().isCrash());
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

    public void toggleUpdate(boolean crashSystem) {
        if (crashSystem) {
            nameVariables = Config.getNameWasher();
            updateFunction = updateFromWasher;
        } else {
            nameVariables = Config.getNameSystem();
            updateFunction = updateFromSystem;
        }
    }

    public void update(double t) {
        updateFunction.update(t);
    }
    
    private interface UpdateFunction{
        void update(double t);
    } 
    
    private class UpdateFromSystem implements UpdateFunction {

        @Override
        public void update(double t) {
            setCenterWasher(
                    Tools.findTwoPoint(
                            Tools.suspensionPoint(parameters, t),
                            values.getVariable(nameVariables, "x") + Config.getHeightWasher() / 2,
                            values.getVariable(nameVariables, "phi")
                    )
            );
        }
    }

    private class UpdateFromWasher implements UpdateFunction{

        @Override
        public void update(double t) {
            setCenterWasher(values.getVariable(nameVariables, "x"), values.getVariable(nameVariables, "y"));
        }
    }
}
