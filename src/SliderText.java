import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.util.HashMap;

public class SliderText extends JPanel {

//    public final JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    private final JSlider slider = new JSlider();
    private final JTextField textField = new JTextField();
    private HashMap<String, Double> groups;
    private String name;
    private double scale;

    private SliderText(HashMap<String, Double> groups, String name, double scale) {
        super(new FlowLayout(FlowLayout.LEFT));
        this.scale = scale;
        this.name = name;
        this.groups = groups;
        this.add(new JLabel(name));

        textField.setText(Double.toString(groups.get(name)));
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
            groups.put(name, Double.parseDouble(textField.getText()));
        }
    }

//  x, phi, dotX, dotPhi, I1,
//  I2, m, L, k1, k2,
//  M, alpha, theta, nu

    public static SliderText[] initMSliderText(HashMap<String, Double> groups, double[] scale) {
        SliderText[] mSL = new SliderText[values.getCountParameters()];
        int i = 0;
        for (String key : values.getKeyParameters()) {
            mSL[i] = new SliderText(values, key, scale[i]);
            i++;
        }

        return mSL;
    }
}

