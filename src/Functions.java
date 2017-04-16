import java.awt.geom.Point2D;
import java.util.Set;

/**
 * String getNameVariables() - получение имени переменных <br>
 * double getResultFunction(double t, int id, double ... args) -
 * получение результата функции <br>
 * getCountFunctions() - количество функций <br>
 */
public interface Functions {
    String getNameVariables();

    double getResultFunction(double t, int id, double ... args);
    int getCountFunctions();
}
