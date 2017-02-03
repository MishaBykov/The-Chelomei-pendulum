import java.util.ArrayList;

public interface Functions {
    double getParameter(int index);
    void setParameter(int index, double parameter);

    double getResultFunction(double t, int id, double ... args);
    int getCountFunctions();
}
