import javax.swing.*;
import java.awt.*;

public class Run {
    public static void main(String[] args) {
        Functions system = new SystemFunctions();
        final Rku rku = new Rku(system, 1.0 / (Setting.getSpeedDown() * 10));
        Pendulum pendulum = new Pendulum(system, rku, Color.magenta);
        Washer washer = new Washer(system, rku, Color.black);
        final ImagePanel imagePanel = new ImagePanel(pendulum, washer, rku, 500, 500, Setting.getSpeedDown());
        final SliderText[] sliderTexts = SliderText.initMSliderText(system,
                new double[]{1, 1, 1, 1, 1, 1, 1, 1/*, 1, 1, 1, 1, 1, 1, 1*/}
        );
        final PanelButton panelButton = new PanelButton(imagePanel, sliderTexts);
        final JPanel mSliderText = new JPanel(new GridLayout(0, 2));
        for (SliderText sliderText : sliderTexts) {
            mSliderText.add(sliderText.panel);
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                final JFrame frame = new JFrame("The Chelomei pendulum");
                JPanel all = new JPanel();
                all.add(imagePanel);
                all.add(mSliderText);
                all.add(panelButton.panel);
                frame.getContentPane().add(all);
                frame.setSize(1050, 740);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }

}

