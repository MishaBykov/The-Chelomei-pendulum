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
 * parameters[0] = x<br>
 * parameters[1] = phi<br>
 * parameters[2] = a<br>
 * parameters[3] = b<br>
 * parameters[4] = I0<br>
 * parameters[5] = I1<br>
 * parameters[6] = m<br>
 * parameters[7] = L<br>
 * parameters[8] = k1<br>
 * parameters[9] = k2<br>
 * parameters[10] = M<br>
 * parameters[11] = 9.8<br>
 * parameters[12] = alpha<br>
 * parameters[13] = theta<br>
 * parameters[14] = nu<br>
 */

class Rku {
    private double h, t = 0;
    private double[] parameters = new double[15];
    private double[][] k = new double[4][4];

    final int countParameters = parameters.length;
    int countExample;

    Rku(double x, double phi, double a, double b, double I0, double I1,
        double m, double L, double k1, double k2, double M, double alpha,
        double theta, double nu, double step)
    {
        countExample = 4;
        parameters[0] = x;
        parameters[1] = phi;
        parameters[2] = a;
        parameters[3] = b;
        parameters[4] = I0;
        parameters[5] = I1;
        parameters[6] = m;
        parameters[7] = L;
        parameters[8] = k1;
        parameters[9] = k2;
        parameters[10] = M;
        parameters[11] = 9.8; // g
        parameters[12] = alpha;
        parameters[13] = nu;
        parameters[14] = theta;
        h = step;
    }



    private double functions(double t, double x, double phi, double a, double b, int id) {
        switch (id){
            case 0:
                return a;
            case 1:
                return b;
            case 2:
                return parameters[0]*Math.pow(b, 2) - parameters[9]*a - (parameters[11]*f(1, t))*Math.cos(phi) - f(2, t)*Math.sin(phi);
            case 3:
                return (-2*parameters[6]*x*a*b - parameters[8]*b + (parameters[10]*parameters[7] +
                        parameters[6]*x)*(parameters[11] + f(1, t))*Math.sin(phi) - (parameters[10]*parameters[7] +
                        parameters[6]*x)*f(2, t)*Math.cos(phi))
                        /
                        (parameters[4] + parameters[5]+ parameters[6]*Math.pow(x, 2));
            default:
                return -1;
        }
    }

    double suspensionX() {
        return parameters[12] * Math.sin(parameters[13] * t) * Math.sin(parameters[14]);
    }

    double suspensionY() {
        return parameters[12] * Math.sin(parameters[13] * t) * Math.cos(parameters[14]);
    }

    private double f(int id, double t){
        switch (id){
            case 1:
                return -Math.pow(parameters[14], 2)*parameters[12]*Math.sin(parameters[13])*Math.sin(parameters[14]*t);
            case 2:
                return -Math.pow(parameters[14], 2)*parameters[12]*Math.sin(parameters[13])*Math.cos(parameters[14]*t);
            default:
                return -1;
        }
    }

    void toStep() {
        for (int i = 0; i < countExample; i++) {
            k[0][i] = h * functions(t, parameters[0], parameters[1], parameters[2], parameters[3], i);
        }

        for (int i = 1; i < 3; i++) {
            for (int j = 0; j < countExample; j++) {
                k[i][j] = h * functions(t + h / 2, parameters[0] + k[i - 1][0] / 2, parameters[1] + k[i - 1][1] / 2,
                        parameters[2] + k[i - 1][2] / 2, parameters[3] + k[i - 1][3] / 2, j);
            }
        }

        for (int i = 0; i < countExample; i++) {
            k[3][i] = h * functions(t + h, parameters[0] + k[2][0], parameters[1] + k[2][1],
                    parameters[2] + k[2][0], parameters[3] + k[2][1], i);
        }

        for (int i = 0; i < countExample; i++) {
            parameters[i] += 1.0 / 6 * (k[0][i] + 2 * k[1][i] + 2 * k[2][i] + k[3][i]);
        }

        t += h;

    }


     void toStep(long time) {
         while (time > 0) {
             for (int i = 0; i < countExample; i++) {
                 k[0][i] = h * functions(t, parameters[0], parameters[1], parameters[2], parameters[3], i);
             }

             for (int i = 1; i < 3; i++) {
                 for (int j = 0; j < countExample; j++) {
                     k[i][j] = h * functions(t + h / 2, parameters[0] + k[i - 1][0] / 2, parameters[1] + k[i - 1][1] / 2,
                             parameters[2] + k[i - 1][2] / 2, parameters[3] + k[i - 1][3] / 2, j);
                 }
             }

             for (int i = 0; i < countExample; i++) {
                 k[3][i] = h * functions(t + h, parameters[0] + k[2][0], parameters[1] + k[2][1],
                         parameters[2] + k[2][0], parameters[3] + k[2][1], i);
             }

             for (int i = 0; i < countExample; i++) {
                 parameters[i] += 1.0 / 6 * (k[0][i] + 2 * k[1][i] + 2 * k[2][i] + k[3][i]);
             }

             t += h;

             time--;
         }
     }

    double getParameters(int index) {
        return parameters[index];
    }

    void setParameters(double parameter, int index) {
        parameters[index] = parameter;
    }

}

