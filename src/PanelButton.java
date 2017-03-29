import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PanelButton extends JPanel{
    private ImagePanel imagePanel;
    private final JButton buttonStartStop = new JButton();
    private final JButton buttonReset = new JButton();
    private ArrayList<SliderText> sliderTexts;

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
            for (SliderText sliderText : sliderTexts) {
                sliderText.resetParameter();
            }
            imagePanel.update();
        }
    };

    public PanelButton(final ImagePanel imagePanel, ArrayList<SliderText> sliderTexts) {
        this.imagePanel = imagePanel;
        this.sliderTexts = sliderTexts;
        GridLayout gl = new GridLayout(3, 1);
        gl.setVgap(10);
        this.setLayout(gl);

        buttonStartStop.setText("Start");
        buttonStartStop.setPreferredSize(new Dimension(70, 26));
        buttonStartStop.addActionListener(startStop);
        this.add(buttonStartStop);

        buttonReset.setText("Reset");
        buttonReset.setPreferredSize(new Dimension(70, 26));
        buttonReset.addActionListener(reset);
        this.add(buttonReset);
    }
}
