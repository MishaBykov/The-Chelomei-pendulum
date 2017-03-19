import java.util.Observable;


public class Rku {
    private double h, t;
    private double[][] k = new double[4][4];
    private Functions functions;
    private Parameters parameters;
    private String[] variables;
    private double[] variablesValue;

    private int countExample;

    Rku(Functions functions,Parameters parameters, double t, double step) {
        this.parameters = parameters;
        countExample = functions.getCountFunctions();
        variables = functions.getNameVariables();
        variablesValue = new double[variables.length];
        this.functions = functions;
        this.t = t;
        h = step;
    }

    public void toStep() {
        for (int i = 0; i < countExample; i++) {
            for (int j = 0; j < variables.length; j++) {
                variablesValue[j] = parameters.get(variables[j]);
            }
            k[0][i] = h * functions.getResultFunction(t, i, variablesValue);
        }

        for (int i = 1; i < 3; i++) {
            for (int j = 0; j < countExample; j++) {
                for (int i1 = 0; i1 < variables.length; i1++) {
                    variablesValue[i1] = parameters.get(variables[i1]) + k[i - 1][i1] / 2;
                }
                k[i][j] = h * functions.getResultFunction(t + h / 2, j, variablesValue);
            }
        }

        for (int i = 0; i < countExample; i++) {
            for (int i1 = 0; i1 < variables.length; i1++) {
                variablesValue[i1] = parameters.get(variables[i1]) + k[2][i1];
            }
            k[3][i] = h * functions.getResultFunction(t + h, i, variablesValue);
        }

        for (int i = 0; i < variables.length; i++) {
            parameters.set(variables[i], parameters.get(variables[i])
                    + 1.0 / 6 * (k[0][i] + 2 * k[1][i] + 2 * k[2][i] + k[3][i]));
        }

        t += h;
    }


    public void toStep(long time) {
        while (time > 0) {
            for (int i = 0; i < countExample; i++) {
                k[0][i] = h * functions.getResultFunction(t, i, 
                        parameters.get(variables[0]), 
                        parameters.get(variables[1]),
                        parameters.get(variables[2]),
                        parameters.get(variables[3]));
            }

            for (int i = 1; i < 3; i++) {
                for (int j = 0; j < countExample; j++) {
                    k[i][j] = h * functions.getResultFunction(t + h / 2, j,
                            parameters.get(variables[0]) + k[i - 1][0] / 2,
                            parameters.get(variables[1]) + k[i - 1][1] / 2,
                            parameters.get(variables[2]) + k[i - 1][2] / 2,
                            parameters.get(variables[3]) + k[i - 1][3] / 2);
                }
            }

            for (int i = 0; i < countExample; i++) {
                k[3][i] = h * functions.getResultFunction(t + h, i,
                        parameters.get(variables[0]) + k[2][0],
                        parameters.get(variables[1]) + k[2][1],
                        parameters.get(variables[2]) + k[2][0],
                        parameters.get(variables[3]) + k[2][1]);
            }

            for (int i = 0; i < countExample; i++) {
                parameters.set(variables[i], parameters.get(variables[i])
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

