import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;


public class CrashSystem {
    private final String nameSystem = "system";
    private final String nameWasher = "washer";

    private Values values;
    private Map<String, Double> variablesSystem;
    private Map<String, Double> variablesWasher;
    private Map<String, Double> parameters;
    private Double x;
    private Double l;
    private Boolean crashSystem;

    public CrashSystem(Values values) {
        this.values = values;
        variablesSystem = values.getVariables(nameSystem);
        variablesWasher = values.getVariables(nameWasher);
        parameters = values.getParameters();
        x = values.getVariables(nameSystem).get("x");
        l = values.getParameters().get("l");
        crashSystem = l < x;
    }

    public void systemToCrash(List<RK4> rk4List) {
        double t = rk4List.get(0).getT();
        double angle = variablesSystem.get("phi");
        Point2D.Double pointWasher = Config.findTwoPoint(
                SuspensionPoint.get(parameters, t),
                x + Config.getHeightWasher() / 2,
                angle
        );
        variablesWasher.put("x", pointWasher.getX());
        variablesWasher.put("y", pointWasher.getY());
        double v = variablesSystem.get("dotPhi") * l;
        variablesWasher.put("dotX", v * Math.cos(angle));
        variablesWasher.put("dotY", v * Math.cos(angle));

        Functions WF = new WasherFunctions(values);
        rk4List.add(new RK4(WF, values, t, Config.getStep()));
        values.getParameters().put("m", 0.0);
    }

    public Boolean isCrash() {
        return crashSystem;
    }

    public void setCrashSystem() {
        this.crashSystem = l < x;
    }
}
