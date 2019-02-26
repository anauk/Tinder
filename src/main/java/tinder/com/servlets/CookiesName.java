package tinder.com.servlets;

public enum CookiesName {
    TINDER("my_tinder");
    private String name;

    CookiesName(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
