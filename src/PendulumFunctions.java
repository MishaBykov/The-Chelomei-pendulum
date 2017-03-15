import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * parameters[0] = x; <br>
 * parameters[1] = phi; <br>
 * parameters[2] = g; <br>
 * parameters[3] = m; <br>
 * parameters[4] = l; <br>
 * parameters[5] = c; <br>
 * parameters[6] = alpha; <br>
 * parameters[7] = nu; <br>
 * parameters[8] = theta; <br>
 * <br>
 * private double func1(double t, double x, double y) {<br>
 * return y;<br>
 * }<br>
 * <br>
 * private double func2(double t, double x, double y) {<br>
 * return -(2 * parameters[5] / parameters[3] * y + (parameters[2] / parameters[4] + (-parameters[6] * Math.pow(parameters[7], 2) * Math.cos(parameters[8]) * Math.sin(parameters[7] * t)) / parameters[4]) * Math.sin(x) +<br>
 * (-parameters[6] * Math.pow(parameters[7], 2) * Math.sin(parameters[8]) * Math.sin(parameters[7] * t)) / parameters[4] * Math.cos(x));<br>
 * }<br>
 * <br>
 * public double suspensionX() {<br>
 * return parameters[6] * Math.sin(parameters[7] * t) * Math.sin(parameters[8]);<br>
 * }<br>
 * <br>
 * public double suspensionY() {<br>
 * return parameters[6] * Math.sin(parameters[7] * t) * Math.cos(parameters[8]);<br>
 * }<br>
 */
public class PendulumFunctions implements Functions {
    private Parameters parameters;
    private String[] variables;

    public PendulumFunctions(Parameters parameters) {
// default:
        variables = new String[]{"x", "phi"};
// --------
        this.parameters = parameters;
    }

    public double suspensionX(double t) {
        return parameters.get("alpha") * Math.sin(parameters.get("theta") * t) * Math.sin(parameters.get("nu"));
    }

    public double suspensionY(double t){
        return  parameters.get("alpha") * Math.sin( parameters.get("theta") * t) * Math.cos( parameters.get("nu"));
    }

    @Override
    public String[] getNameVariables() {
        return variables;
    }

    /**
     * phi = args[0]
     * a = args[1]
     */
    @Override
    public double getResultFunction(double t, int id, double ... args) {
        switch (id) {
            case 0:
                return args[1];
            case 1:
                return -2*parameters.get("c")/parameters.get("m")*parameters.get("a")
                        -(parameters.get("g")/parameters.get("l") + f(2, t)/parameters.get("l"))*Math.sin(args[0])
                        -f(1, t)/parameters.get("l")*Math.cos(args[0]);
            default:
                return -1;
        }
    }
    private double f(int id, double t) {
        switch (id) {
            case 1:
                return -Math.pow( parameters.get("nu"), 2) *  parameters.get("alpha") * Math.sin( parameters.get("theta"))
                        * Math.sin( parameters.get("nu") * t);
            case 2:
                return -Math.pow( parameters.get("nu"), 2) *  parameters.get("alpha") * Math.sin( parameters.get("theta"))
                        * Math.cos( parameters.get("nu") * t);
            default:
                return -1;
        }
    }

    @Override
    public int getCountFunctions() {
        return 2;
    }
}

