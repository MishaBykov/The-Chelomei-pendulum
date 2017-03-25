import java.awt.geom.Point2D;

/**
 * values[0] = x; <br>
 * values[1] = phi; <br>
 * values[2] = g; <br>
 * values[3] = m; <br>
 * values[4] = l; <br>
 * values[5] = c; <br>
 * values[6] = alpha; <br>
 * values[7] = nu; <br>
 * values[8] = theta; <br>
 * <br>
 * private double func1(double t, double x, double y) {<br>
 * return y;<br>
 * }<br>
 * <br>
 * private double func2(double t, double x, double y) {<br>
 * return -(2 * values[5] / values[3] * y + (values[2] / values[4] + (-values[6] * Math.pow(values[7], 2) * Math.cos(values[8]) * Math.sin(values[7] * t)) / values[4]) * Math.sin(x) +<br>
 * (-values[6] * Math.pow(values[7], 2) * Math.sin(values[8]) * Math.sin(values[7] * t)) / values[4] * Math.cos(x));<br>
 * }<br>
 * <br>
 * public double suspensionX() {<br>
 * return values[6] * Math.sin(values[7] * t) * Math.sin(values[8]);<br>
 * }<br>
 * <br>
 * public double suspensionY() {<br>
 * return values[6] * Math.sin(values[7] * t) * Math.cos(values[8]);<br>
 * }<br>
 */
public class PendulumFunctions implements Functions {
    private String nameVariables = "pendulum";
    private Values values;
    private String[] variables;

    public PendulumFunctions(Values values) {
// default:
        variables = new String[]{"x", "phi"};
// --------
        this.values = values;
    }

    @Override
    public Point2D.Double suspensionPoint(double t) {
        return new Point2D.Double(
                values.getParameter("alpha") * Math.sin(values.getParameter("theta") * t) * Math.sin(values.getParameter("nu")),
                values.getParameter("alpha") * Math.sin(values.getParameter("theta") * t) * Math.cos(values.getParameter("nu"))
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
                return -2 * values.getParameter("c") / values.getParameter("m") * values.getParameter("dotX")
                        - (values.getParameter("g") / values.getParameter("l") + f(2, t) / values.getParameter("l")) * Math.sin(args[0])
                        - f(1, t) / values.getParameter("l") * Math.cos(args[0]);
            default:
                return -1;
        }
    }

    private double f(int id, double t) {
        switch (id) {
            case 1:
                return -Math.pow(values.getParameter("nu"), 2) * values.getParameter("alpha") * Math.sin(values.getParameter("theta"))
                        * Math.sin(values.getParameter("nu") * t);
            case 2:
                return -Math.pow(values.getParameter("nu"), 2) * values.getParameter("alpha") * Math.sin(values.getParameter("theta"))
                        * Math.cos(values.getParameter("nu") * t);
            default:
                return -1;
        }
    }

    @Override
    public int getCountFunctions() {
        return 2;
    }
}

