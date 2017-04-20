import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class SliderText extends JPanel {

//    public final JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    private final JSlider slider = new JSlider();
    private final JTextField textField = new JTextField();
    EditValue edf;
    private String nameVariable;
    private String name;
    private double scale;

    private SliderText(EditValue f, String nameVariable, String name, double scale) {
        super(new FlowLayout(FlowLayout.LEFT));

        this.edf = f;
        this.nameVariable = nameVariable;
        this.name = name;
        this.scale = scale;

        this.add(new JLabel(name));

        textField.setText(Double.toString(edf.getValue(name)));
        textField.getDocument().addDocumentListener(dl);
        textField.setPreferredSize(new Dimension(70, 26));
        this.add(textField);

        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        slider.addChangeListener(cl);
        slider.setVisible(false);
        this.add(slider);
    }

    ChangeListener cl = new ChangeListener() {
        public void stateChanged(ChangeEvent event) {
            JSlider source = (JSlider) event.getSource();
            textField.getDocument().removeDocumentListener(dl);
            textField.setText(Double.toString(source.getValue()/scale));
            textField.getDocument().addDocumentListener(dl);
        }
    };

    DocumentListener dl = new DocumentListener() {
        public void insertUpdate(DocumentEvent e) {
            if (textField.getText().matches("^\\d+\\.\\d+$") || (textField.getText().matches("^\\d+$"))) {
                double a = Double.parseDouble(textField.getText())*scale;
                String str = Double.toString(a);
                int b = Integer.parseInt(str.replaceAll("\\.\\d+$",""));
                if (a <= slider.getMaximum() && a >= slider.getMinimum()) {
                    slider.removeChangeListener(cl);
                    slider.setValue(b);
                    slider.addChangeListener(cl);
                }
            }
        }

        public void removeUpdate(DocumentEvent e) {
            if (textField.getText().matches("^\\d+\\.\\d+$") || (textField.getText().matches("^\\d+$"))) {
                double a = Double.parseDouble(textField.getText())*scale;
                String str = Double.toString(a);
                int b = Integer.parseInt(str.replaceAll("\\.\\d+$",""));
                if (a <= slider.getMaximum() && a >= slider.getMinimum()) {
                    slider.removeChangeListener(cl);
                    slider.setValue(b);
                    slider.addChangeListener(cl);
                }
            }
        }

        public void changedUpdate(DocumentEvent e) {
            if ((textField.getText().matches("^\\d+\\.\\d+$")) || (textField.getText().matches("^\\d+$"))) {
                double a = Double.parseDouble(textField.getText())*scale;
                String str = Double.toString(a);
                int b = Integer.parseInt(str.replaceAll("\\.\\d+$",""));
                if (a <= slider.getMaximum() && a >= slider.getMinimum()) {
                    slider.removeChangeListener(cl);
                    slider.setValue(b);
                    slider.addChangeListener(cl);
                }
            }
        }
    };

    void resetParameter(){
        if ((textField.getText().matches("^\\d+\\.\\d+$")) || (textField.getText().matches("^\\d+$"))) {
            edf.setValue(Double.parseDouble(textField.getText()), name, nameVariable);
        }
    }

//  x, phi, dotX, dotPhi, I1,
//  I2, m, L, k1, k2,
//  M, alpha, theta, nu

    public static SliderText[] initSliderTexts(String[] names){
        SliderText[] result = new SliderText[names.length];
        for (int i=0;i< names.length;i++) {
            result[i] = new SliderText(new EditParameter(), null, names[i], Config.getScaleSlider().get(names[i]));
        }
        return result;
    }

    public static SliderText[] initSliderTexts(String[] names, String nameVariable) {
        SliderText[] result = new SliderText[names.length];
        for (int i = 0; i < names.length; i++) {
            result[i] = new SliderText(new EditParameter(), nameVariable, names[i], Config.getScaleSlider().get(names[i]));
        }
        return result;
    }

    private interface EditValue{
        void setValue(double newValue, String ... args);
        double getValue(String... args);
    }

    private static class EditParameter implements EditValue{
        Values values;

        public EditParameter() {
            values = Values.getInstance();
        }


        @Override
        public void setValue(double newValue, String... args) {
            values.setParameter(args[0], newValue);
        }

        @Override
        public double getValue(String ... args) {
            return values.getParameter(args[0]);
        }
    }

    private static class EditVariable implements EditValue{
        Values values;

        public EditVariable() {
            values = Values.getInstance();
        }


        @Override
        public void setValue(double newValue, String... args) {
            values.setVariable(args[1], args[0], newValue);
        }

        @Override
        public double getValue(String... args) {
            return values.getVariable(args[1], args[0]);
        }
    }
}

