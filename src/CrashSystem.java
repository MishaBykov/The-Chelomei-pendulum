import java.util.List;


public class CrashSystem {
    private Values values;
    private Boolean crashSystem;

    public CrashSystem(Values values) {
        this.values = values;
        crashSystem = values.getParameters().get("l") < values.getVariables("system").get("x");
    }

    public void systemToCrash(List<RK4> rk4List, Pendulum pendulum, Washer washer){
        double t = rk4List.get(0).getT();
        Functions WF = new WasherFunctions(values);
        rk4List.add(new RK4(WF, values, t, Config.getStep()));
        values.getParameters().put("m", 0.0);
    }

    public Boolean isCrash() {
        return crashSystem;
    }

    public void setCrashSystem() {
        this.crashSystem = values.getParameters().get("l") < values.getVariables("system").get("x");
    }
}
