public enum Setting {
    OFF("---"),
    LOW("--+"),
    MEDIUM("-++"),
    HIGH("+++");

    private final String bars;

    Setting(String bars) { this.bars = bars; }

    @Override
    public String toString() { return bars; }

    // Optional helpers (nice for Burner logic later)
    public Setting up() {
        switch (this) {
            case OFF:    return LOW;
            case LOW:    return MEDIUM;
            case MEDIUM: return HIGH;
            case HIGH:   default: return HIGH;
        }
    }

    public Setting down() {
        switch (this) {
            case HIGH:   return MEDIUM;
            case MEDIUM: return LOW;
            case LOW:    return OFF;
            case OFF:    default: return OFF;
        }
    }
}