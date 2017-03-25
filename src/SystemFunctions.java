import java.awt.geom.Point2D;
import java.util.HashMap;

/**
 * M — Масса стержня,                                                               <br>
 * m — масса шайбы,                                                                 <br>
 * t — время,                                                                       <br>
 * I0 — момент инерции стержня без шайбы относительно оси вращения,                 <br>
 * I1 + mx2 — момент инерции шайбы,                                                 <br>
 * I1 — собственный момент инерции шайбы,                                           <br>
 * x — текущая координата шайбы, отсчитываемая вдоль стержня,                       <br>
 * φ — текущий угол поворота стержня при колебаниях, отсчитываемый от вертикали,    <br>
 * L — расстояние от центра массы стержня до точки подвеса,                         <br>
 * (Считаю стержень равномерно-распределенной массы)                                 <br>
 * g — ускорение свободного падения,                                                <br>
 * k1φ' — момент трения, создаваемый движением всей системы,                        <br>
 * k2x' — сила трения шайбы о стержень,                                             <br>
 * f1(t) — вертикальная составляющая колебаний точки подвеса,                       <br>
 * f2(t) — горизонтальная составляющая колебаний точки подвеса.                     <br>
 */

public class SystemFunctions implements Functions{
    private String nameVariables = "system";
    private HashMap<String, Double> variables;
    private HashMap<String, Double> parameters;
    private boolean crash;


    public SystemFunctions(Values values) {
        parameters = values.getParameters();
        variables = values.getVariables(nameVariables);
        crash = parameters.get("l") < variables.get("x");
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
     * x   = args[0]
     * phi = args[1]
     * dotX   = args[2]
     * dotPhi   = args[3]
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
                double l = parameters.get("l")/2;
                return (-2 * parameters.get("m") * args[0] * args[2] * args[3]
                        - parameters.get("k1") * args[3]
                        + (parameters.get("M") * l + parameters.get("m") * args[0])
                            * (parameters.get("g") + f(1, t)) * Math.sin(args[1])
                        - (parameters.get("M") * l + parameters.get("m") * args[0])
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

    public boolean isCrash() {
        return crash;
    }

    public void setCrash() {
        crash = parameters.get("l") < variables.get("x");
    }
}
