package tinder.com.servlets;

public enum CookiesNames {

    SHOP("myShop_cookie"),
    TINDER("tinder_cookie");

    private String name;

    CookiesNames(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
