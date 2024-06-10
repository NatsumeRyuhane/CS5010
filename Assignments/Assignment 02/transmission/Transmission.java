package transmission;

public interface Transmission {
    Transmission increaseSpeed();
    Transmission decreaseSpeed();
    int getSpeed();
    void setSpeed(int speed);
    int getGear();
    String toString();
}
