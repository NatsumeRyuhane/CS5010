package birds;

import utils.BirdFood;

public abstract class BirdsLiveByWater extends Bird {
    private String bodyOfWaterLivesBy;

    public BirdsLiveByWater(String name, String speciesName, String characteristics, int numberOfWings,
                            BirdFood[] preferredFood, boolean isInstinct, String bodyOfWaterLivesBy) {
        super(name, speciesName, characteristics, numberOfWings, preferredFood, isInstinct);
        setBodyOfWaterLivesBy(bodyOfWaterLivesBy);
    }


    /**
     * gets the body of water the bird lives by
     * @return the body of water the bird lives by
     */
    public String getBodyOfWaterLivesBy() {

        return bodyOfWaterLivesBy;
    }

    /**
     * sets the body of water the bird lives by
     * @param bodyOfWaterLivesBy the body of water the bird lives by
     */
    public void setBodyOfWaterLivesBy(String bodyOfWaterLivesBy) {
        this.bodyOfWaterLivesBy = bodyOfWaterLivesBy;
    }

    /**
     * returns a string representation of the bird
     * @return a string representation of the bird
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString())
           // remove the last { at the end of the string
          .deleteCharAt(sb.length() - 1)
          .append(", bodyOfWaterLivesBy=").append(bodyOfWaterLivesBy).append("}");

        return sb.toString();
    }
}