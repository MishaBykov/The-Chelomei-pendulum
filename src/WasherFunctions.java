import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class WasherFunctions implements Functions {
    private String nameVariables = "washer";
    private Map<String, Double> variables;
    private Map<String, Double> parameters;
    public WasherFunctions(Values values) {
        parameters = values.getParameters();
        variables = values.getVariables(nameVariables);
    }

    @Override
    public String getNameVariables() {
        return nameVariables;
    }

    /**
     * args[0] = x
     * args[1] = y
     */
    @Override
    public double getResultFunction(double t, int id, double ... args) {
        switch (id) {
            case 0:
                return 0;
            case 1:
                return -parameters.get("g");
            default:
                return -1;
        }
    }

    @Override
    public int getCountFunctions() {
        return 2;
    }
}

