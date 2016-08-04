package com.ttnd.linksharing

/**
 * Created by ttnd on 30/6/16.
 */
enum Seriouness {
    Serious,
    Very_Serious,
    Casual

    static final SERIOUSNESS_SERIOUS = "Serious"
    static final SERIOUSNESS_VERY_SERIOUS = "VerySerious"
    static final SERIOUSNESS_CASUAL = "Casual"

    static Seriouness getSeriousnessFromString(String str){
        if(str.equalsIgnoreCase(SERIOUSNESS_VERY_SERIOUS)){
            return Very_Serious
        }else if(str.equalsIgnoreCase(SERIOUSNESS_CASUAL)){
            return Casual
        }else{
            return Serious
        }
    }

}