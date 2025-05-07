package com.group16.util;

public enum  RecipeStyle {
    STARTER(0), MAIN(1), DESSERT(2), SIDE(3), DRESSING(4), BREAKFAST(5), SNACK(6);
    private final int value;

    RecipeStyle(int value) {
        this.value = value;
    }

    public static RecipeStyle fromInt(int value) {
        switch (value) {
            case 0: return STARTER;
            case 1: return MAIN;
            case 2: return DESSERT;
            case 3: return SIDE;
            case 4: return DRESSING;
            case 5: return BREAKFAST;
            case 6: return SNACK;
            default: return null;
        }
    }

    public int getValue() {
        return value;
    }

    public String getString(){
        switch (this) {
            case STARTER: return "Alkuruoka";
            case MAIN: return "Pääruoka";
            case DESSERT: return "Jälkiruoka";
            case SIDE: return "Lisuke";
            case DRESSING: return "Kastike";
            case BREAKFAST: return "Aamupala";
            case SNACK: return "Välipala";
            default: return "Ei tiedossa";      
        }
    }
}
