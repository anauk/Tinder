package tinder.com.entity;

public enum CookiesNames {
    SHOP("my_shop"),
    TINDER("tinder_cookie");
    private String name;

    CookiesNames(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
