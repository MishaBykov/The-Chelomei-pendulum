import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;


public class CrashSystem {
    private static CrashSystem  instance;

    private final String nameSystem = "system";
    private final String nameWasher = "washer";

    private Values values;
    private Double x;
    private Double l;
    private Boolean crashSystem;

    private CrashSystem(Values values){
        this.values = values;
        x = values.getVariables(nameSystem).get("x");
        l = values.getParameters().get("l");
        crashSystem = l < x;
    }

    public static CrashSystem getInstance(Values values){
        if (instance == null) {
            instance = new CrashSystem(values);
        }
        return instance;
    }

    public static CrashSystem getInstance(){
        if (instance == null) {
            throw new NullPointerException("Ошибка(CrashSystem)");
        }
        return instance;
    }

    public void systemToCrash(List<RK4> rk4List, Washer washer) {
        double t = rk4List.get(0).getT();
        double angle = values.getVariables(nameSystem).get("phi");
        Point2D.Double pointWasher = Tools.findTwoPoint(
                Tools.suspensionPoint(values.getParameters(), t),
                x + Config.getHeightWasher() / 2,
                angle
        );
        values.getVariables(nameWasher).put("x", pointWasher.getX());
        values.getVariables(nameWasher).put("y", pointWasher.getY());
        double v = values.getVariables(nameSystem).get("dotPhi") * l;
        values.getVariables(nameWasher).put("dotX", v * Math.cos(angle));
        values.getVariables(nameWasher).put("dotY", v * Math.cos(angle));

        Functions WF = new WasherFunctions(values);
        washer.setFunctions(values, WF);
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
