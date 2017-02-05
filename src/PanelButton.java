import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanelButton {
    public JPanel panel = new JPanel();
    private ImagePanel imagePanel;
    private final JButton buttonStartStop = new JButton();
    private final JButton buttonReset = new JButton();
    private SliderText[] masSliderText;

    private ActionListener startStop = new ActionListener() {
        private boolean pulsing = false;
        @Override
        public void actionPerformed(ActionEvent e) {
            if (pulsing) {
                pulsing = false;
                imagePanel.stop();
                buttonStartStop.setText("Start");
            } else {
                pulsing = true;
                imagePanel.start();
                buttonStartStop.setText("Stop");
            }
        }
    };

    private ActionListener reset = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (SliderText aMasSliderText : masSliderText) {
                aMasSliderText.resetParameter();
            }
            imagePanel.update();
        }
    };

    public PanelButton(final ImagePanel imagePanel, SliderText[] masSliderText) {
        this.imagePanel = imagePanel;
        this.masSliderText = masSliderText;
        GridLayout gl = new GridLayout(3, 1);
        gl.setVgap(10);
        panel.setLayout(gl);

        buttonStartStop.setText("Start");
        buttonStartStop.setPreferredSize(new Dimension(70, 26));
        buttonStartStop.addActionListener(startStop);
        panel.add(buttonStartStop);

        buttonReset.setText("Reset");
        buttonReset.setPreferredSize(new Dimension(70, 26));
        buttonReset.addActionListener(reset);
        panel.add(buttonReset);
    }
}
