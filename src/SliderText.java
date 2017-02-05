import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.util.Set;

public class SliderText extends JFrame {

    public final JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    private final JSlider slider = new JSlider();
    private final JTextField textField = new JTextField();
    private String nameParameter;
    private Functions functions;
    private double scale;

    SliderText(Functions functions, String nameParameter, double scale) {
        this.scale = scale;
        this.functions = functions;
        this.nameParameter = nameParameter;
        panel.add(new JLabel(nameParameter));

        textField.setText(Double.toString(functions.getParameter(nameParameter)));
        textField.getDocument().addDocumentListener(dl);
        textField.setPreferredSize(new Dimension(70, 26));
        panel.add(textField);

        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        slider.addChangeListener(cl);
        slider.setVisible(false);
        panel.add(slider);
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
            functions.setParameter(nameParameter, Double.parseDouble(textField.getText()));
        }
    }

//  x, phi, a, b, I1,
//  I2, m, L, k1, k2,
//  M, alpha, theta, nu

    public static SliderText[] initMSliderText(Functions functions, double[] scale) {
        SliderText[] mSL = new SliderText[functions.getCountParameters()];
        int i = 0;
        for (String key : functions.getKeyParameters()) {
            mSL[i] = new SliderText(functions, key, scale[i]);
            i++;
        }
        /*
        for (int i = 0; i < functions.getCountParameters(); i++) {
            mSL[i] = new SliderText(functions, nameParameters[i], scale[i]);
        }

        mSL[0] = new SliderText(functions, 0, "x", 1);
        mSL[1] = new SliderText(functions, 1, "phi", 1);
        mSL[2] = new SliderText(functions, 2, "a", 1);
        mSL[3] = new SliderText(functions, 3, "b", 1);
        mSL[4] = new SliderText(functions, 4, "I1", 1);
        mSL[5] = new SliderText(functions, 5, "I2", 1);
        mSL[6] = new SliderText(functions, 6, "m", 1);
        mSL[7] = new SliderText(functions, 7, "L", 1);
        mSL[8] = new SliderText(functions, 8, "k1", 1);
        mSL[9] = new SliderText(functions, 9, "k2", 1);
        mSL[10] = new SliderText(functions, 10, "M", 1);
        mSL[11] = new SliderText(functions, 11, "g", 1);
        mSL[12] = new SliderText(functions, 12, "alpha", 1);
        mSL[14] = new SliderText(functions, 13, "nu", 1);
        mSL[13] = new SliderText(functions, 14, "theta", 1);

        mSL[0].slider.setVisible(false);
        mSL[1].slider.setVisible(false);
        mSL[2].slider.setVisible(false);
        mSL[4].slider.setMaximum(500);
        mSL[8].slider.setMaximum(314);
        mSL[4].textField.setText("" + functions.getParameters(7));
        mSL[8].textField.setText("" + functions.getParameters(8));
        */
        return mSL;
    }
}

