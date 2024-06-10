package conservatory;
import birds.Bird;

public class GenericAviary extends Aviary {
    public GenericAviary(int id) {
        super(id);
    }

    @Override
    public String getAviaryType() {
        return "Generic Aviary";
    }

    @Override
    public boolean acceptBird(Bird bird) {
        boolean compatibilityCheck = true;
        if (bird instanceof birds.BirdOfPrey || bird instanceof birds.FlightlessBird || bird instanceof birds.Waterfowl) {
            compatibilityCheck = false;
        }

        return super.acceptBird(bird)&&compatibilityCheck;
    }

}
