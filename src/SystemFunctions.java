public class SystemFunctions implements Functions{
    double[] parameters;


    /**
     * parameters[0] = x      <br>
     * parameters[1] = phi    <br>
     * parameters[2] = a      <br>
     * parameters[3] = b      <br>
     * parameters[4] = I0     <br>
     * parameters[5] = I1     <br>
     * parameters[6] = m      <br>
     * parameters[7] = L      <br>
     * parameters[8] = k1     <br>
     * parameters[9] = k2     <br>
     * parameters[10] = M     <br>
     * parameters[11] = g     <br>
     * parameters[12] = alpha <br>
     * parameters[13] = theta <br>
     * parameters[14] = nu    <br>
     */
    public SystemFunctions(double ... parameters) {
        this.parameters = parameters;
    }

    public double suspensionX(double t) {
        return parameters[12] * Math.sin(parameters[13] * t) * Math.sin(parameters[14]);
    }

    public double suspensionY(double t){
        return parameters[12] * Math.sin(parameters[13] * t) * Math.cos(parameters[14]);
    }

    private double f(int id, double t) {
        switch (id) {
            case 1:
                return -Math.pow(parameters[14], 2) * parameters[12] * Math.sin(parameters[13])
                        * Math.sin(parameters[14] * t);
            case 2:
                return -Math.pow(parameters[14], 2) * parameters[12] * Math.sin(parameters[13])
                        * Math.cos(parameters[14] * t);
            default:
                return -1;
        }
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
                        - parameters[9] * args[2]
                        - (parameters[11] + f(1, t)) * Math.cos(args[1])
                        - f(2, t) * Math.sin(args[1]);
            case 3:
                return (-2 * parameters[6] * args[0] * args[2] * args[3]
                        - parameters[8] * args[3]
                        + (parameters[10] * parameters[7] + parameters[6] * args[0]) * (parameters[11] + f(1, t)) * Math.sin(args[1])
                        - (parameters[10] * parameters[7] + parameters[6] * args[0]) * f(2, t) * Math.cos(args[1])
                )
                        / (parameters[4]
                        + parameters[5]
                        + parameters[6] * Math.pow(args[0], 2)
                );
            default:
                return -1;
        }
    }

    @Override
    public double getParameter(int index) {
        return parameters[index];
    }

    @Override
    public void setParameter(int index, double parameter) {
        parameters[index] = parameter;
    }

    @Override
    public int getCountFunctions() {
        return parameters.length;
    }
}
