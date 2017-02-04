import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SystemFunctions implements Functions{
    private Map<String, Double> parameters;
    private String[] variables;


    /**
     * parameters[0] = x      <br>
     * parameters[1] = phi    <br>
     * parameters[2] = a      <br>
     * parameters[3] = b      <br>
     * parameters[4] = I0     <br>
     * parameters[5] = I1     <br>
     * parameters[6] = m      <br>
     * parameters[7] = L      <br>
     * parameters[8] = k1     <br>
     * parameters[9] = k2     <br>
     * parameters[10] = M     <br>
     * parameters[11] = g     <br>
     * parameters[12] = alpha <br>
     * parameters[13] = theta <br>
     * parameters[14] = nu    <br>
     */
    public SystemFunctions(String[] variables,
            String[] parametersName, double[] parametersValue) {
        parameters = new HashMap<String, Double>();
        for (int i = 0; i < parametersName.length; i++) {
            parameters.put(parametersName[i], parametersValue[i]);
        }

        this.variables = variables;
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

    @Override
    public int getCountParameters() {
        return parameters.size();
    }

    @Override
    public Set<String> getKeyParameters() {
        return parameters.keySet();
    }

    @Override
    public double getParameter(String nameParameter) {
        return parameters.get(nameParameter);
    }

    @Override
    public void setParameter(String nameParameter, double newParameter) {
        parameters.put(nameParameter, newParameter);
    }

    /**
     * x   = args[0]
     * phi = args[1]
     * a   = args[2]
     * b   = args[3]
     */
    @Override
    public double getResultFunction(double t, int id, double ... args) {
        switch (id) {
            case 0:
                return args[2];
            case 1:
                return args[3];
            case 2:
                return args[0] * Math.pow(args[3], 2)
                        - parameters.get("k2") * args[2]
                        - (parameters.get("g") + f(1, t)) * Math.cos(args[1])
                        - f(2, t) * Math.sin(args[1]);
            case 3:
                return (-2 * parameters.get("m") * args[0] * args[2] * args[3]
                        - parameters.get("k1") * args[3]
                        + (parameters.get("M") * parameters.get("L") + parameters.get("m") * args[0])
                            * (parameters.get("g") + f(1, t)) * Math.sin(args[1])
                        - (parameters.get("M") * parameters.get("L") + parameters.get("m") * args[0])
                            * f(2, t) * Math.cos(args[1])
                )
                        / (parameters.get("I0")
                        + parameters.get("I1")
                        + parameters.get("m") * Math.pow(args[0], 2)
                );
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
        return 4;
    }
}
