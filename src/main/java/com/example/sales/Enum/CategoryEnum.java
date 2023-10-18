package com.example.sales.Enum;

public enum CategoryEnum {
    CLOTHING_AND_APPAREL("CA"),
    ELECTRONICS("E"),
    BEAUTY_AND_PERSONAL_CARE("BPC"),
    HOME_AND_GARDEN("HG"),
    SPORTS_AND_OUTDOORS("SO"),
    HEALTH_AND_WELLNESS("HW"),
    TOYS_AND_GAMES("TG"),
    BOOKS_AND_MEDIA("BM"),
    AUTOMOTIVE("A"),
    FOOD_AND_BEVERAGES("FB"),
    JEWELRY_AND_ACCESSORIES("JA"),
    PET_SUPPLIES("PS"),
    OFFICE_SUPPLIES("OS"),
    ARTS_AND_CRAFTS("AC"),
    BABY_AND_KIDS("BK");

    private final String abbreviation;

    CategoryEnum(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}
