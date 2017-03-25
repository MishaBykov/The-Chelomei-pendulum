import java.awt.geom.Point2D;
import java.util.HashMap;

/**
 *
 */
public class PendulumFunctions implements Functions {
    private String nameVariables = "pendulum";
    private HashMap<String, Double> variables;
    private HashMap<String, Double> parameters;

    public PendulumFunctions(Values values) {
        this.variables = values.getVariables(nameVariables);
        this.parameters = values.getParameters();
    }

    @Override
    public Point2D.Double suspensionPoint(double t) {
        return new Point2D.Double(
                parameters.get("alpha") * Math.sin(parameters.get("theta") * t) * Math.sin(parameters.get("nu")),
                parameters.get("alpha") * Math.sin(parameters.get("theta") * t) * Math.cos(parameters.get("nu"))
        );
    }

    @Override
    public String getNameVariables() {
        return nameVariables;
    }

    /**
     * phi = args[0]
     * dotPhi = args[1]
     */
    @Override
    public double getResultFunction(double t, int id, double... args) {
        switch (id) {
            case 0:
                return args[1];
            case 1:
                return -2 * parameters.get("c") / parameters.get("m") * parameters.get("dotX")
                        - (parameters.get("g") / parameters.get("l") + f(2, t) / parameters.get("l")) * Math.sin(args[0])
                        - f(1, t) / parameters.get("l") * Math.cos(args[0]);
            default:
                return -1;
        }
    }

    private double f(int id, double t) {
        switch (id) {
            case 1:
                return -Math.pow(parameters.get("nu"), 2) * parameters.get("alpha") * Math.sin(parameters.get("theta"))
                        * Math.sin(parameters.get("nu") * t);
            case 2:
                return -Math.pow(parameters.get("nu"), 2) * parameters.get("alpha") * Math.sin(parameters.get("theta"))
                        * Math.cos(parameters.get("nu") * t);
            default:
                return -1;
        }
    }

    @Override
    public int getCountFunctions() {
        return 2;
    }
}

