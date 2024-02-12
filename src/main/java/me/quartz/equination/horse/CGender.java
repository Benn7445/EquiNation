package me.quartz.equination.horse;

public enum CGender {

    MARE("Mare"),
    STALLION("Stallion")
    ;

    private final String name;

    CGender(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}