package conservatory;
import birds.Bird;

public class FlightlessBirdAviary extends Aviary{
    public FlightlessBirdAviary(int id) {
        super(id);
    }

    @Override
    public String getAviaryType() {
        return "Flightless Bird Aviary";
    }

    @Override
    public boolean acceptBird(Bird bird) {
        boolean compatibilityCheck = false;
        if (bird instanceof birds.FlightlessBird) {
            compatibilityCheck = true;
        }

        return super.acceptBird(bird)&&compatibilityCheck;
    }
}
