package com.interapt.android.InsiderLouisville.enums;


public enum BUSINESS {
    MERCURY (""),
    VENUS   (""),
    EARTH   (""),
    MARS    (""),
    JUPITER (""),
    SATURN  (""),
    URANUS  (""),
    NEPTUNE ("");

    private final String category;   
    
    BUSINESS(String val) {
        this.category = val;
        
    }
    private String mass() { return category; }

    

    
   
   
}