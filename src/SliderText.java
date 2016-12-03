import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

class SliderText extends JFrame {

    final JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    final JSlider slider = new JSlider();
    final JTextField textField = new JTextField();
    private int idParameter;
    private Rku rku;
    private double scale;

    SliderText(Rku rku, int idParameter, String str, double scale) {
        this.scale = scale;
        this.rku = rku;
        this.idParameter = idParameter;
        panel.add(new JLabel(str));

        textField.setText("" + rku.getParameters(idParameter));
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
            textField.setText("" + source.getValue()/scale);
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
            rku.setParameters(Double.parseDouble(textField.getText()), idParameter);
        }
    }

//  x, phi, a, b, I1,
//  I2, m, L, k1, k2,
//  M, alpha, theta, nu

    public static SliderText[] initMSliderText(Rku rku) {
        SliderText[] mSL = new SliderText[rku.countParameters];
        mSL[0] = new SliderText(rku, 0, "x", 1);
        mSL[1] = new SliderText(rku, 1, "phi", 1);
        mSL[2] = new SliderText(rku, 2, "a", 1);
        mSL[3] = new SliderText(rku, 3, "b", 1);
        mSL[4] = new SliderText(rku, 4, "I1", 1);
        mSL[5] = new SliderText(rku, 5, "I2", 1);
        mSL[6] = new SliderText(rku, 6, "m", 1);
        mSL[7] = new SliderText(rku, 7, "L", 1);
        mSL[8] = new SliderText(rku, 8, "k1", 1);
        mSL[9] = new SliderText(rku, 9, "k2", 1);
        mSL[10] = new SliderText(rku, 10, "M", 1);
        mSL[11] = new SliderText(rku, 11, "g", 1);
        mSL[12] = new SliderText(rku, 12, "alpha", 1);
        mSL[14] = new SliderText(rku, 13, "nu", 1);
        mSL[13] = new SliderText(rku, 14, "theta", 1);
        /*
        mSL[0].slider.setVisible(false);
        mSL[1].slider.setVisible(false);
        mSL[2].slider.setVisible(false);
        mSL[4].slider.setMaximum(500);
        mSL[8].slider.setMaximum(314);
        mSL[4].textField.setText("" + rku.getParameters(7));
        mSL[8].textField.setText("" + rku.getParameters(8));
        */
        return mSL;
    }
}

