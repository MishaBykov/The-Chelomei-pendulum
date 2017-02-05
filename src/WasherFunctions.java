import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 */
public class WasherFunctions implements Functions{
    private Map<String, Double> parameters;
    private String[] variables;
    public WasherFunctions() {
// default:
        variables = new String[]{};
        String[] parametersName = new String[]{};
        double[] parametersValue = new double[]{};
// --------
        parameters = new HashMap<String, Double>();
        for (int i = 0; i < parametersName.length; i++) {
            parameters.put(parametersName[i], parametersValue[i]);
        }
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
     *
     */
    @Override
    public double getResultFunction(double t, int id, double ... args) {
        return 0;
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

