import java.util.List;


public class CrashSystem {
    private boolean crash;
    private Values values;

    public CrashSystem(Values values) {
        this.values = values;
    }

    private void systemToCrash(List<RK4> rk4List, Pendulum pendulum, Washer washer){
        values.getVariables("pendulum").put("phi", values.getVariables("system").get("phi"));
        values.getVariables("pendulum").put("dotPhi", values.getVariables("system").get("dotPhi"));
        double t = rk4List.get(0).getT();
        Functions PF = new PendulumFunctions(values);
        Functions WF = new WasherFunctions(values);
        rk4List.clear();
        rk4List.add(new RK4(PF, values, t, Setting.getStep()));
        rk4List.add(new RK4(WF, values, t, Setting.getStep()));
        pendulum.setFunctions(PF);
        washer.setFunctions(WF);
    }

    public boolean isCrash() {
        return crash;
    }

    public void setCrash() {
        crash = values.getParameters().get("l") < values.getVariables("system").get("x");
    }
}
