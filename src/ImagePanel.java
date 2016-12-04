import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Date;


public class ImagePanel extends JComponent implements ActionListener {
    private Timer timer;
    private int width;
    private int height;
    private int dx;
    private int dy;
    private Setting setting;
    private Pendulum pendulum;
    private Washer washer;
    private Rku rku;

    private long[] time = new long[2];


    ImagePanel(Setting setting, Pendulum pendulum, Washer washer, Rku rku, int height, int width, int delay) {
        this.setting = setting;
        this.pendulum = pendulum;
        this.washer = washer;
        this.rku = rku;
        timer = new Timer(delay, this);
        this.width = width;
        this.height = height;
        dx = width / 2;
        dy = height / 2;



        setPreferredSize(new Dimension(width, height));
    }

    public void start() {
        time[0] = System.currentTimeMillis();

        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    private Point2D.Double toSystem(Point2D.Double point){
        return new Point2D.Double(dx + setting.getScale() * point.getX(), dy - setting.getScale() * point.getY());
    }

//    private void drawWasher(Graphics2D g2d, double len, double angle) {

//        return new Point2D.Double(-Math.sin(angle) * setting.getScale() * len + one.getX(),
//                -Math.cos(angle) * setting.getScale() * len + one.getY());
//    }

    public void actionPerformed(ActionEvent event) {
        time[1] = System.currentTimeMillis();
        System.out.println((time[1] - time[0])/setting.getSpeed());
        rku.toStep((time[1] - time[0])/setting.getSpeed());
        time[0] = time[1];
        pendulum.update();
        washer.update();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.white);
        g.fillRect(0, 0, width, height);
        g2d.setColor(Color.black);
        g2d.drawRect(0, 0, width - 1, height - 1);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawLine(0, dy, width, dx);
        g2d.drawLine(dx, 0, dy, height);

        g2d.setColor(pendulum.getColor());
        g2d.draw(new Line2D.Double(toSystem(pendulum.getOnePoint()), toSystem(pendulum.getTwoPoint())));

        g2d.setColor(washer.getColor());
        g2d.draw(new Ellipse2D.Double(toSystem(washer.getCenterWasher()).getX()-setting.getWidthWasher()/2,
                toSystem(washer.getCenterWasher()).getY()-setting.getHeightWasher()/2,
                setting.getWidthWasher(), setting.getHeightWasher()));
    }
}