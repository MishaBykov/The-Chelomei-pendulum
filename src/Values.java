import java.util.HashMap;
import java.util.Map;

/*
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
 */
public class Values {
    private static Values instance;
    private Map<String, Double> parameters;
    private Map<String, Map<String, Double>> variables;

    private Values() {

        String[] parametersName = new String[]{"I0", "I1", "m", "l", "k1", "k2", "M", "g", "alpha", "nu", "theta"};
        double[] parametersValue = new double[]{ 1 ,   1 ,  1 ,  2 ,   1 ,   1 ,  1 , 9.8,      0 ,   0 ,      0 };

        String[] systemName = new String[]{"x", "phi", "dotX", "dotPhi"};
        double[] systemValue = new double[]{1,    1,      1,      1};

        String[] washerName = new String[]{"x", "y", "dotX", "dotY"};
        double[] washerValue = new double[]{0,   0,     0,      0};

        parameters = new HashMap<String, Double>();
        for (int i = 0; i < parametersName.length; i++) {
            parameters.put(parametersName[i], parametersValue[i]);
        }

        variables = new HashMap<String, Map<String, Double> > ();

        variables.put("system", new HashMap<String, Double>());
        variables.put("systemOrder", new HashMap<String, Double>());
        for (int i = 0; i < systemName.length; i++) {
            variables.get("system").put(systemName[i], systemValue[i]);
            variables.get("systemOrder").put(systemName[i],(double) i);
        }

        variables.put("washer", new HashMap<String, Double>());
        variables.put("washerOrder", new HashMap<String, Double>());
        for (int i = 0; i < washerName.length; i++) {
            variables.get("washer").put(washerName[i], washerValue[i]);
            variables.get("washerOrder").put(washerName[i],(double) i);
        }
    }

    public static Values getInstance() {
        if (instance == null) {
            instance = new Values();
        }
        return instance;
    }

    public Map<String, Double> getParameters() {
        return parameters;
    }

    public Map<String, Double> getVariables(String nameVariables) {
        return variables.get(nameVariables);
    }
}
