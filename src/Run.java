import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Run {
    public static void main(String[] args) {
        Values values = new Values();
        Functions system = new SystemFunctions(values);
        CrashSystem crashSystem = new CrashSystem(values);
        Pendulum pendulum = new Pendulum(system, values, 0, Color.magenta);
        Washer washer = new Washer(system, values, 0, Color.black);
        RK4 RK4 = new RK4(system, values, 0.0, Config.getStep());
        final ImagePanel imagePanel = new ImagePanel(crashSystem, pendulum, washer,
                RK4, 500, 500, Config.getSpeedDown());
        ArrayList<SliderText> sliderTexts = SliderText.initMSliderText(new ArrayList<SliderText>(),
                values.getVariables("system"));
        sliderTexts = SliderText.initMSliderText(sliderTexts, values.getParameters());
        final PanelButton panelButton = new PanelButton(imagePanel, sliderTexts);
        final JPanel panelSliderText = new JPanel(new GridLayout(0, 2));
        for (SliderText sliderText : sliderTexts) {
            panelSliderText.add(sliderText);
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                final JFrame frame = new JFrame("The Chelomei pendulum");
                JPanel all = new JPanel();
                all.add(imagePanel);
                all.add(panelSliderText);
                all.add(panelButton);
                frame.getContentPane().add(all);
                frame.setSize(1050, 740);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }

}

