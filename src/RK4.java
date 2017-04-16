import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RK4 {
    private double h, t;
    private double[][] k;
    private Functions functions;
    private Map<String, Double> variables;
    private String[] namesVariables;
    private double[] valueVariables;

    private int countExample;

    RK4(Functions functions, Values values, double t, double step) {
        variables = values.getVariables(functions.getNameVariables());
        countExample = functions.getCountFunctions();
        k = new double[4][countExample];

        Map<String, Double> ov = values.getVariables(functions.getNameVariables()+"Order");
        Set<String> keys = ov.keySet();
        namesVariables = new String[ov.size()];
        for(String key : keys) {
            namesVariables[(int) (double) ov.get(key)] = key;
        }

        valueVariables = new double[namesVariables.length];
        this.functions = functions;
        this.t = t;
        h = step;
    }

    public void toStep() {
        for (int i = 0; i < countExample; i++) {
            for (int j = 0; j < namesVariables.length; j++) {
                valueVariables[j] = variables.get(namesVariables[j]);
            }
            k[0][i] = h * functions.getResultFunction(t, i, valueVariables);
        }

        for (int i = 1; i < 3; i++) {
            for (int j = 0; j < countExample; j++) {
                for (int i1 = 0; i1 < namesVariables.length; i1++) {
                    valueVariables[i1] = variables.get(namesVariables[i1]) + k[i - 1][i1] / 2;
                }
                k[i][j] = h * functions.getResultFunction(t + h / 2, j, valueVariables);
            }
        }

        for (int i = 0; i < countExample; i++) {
            for (int i1 = 0; i1 < namesVariables.length; i1++) {
                valueVariables[i1] = variables.get(namesVariables[i1]) + k[2][i1];
            }
            k[3][i] = h * functions.getResultFunction(t + h, i, valueVariables);
        }

        for (int i = 0; i < namesVariables.length; i++) {
            variables.put(namesVariables[i], variables.get(namesVariables[i])
                    + 1.0 / 6 * (k[0][i] + 2 * k[1][i] + 2 * k[2][i] + k[3][i]));
        }

        t += h;
    }

    public double getT() {
        return t;
    }
}

