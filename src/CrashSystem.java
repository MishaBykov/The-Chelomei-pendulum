import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;


public class CrashSystem {
    private static CrashSystem  instance;

    private Values values;

    private CrashSystem(){
        this.values = Values.getInstance();
    }

    public static CrashSystem getInstance(){
        if (instance == null) {
            instance = new CrashSystem();
        }
        return instance;
    }

    public void systemToCrash(List<RK4> rk4List, Washer washer) {
        double t = rk4List.get(0).getT();
        double angle = values.getVariable(Config.getNameSystem(), "phi");
        Point2D.Double pointWasher = Tools.findTwoPoint(
                Tools.suspensionPoint(values.getParameters(), t),
                values.getVariable(Config.getNameSystem(), "x") + Config.getHeightWasher() / 2,
                angle
        );
        values.setVariable(Config.getNameWasher(), "x", pointWasher.getX());
        values.setVariable(Config.getNameWasher(), "y", pointWasher.getY());
        double v = values.getVariable(Config.getNameSystem(), "dotPhi") * values.getParameters().get("l");
        values.setVariable(Config.getNameWasher(), "dotX", v * Math.cos(angle));
        values.setVariable(Config.getNameWasher(), "dotY", v * Math.cos(angle));

        Functions WF = new WasherFunctions(values);
        washer.setFunctions(WF);
        rk4List.add(new RK4(WF, values, t, Config.getStep()));
        values.getParameters().put("m", 0.0);
    }

    public Boolean isCrash() {
        return values.getParameters().get("l") < values.getVariable(Config.getNameSystem(), "x");
    }
}
