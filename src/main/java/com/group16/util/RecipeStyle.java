package com.group16.util;

public enum  RecipeStyle {
    STARTER, MAIN, DESSERT, SIDE, DRESSING, BREAKFAST, SNACK; 

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
