package conservatory;
import birds.Bird;

public class WaterfowlAviary extends Aviary{
    public WaterfowlAviary(int id) {
        super(id);
    }

    @Override
    public String getAviaryType() {
        return "Waterfowl Aviary";
    }

    @Override
    public boolean acceptBird(Bird bird) {
        boolean compatibilityCheck = false;
        if (bird instanceof birds.Waterfowl) {
            compatibilityCheck = true;
        }

        return super.acceptBird(bird)&&compatibilityCheck;
    }
}
