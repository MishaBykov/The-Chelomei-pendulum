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

    double getResultFunction(double t, int id, double ... args);
    int getCountFunctions();

    double suspensionX(double t);
    double suspensionY(double t);
}
