import java.util.Set;

/**
 * getNameVariables   <br><br>
 * getCountParameters <br>
 * getParameter       <br>
 * setParameter       <br><br>
 * getResultFunction  <br>
 * getCountFunctions  <br>
 */
public interface Functions {
    String[] getNameVariables();
    
    int getCountParameters();
    Set<String> getKeyParameters();
    double getParameter(String name);
    void setParameter(String name, double newP);

    double getResultFunction(double t, int id, double ... args);
    int getCountFunctions();
}
