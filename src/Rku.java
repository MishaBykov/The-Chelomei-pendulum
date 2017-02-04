import java.util.Observable;

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
 */
public class Rku {
    private double h, t = 0;
    private double[][] k = new double[4][4];
    private Functions functions;
    private String[] variables;

    private int countExample;

    Rku(Functions functions, double step) {
        countExample = functions.getCountFunctions();
        variables = functions.getNameVariables();
        this.functions = functions;
        h = step;
    }

    public void toStep() {
        for (int i = 0; i < countExample; i++) {
            k[0][i] = h * functions.getResultFunction(t, i,
                    functions.getParameter(variables[0]),
                    functions.getParameter(variables[1]),
                    functions.getParameter(variables[2]),
                    functions.getParameter(variables[3]));
        }

        for (int i = 1; i < 3; i++) {
            for (int j = 0; j < countExample; j++) {
                k[i][j] = h * functions.getResultFunction(t + h / 2, j,
                        functions.getParameter(variables[0]) + k[i - 1][0] / 2,
                        functions.getParameter(variables[1]) + k[i - 1][1] / 2,
                        functions.getParameter(variables[2]) + k[i - 1][2] / 2,
                        functions.getParameter(variables[3]) + k[i - 1][3] / 2);
            }
        }

        for (int i = 0; i < countExample; i++) {
            k[3][i] = h * functions.getResultFunction(t + h, i,
                    functions.getParameter(variables[0]) + k[2][0],
                    functions.getParameter(variables[1]) + k[2][1],
                    functions.getParameter(variables[2]) + k[2][0],
                    functions.getParameter(variables[3]) + k[2][1]);
        }

        for (int i = 0; i < countExample; i++) {
            functions.setParameter(variables[i], functions.getParameter(variables[i])
                    + 1.0 / 6 * (k[0][i] + 2 * k[1][i] + 2 * k[2][i] + k[3][i]));
        }

        t += h;
    }


    public void toStep(long time) {
        while (time > 0) {
            for (int i = 0; i < countExample; i++) {
                k[0][i] = h * functions.getResultFunction(t, i, 
                        functions.getParameter(variables[0]), 
                        functions.getParameter(variables[1]),
                        functions.getParameter(variables[2]),
                        functions.getParameter(variables[3]));
            }

            for (int i = 1; i < 3; i++) {
                for (int j = 0; j < countExample; j++) {
                    k[i][j] = h * functions.getResultFunction(t + h / 2, j,
                            functions.getParameter(variables[0]) + k[i - 1][0] / 2,
                            functions.getParameter(variables[1]) + k[i - 1][1] / 2,
                            functions.getParameter(variables[2]) + k[i - 1][2] / 2,
                            functions.getParameter(variables[3]) + k[i - 1][3] / 2);
                }
            }

            for (int i = 0; i < countExample; i++) {
                k[3][i] = h * functions.getResultFunction(t + h, i,
                        functions.getParameter(variables[0]) + k[2][0],
                        functions.getParameter(variables[1]) + k[2][1],
                        functions.getParameter(variables[2]) + k[2][0],
                        functions.getParameter(variables[3]) + k[2][1]);
            }

            for (int i = 0; i < countExample; i++) {
                functions.setParameter(variables[i], functions.getParameter(variables[i])
                + 1.0 / 6 * (k[0][i] + 2 * k[1][i] + 2 * k[2][i] + k[3][i]));
            }

            t += h;

            time--;
        }
    }

    public double getT() {
        return t;
    }
}

