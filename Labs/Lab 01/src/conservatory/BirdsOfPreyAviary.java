package conservatory;

import birds.Bird;

public class BirdsOfPreyAviary extends Aviary{
    public BirdsOfPreyAviary(int id) {
        super(id);
    }

    @Override
    public String getAviaryType() {
        return "Birds of Prey Aviary";
    }

    @Override
    public boolean acceptBird(Bird bird) {
        boolean compatibilityCheck = false;
        if (bird instanceof birds.BirdOfPrey) {
            compatibilityCheck = true;
        }

        return super.acceptBird(bird)&&compatibilityCheck;
    }
}
