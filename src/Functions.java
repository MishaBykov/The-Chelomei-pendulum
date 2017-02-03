import java.util.ArrayList;

public interface Functions {
    double getParameters(int index);
    void setParameters(double parameter, int index);

    double getResultFunction(double t, int id, double ... args);
    int getCountFunctions();
}
