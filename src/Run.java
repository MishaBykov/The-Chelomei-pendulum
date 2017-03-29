import javax.swing.*;
import java.awt.*;

public class Run {
    public static void main(String[] args) {
        Values values = new Values();
        Functions system = new SystemFunctions(values);
        CrashSystem crashSystem = new CrashSystem(values);
        Pendulum pendulum = new Pendulum(system, values, 0, Color.magenta);
        Washer washer = new Washer(system, values, 0, Color.black);
        final RK4 RK4 = new RK4(system, values, 0.0, Setting.getSpeedDown());
        final ImagePanel imagePanel = new ImagePanel(crashSystem, pendulum, washer,
                RK4, 500, 500, Setting.getSpeedDown());
        final SliderText[] sliderTextsV = SliderText.initMSliderText(values.getVariables("system"));
        final SliderText[] sliderTextsP = SliderText.initMSliderText(values.getParameters());
        final PanelButton panelButton = new PanelButton(imagePanel, sliderTextsV);
        final JPanel mSliderText = new JPanel(new GridLayout(0, 2));
        for (SliderText sliderText : sliderTextsV) {
            mSliderText.add(sliderText);
        }
        for (SliderText sliderText : sliderTextsP) {
            mSliderText.add(sliderText);
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

