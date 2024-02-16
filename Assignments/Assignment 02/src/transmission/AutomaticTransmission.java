package transmission;

public class AutomaticTransmission implements Transmission {
    private int speed = 0;
    private int gear = 0;
    private final int[] gearThresholds = {0, 0, 0, 0, 0};

    public AutomaticTransmission(int gearThreshold1, int gearThreshold2, int gearThreshold3, int gearThreshold4, int gearThreshold5) {
        if (gearThreshold1 > 0) {
            gearThresholds[0] = gearThreshold1;
        }
        else {
            throw new IllegalArgumentException();
        }

        if (gearThreshold2 > gearThreshold1) {
            gearThresholds[1] = gearThreshold2;
        }
        else {
            throw new IllegalArgumentException();
        }

        if (gearThreshold3 > gearThreshold2) {
            gearThresholds[2] = gearThreshold3;
        }
        else {
            throw new IllegalArgumentException();
        }

        if (gearThreshold4 > gearThreshold3) {
            gearThresholds[3] = gearThreshold4;
        }
        else {
            throw new IllegalArgumentException();
        }

        if (gearThreshold5 > gearThreshold4) {
            gearThresholds[4] = gearThreshold5;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    private void setGearBySpeed(int speed) {
        if (speed < 0) {
            throw new IllegalArgumentException("Speed cannot be negative.");
        }
        else if (speed == 0) {
            this.gear = 0;
        }
        else if (speed < gearThresholds[0]) {
            this.gear = 1;
        }
        else if (speed < gearThresholds[1]) {
            this.gear = 2;
        }
        else if (speed < gearThresholds[2]) {
            this.gear = 3;
        }
        else if (speed < gearThresholds[3]) {
            this.gear = 4;
        }
        else if (speed < gearThresholds[4]) {
            this.gear = 5;
        }
        else {
            this.gear = 6;
        }
    }

    public Transmission increaseSpeed() {
        Transmission newTransmission = new AutomaticTransmission(gearThresholds[0], gearThresholds[1], gearThresholds[2], gearThresholds[3], gearThresholds[4]);
        newTransmission.setSpeed(this.speed + 2);

        return newTransmission;
    }

    public Transmission decreaseSpeed() {
        Transmission newTransmission = new AutomaticTransmission(gearThresholds[0], gearThresholds[1], gearThresholds[2], gearThresholds[3], gearThresholds[4]);
        newTransmission.setSpeed(this.speed - 2);

        return newTransmission;
    }

    public String toString() {
        return "Transmission (speed = " + this.speed + ", gear = " + this.gear + ")";
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        if (speed < 0) {
            throw new IllegalStateException("Speed cannot be negative.");
        }
        this.speed = speed;
        this.setGearBySpeed(speed);
    }

    public int getGear() {
        return gear;
    }
}
