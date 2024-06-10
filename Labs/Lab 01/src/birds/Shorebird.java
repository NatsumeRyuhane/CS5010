package birds;

import utils.BirdFood;

public class Shorebird extends BirdsLiveByWater{
    public Shorebird(String name, String speciesName, String characteristics, int numberOfWings, BirdFood[] preferredFood, boolean isInstinct, String bodyOfWaterLivesBy) {
        super(name, speciesName, characteristics, numberOfWings, preferredFood, isInstinct, bodyOfWaterLivesBy);
    }
}
