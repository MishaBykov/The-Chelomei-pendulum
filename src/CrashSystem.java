import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;


public class CrashSystem {
    String nameSystem = "system";
    String nameWasher = "washer";

    private Values values;
    private Map<String, Double> parameters;
    private Double x;
    private Double l;
    private Boolean crashSystem;

    public CrashSystem(Values values) {
        this.values = values;
        parameters = values.getParameters();
        x = values.getVariables(nameSystem).get("x");
        l = values.getParameters().get("l");
        crashSystem = l < x;
    }

    public void systemToCrash(List<RK4> rk4List){
        double t = rk4List.get(0).getT();
        Point2D.Double pointWasher = Config.findTwoPoint(
                SuspensionPoint.get(parameters, t),
                x + Config.getHeightWasher() / 2,
                values.getVariables(nameSystem).get("phi")
        );
        values.getVariables(nameWasher).put("x", pointWasher.getX());
        values.getVariables(nameWasher).put("y", pointWasher.getY());
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
