package com.codinghavoc.warcardgame;
public enum FaceValue {
    ACE(1,"Ace"),
    KING(2,"King"),
    QUEEN(3,"Queen"),
    JACK(4,"Jack"),
    TEN(5,"Ten"),
    NINE(6,"Nine"),
    EIGHT(7,"Eight"),
    SEVEN(8,"Seven"),
    SIX(9,"Six"),
    FIVE(10,"Five"),
    FOUR(11,"Four"),
    THREE(12,"Three"),
    TWO(13,"Two");

    public final int rank;
    public final String face;

    private FaceValue(int r, String f){
        rank = r;
        face = f;
    }

    public void getValues(){
        // for()
    }

    public String toString(){
        return this.face + " " + this.rank;
    }

    public String getFace(){
        return this.face;
    }
}
