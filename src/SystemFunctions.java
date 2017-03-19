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

 * values[0]  = x     <br>
 * values[1]  = phi   <br>
 * values[2]  = dotX     <br>
 * values[3]  = dotPhi     <br>
 * values[4]  = I0    <br>
 * values[5]  = I1    <br>
 * values[6]  = m     <br>
 * values[7]  = L     <br>
 * values[8]  = k1    <br>
 * values[9]  = k2    <br>
 * values[10] = M     <br>
 * values[11] = g     <br>
 * values[12] = alpha <br>
 * values[13] = theta <br>
 * values[14] = nu    <br>
 */

public class SystemFunctions implements Functions{
    private Values values;
    private String[] variables;

    public SystemFunctions(Values values) {
// default:
        variables = new String[]{"x", "phi", "dotX", "dotPhi"};
// --------
        this.values = values;
    }

    public double suspensionX(double t) {
        return values.getParameter("alpha") * Math.sin(values.getParameter("theta") * t) * Math.sin(values.getParameter("nu"));
    }

    public double suspensionY(double t){
        return  values.getParameter("alpha") * Math.sin( values.getParameter("theta") * t) * Math.cos( values.getParameter("nu"));
    }

    @Override
    public String[] getNameVariables() {
        return variables;
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
                        - values.getParameter("k2") * args[2]
                        - (values.getParameter("g") + f(1, t)) * Math.cos(args[1])
                        - f(2, t) * Math.sin(args[1]);
            case 3:
                double l = values.getParameter("l")/2;
                return (-2 * values.getParameter("m") * args[0] * args[2] * args[3]
                        - values.getParameter("k1") * args[3]
                        + (values.getParameter("M") * l + values.getParameter("m") * args[0])
                            * (values.getParameter("g") + f(1, t)) * Math.sin(args[1])
                        - (values.getParameter("M") * l + values.getParameter("m") * args[0])
                            * f(2, t) * Math.cos(args[1])
                )
                        / (values.getParameter("I0")
                        + values.getParameter("I1")
                        + values.getParameter("m") * Math.pow(args[0], 2)
                );
            default:
                return -1;
        }
    }
    private double f(int id, double t) {
        switch (id) {
            case 1:
                return -Math.pow( values.getParameter("nu"), 2) *  values.getParameter("alpha") * Math.sin( values.getParameter("theta"))
                        * Math.sin( values.getParameter("nu") * t);
            case 2:
                return -Math.pow( values.getParameter("nu"), 2) *  values.getParameter("alpha") * Math.sin( values.getParameter("theta"))
                        * Math.cos( values.getParameter("nu") * t);
            default:
                return -1;
        }
    }

    @Override
    public int getCountFunctions() {
        return 4;
    }
}
