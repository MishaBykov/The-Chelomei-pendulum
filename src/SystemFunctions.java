import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * M — Масса стержня,<br>
 * m — масса шайбы,<br>
 * t — время,<br>
 * I0 — момент инерции стержня без шайбы относительно оси вращения,<br>
 * I1 + mx2 — момент инерции шайбы,<br>
 * I1 — собственный момент инерции шайбы,<br>
 * x — текущая координата шайбы, отсчитываемая вдоль стержня,<br>
 * φ — текущий угол поворота стержня при колебаниях, отсчитываемый от вертикали,<br>
 * L — расстояние от центра массы стержня до точки подвеса,<br>
 * (Считаю стержень равномерно-распределенной массы)<br>
 * g — ускорение свободного падения,<br>
 * k1φ' — момент трения, создаваемый движением всей системы,<br>
 * k2x' — сила трения шайбы о стержень,<br>
 * f1(t) — вертикальная составляющая колебаний точки подвеса,<br>
 * f2(t) — горизонтальная составляющая колебаний точки подвеса.<br>

 * parameters[0]  = x     <br>
 * parameters[1]  = phi   <br>
 * parameters[2]  = a     <br>
 * parameters[3]  = b     <br>
 * parameters[4]  = I0    <br>
 * parameters[5]  = I1    <br>
 * parameters[6]  = m     <br>
 * parameters[7]  = L     <br>
 * parameters[8]  = k1    <br>
 * parameters[9]  = k2    <br>
 * parameters[10] = M     <br>
 * parameters[11] = g     <br>
 * parameters[12] = alpha <br>
 * parameters[13] = theta <br>
 * parameters[14] = nu    <br>
 */

public class SystemFunctions implements Functions{
    private Parameters parameters;
    private String[] variables;

    public SystemFunctions(Parameters parameters) {
// default:
        variables = new String[]{"x", "phi", "a", "b"};
// --------
        this.parameters = parameters;
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
