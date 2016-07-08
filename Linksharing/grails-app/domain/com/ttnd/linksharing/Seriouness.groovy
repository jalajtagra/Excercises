package com.ttnd.linksharing

/**
 * Created by ttnd on 30/6/16.
 */
enum Seriouness {
    Serious,
    Very_Serious,
    Casual

    static final SERIOUSNESS_SERIOUS = "Serious"
    static final SERIOUSNESS_VERY_SERIOUS = "Very Serious"
    static final SERIOUSNESS_CASUAL = "CASUAL"

    static Seriouness getSeriousnessFromString(String str){
        if(str == SERIOUSNESS_VERY_SERIOUS){
            return Very_Serious
        }else if(str == SERIOUSNESS_CASUAL){
            return Casual
        }else{
            return Serious
        }
    }

}