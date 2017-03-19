import java.awt.geom.Point2D;
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
    String getNameVariables();

    double getResultFunction(double t, int id, double ... args);
    int getCountFunctions();

    Point2D.Double suspensionPoint(double t);
}
