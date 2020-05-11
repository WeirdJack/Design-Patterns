package monopoly.util;

public enum Categories {

    BASIC ("basic"),
    MODERATELY_EXPENSIVE("moderatelyExpensive"),
    SUPER_EXPENSIVE("superExpensive");

    private String category;

    Categories(String categoryIn) {

        category = categoryIn;
    }

    public String getCategory()
    {
        return category;
    }
}
