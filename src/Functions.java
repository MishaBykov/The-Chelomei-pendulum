import java.util.ArrayList;

public interface Functions {
    int getCountParameter();
    double getParameter(String nameParameter);
    void setParameter(String nameParameter, double newParameter);

    double getResultFunction(double t, int id, double ... args);
    int getCountFunctions();
}
