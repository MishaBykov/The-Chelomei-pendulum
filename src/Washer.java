import java.awt.*;
import java.awt.geom.*;
import java.util.Map;

public class Washer {
    private Point2D.Double centerWasher;
    private Color color;

    private Values values;
    private Map<String, Double> parameters;
    private String nameVariables;
    private CrashSystem crashSystem;
    private UpdateFunction updateFunction;

    public Washer(CrashSystem crashSystem, Functions functions, Values values, double t, Color color) {
        this.crashSystem = crashSystem;
        this.values = values;
        this.parameters = values.getParameters();
        nameVariables = functions.getNameVariables();
        this.color = color;
        toggleUpdate(crashSystem.isCrash());
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

    public void toggleUpdate(boolean crashSystem) {
        if (crashSystem){
            updateFunction = new UpdateFromWasher();
        }
        else {
            updateFunction = new UpdateFromSystem();
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
