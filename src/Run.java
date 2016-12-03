import javax.swing.*;
import java.awt.*;

class Run {
    public static void main(String[] args) {
        final Rku rku = new Rku(0, 0, 1, 1, 1, 1, 1, 1, 1,1, 1, 0, 0, 0, 0.001);
        Setting setting = new Setting(100, 100);
        Pendulum pendulum = new Pendulum(rku, Color.black);
        Washer washer = new Washer(rku, Color.black);
        final ImagePanel imagePanel = new ImagePanel(setting, pendulum, washer, rku, 500, 500, 1);
        final SliderText[] sliderTexts = SliderText.initMSliderText(rku);
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

