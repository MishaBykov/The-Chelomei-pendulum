import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class WasherFunctions implements Functions {
    private String nameVariables = Config.getNameWasher();
    private Map<String, Double> parameters;

    public WasherFunctions(Values values) {
        parameters = values.getParameters();
    }

    @Override
    public String getNameVariables() {
        return nameVariables;
    }

    /**
     * args[0] = x
     * args[1] = y
     * args[0] = dotX
     * args[1] = dotY
     */
    @Override
    public double getResultFunction(double t, int id, double ... args) {
        switch (id) {
            case 0:
                return args[0];
            case 1:
                return args[1];
            case 2:
                return 0;
            case 3:
                return -parameters.get("g");
            default:
                return -1;
        }
    }

    @Override
    public int getCountFunctions() {
        return 4;
    }
}

