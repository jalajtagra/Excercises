package com.ttnd.linksharing

/**
 * Created by ttnd on 30/6/16.
 */
enum Visibility {
    Public,
    Private

    public static final VISIBILITY_PUBLIC = "Public"
    public static final VISIBILITY_PRIVATE = "Private"

    static Visibility getVisibilityFromString(String str){
        if(str == VISIBILITY_PUBLIC){
            Visibility.Public
        }else if(str == VISIBILITY_PRIVATE){
            Visibility.Private
        }
    }

}