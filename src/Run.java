import javax.swing.*;
import java.awt.*;

public class Run {
    public static void main(String[] args) {
        Setting setting = new Setting(100, 10);

        final Rku rku = new Rku(0, 0, 0, 4, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1.0/(setting.getSpeed()*10));
        Pendulum pendulum = new Pendulum(rku, Color.magenta);
        Washer washer = new Washer(rku, Color.red);
        rku.addObserver(pendulum);
        rku.addObserver(washer);
        final ImagePanel imagePanel = new ImagePanel(setting, pendulum, washer, rku, 500, 500, setting.getSpeed());
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

