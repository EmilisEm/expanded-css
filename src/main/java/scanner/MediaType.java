package scanner;

public enum MediaType {
    SCREEN("Screen"),
    AURAL("Aural");

    private final String name;

    MediaType(String type) {
        name = type;
    }

    public String toString() {
        return name;
    }
}
