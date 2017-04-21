import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private Map<String, String[]> namesVariables;

    private Values() {
        variables = new HashMap<String, Map<String, Double>>();
        namesVariables = new HashMap<String, String[]>();
        parameters = new HashMap<String, Double>();

        Map<String, Double> variableSystem = new HashMap<String, Double>();
        Map<String, Double> variableWasher = new HashMap<String, Double>();

        parameters.put("I0", 1.0);
        parameters.put("I1", 1.0);
        parameters.put("m", 1.0);
        parameters.put("l", 1.0);
        parameters.put("k1", 1.0);
        parameters.put("k2", 1.0);
        parameters.put("M", 1.0);
        parameters.put("g", 9.8);
        parameters.put("alpha", 0.1);
        parameters.put("nu", 100.0);
        parameters.put("theta", 1.0);

        variableSystem.put("x", 1.0);
        variableSystem.put("phi", 1.0);
        variableSystem.put("dotX", 1.0);
        variableSystem.put("dotPhi", 1.0);
        variables.put(Config.getNameSystem(), variableSystem);

        variableWasher.put("x", 0.0);
        variableWasher.put("y", 0.0);
        variableWasher.put("dotX", 0.0);
        variableWasher.put("dotY", 0.0);
        variables.put(Config.getNameWasher(), variableWasher);

        namesVariables.put(Config.getNameSystem(), new String[]{"x", "phi", "dotX", "dotPhi"});

        namesVariables.put(Config.getNameWasher(), new String[]{"x", "y", "dotX", "dotY"});
    }

    public static Values getInstance() {
        if (instance == null) {
            instance = new Values();
        }
        return instance;
    }

    public Map<String, Double> getParameters(){
        return parameters;
    }

    public Double getParameter(String nameParameter){
        return parameters.get(nameParameter);
    }

    public Double getVariable(String nameGroups, String nameVariable) {
        return variables.get(nameGroups).get(nameVariable);
    }

    public void setParameter(String nameParameter, Double newValue) {
        parameters.put(nameParameter, newValue);
    }

    public void setVariable(String nameGroups, String nameVariable, Double newValue) {
        if (nameVariable.charAt(0) == 'x' && newValue < 0 && nameGroups.equals(Config.getNameSystem())
                && variables.get(Config.getNameSystem()).get("dotX") < 0) {
            variables.get(Config.getNameSystem()).put("dotX", 0.0);
            variables.get(nameGroups).put(nameVariable, 0.0);
        } else {
            variables.get(nameGroups).put(nameVariable, newValue);
        }
    }

    public String[] getNamesVariables(String nameVariable) {
        return namesVariables.get(nameVariable);
    }

    public String[] getNamesParameters() {
        return (String[]) parameters.keySet().toArray();
    }
}
