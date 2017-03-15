import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Parameters {
    private Map<String, Double> parameters;

    public Parameters() {

        String[] parametersName = new String[]{"x", "phi", "a", "b", "I0", "I1", "m", "l",
                "k1", "k2", "M", "g", "alpha", "nu", "theta"};
        double[] parametersValue = new double[]{0.0, 0.0, 0, 0, 1, 1, 1, 1,
                1, 1, 1, 9.8, 0, 0, 0};

        parameters = new HashMap<String, Double>();
        for (int i = 0; i < parametersName.length; i++) {
            parameters.put(parametersName[i], parametersValue[i]);
        }

//        this.parameters = parameters;
    }

    public int getCountParameters() {
        return parameters.size();
    }

    public Set<String> getKeyParameters() {
        return parameters.keySet();
    }

    public void set(String nameParameter, double newValue) {
        if (parameters.get("x")<0 && parameters.get("a")<0){
            parameters.put("x", 0.0);
            parameters.put("a", 0.0);
        }
        parameters.put(nameParameter, newValue);
    }

    public double get(String name) {
        return parameters.get(name);
    }

}
