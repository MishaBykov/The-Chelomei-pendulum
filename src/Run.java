import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Run {
    public static void main(String[] args) {
        Functions system = new SystemFunctions(Values.getInstance());
        Pendulum pendulum = new Pendulum(system, Values.getInstance(), 0, Color.magenta);
        Washer washer = new Washer(CrashSystem.getInstance(), system, Values.getInstance(), 0, Color.black);
        RK4 rk4 = new RK4(system, Values.getInstance(), 0.0, Config.getStep());
        final ImagePanel imagePanel = new ImagePanel(
                CrashSystem.getInstance(), pendulum, washer,rk4, 500, 500, Config.getSpeedDown()
        );
        ArrayList<SliderText> sliderTexts = SliderText.initSliderTexts(
                new ArrayList<SliderText>(), Values.getInstance().getVariable(Config.getNameSystem(),)
        );
        sliderTexts = SliderText.initSliderTexts(
                sliderTexts, Values.getInstance().getParameters()
        );
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