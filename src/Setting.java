public class Setting {
    private double scale;
    private double widthWasher = 10;
    private double heightWasher = 10;
    private long speed;

    Setting(double scale, int speed){
        this.scale = scale;
        this.speed = speed;
    }

    public double getScale() {
        return scale;
    }

    public long getSpeed() {
        return speed;
    }

    public double getHeightWasher() {
        return heightWasher;
    }

    public double getWidthWasher() {
        return widthWasher;
    }
}
