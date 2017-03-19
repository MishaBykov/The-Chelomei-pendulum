import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Values {
    private HashMap<String, Double> parameters;
    private HashMap<String, HashMap<String, Double>> variables;


    public Values() {

        String[] parametersName = new String[]{"I0", "I1", "m", "l", "k1", "k2", "M", "g", "alpha", "nu", "theta"};
        double[] parametersValue = new double[]{  1,    1,   1,   1,    1,    1,   1, 9.8,       0,    0,       0};

        String[] pendulumName = new String[]{"x", "phi", "dotX", "dotPhi"};
        double[] pendulumValue = new double[]{  1,    1,      1,        1};

        String[] washerName = new String[]{"x", "y"};
        double[] washerValue = new double[]{ 0,   0};

        parameters = new HashMap<String, Double>();
        for (int i = 0; i < parametersName.length; i++) {
            parameters.put(parametersName[i], parametersValue[i]);
        }

        variables = new HashMap<String, HashMap<String, Double> > ();
        variables.put("pendulum", new HashMap<String, Double>());
        variables.put("pendulumOrder", new HashMap<String, Double>());
        for (int i = 0; i < pendulumName.length; i++) {
            variables.get("pendulum").put(pendulumName[i], pendulumValue[i]);
            variables.get("pendulumOrder").put(pendulumName[i],(double) i);
        }
        variables.put("washer", new HashMap<String, Double>());
        variables.put("washerOrder", new HashMap<String, Double>());
        for (int i = 0; i < washerName.length; i++) {
            variables.get("washer").put(washerName[i], washerValue[i]);
            variables.get("washerOrder").put(washerName[i],(double) i);
        }
    }

    public int getCountParameters() {
        return parameters.size();
    }

    public Set<String> getKeyParameters() {
        return parameters.keySet();
    }

    public void setParameter(String nameParameter, double newValue) {
        parameters.put(nameParameter, newValue);
    }

    public double getParameter(String name) {
        return parameters.get(name);
    }

    public HashMap<String, Double> getVariables(String nameVariables) {
        return variables.get(nameVariables);
    }
}
