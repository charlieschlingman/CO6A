public class Burner {
    public enum Temperature { BLAZING, HOT, WARM, COLD }

    public static final int TIME_DURATION = 2;

    private Temperature myTemperature = Temperature.COLD;
    private Setting mySetting = Setting.OFF;
    private int timer = 0;

    public Temperature getTemperature() {
        return myTemperature;
    }
    public void plusButton() {
        switch (mySetting) {
            case OFF:    mySetting = Setting.LOW;    break;
            case LOW:    mySetting = Setting.MEDIUM; break;
            case MEDIUM: mySetting = Setting.HIGH;   break;
            case HIGH:   break; // already highest
        }
        timer = TIME_DURATION;
    }

    public void minusButton() {
        switch (mySetting) {
            case HIGH:   mySetting = Setting.MEDIUM; break;
            case MEDIUM: mySetting = Setting.LOW;    break;
            case LOW:    mySetting = Setting.OFF;    break;
            case OFF:    break; // already lowest
        }
        timer = TIME_DURATION;
    }
    private Temperature targetFor(Setting s) {
        switch (s) {
            case OFF:    return Temperature.COLD;
            case LOW:    return Temperature.WARM;
            case MEDIUM: return Temperature.HOT;
            case HIGH:   return Temperature.BLAZING;
            default:     return Temperature.COLD;
        }
    }

    public void updateTemperature() {
        if (timer > 0) {
            timer--;
            if (timer == 0) {
                Temperature target = targetFor(mySetting);
                if (myTemperature != target) {
                    myTemperature = stepToward(myTemperature, target);
                    if (myTemperature != target) timer = TIME_DURATION;
                }
            }
        }
    }
    private Temperature stepToward(Temperature current, Temperature target) {
        if (rank(current) < rank(target)) return upOne(current);
        if (rank(current) > rank(target)) return downOne(current);
        return current;
    }

    private int rank(Temperature t) {
        switch (t) {
            case COLD: return 0;
            case WARM: return 1;
            case HOT: return 2;
            case BLAZING: return 3;
            default: return 0;
        }
    }
    private Temperature upOne(Temperature t) {
        switch (t) {
            case COLD: return Temperature.WARM;
            case WARM: return Temperature.HOT;
            case HOT:  return Temperature.BLAZING;
            default:   return Temperature.BLAZING;
        }
    }

    private Temperature downOne(Temperature t) {
        switch (t) {
            case BLAZING: return Temperature.HOT;
            case HOT:     return Temperature.WARM;
            case WARM:    return Temperature.COLD;
            default:      return Temperature.COLD;
        }
    }

    public void display() {
        System.out.printf("[ %s ]  %s%n", mySetting.toString(), myTemperature);
    }
}
