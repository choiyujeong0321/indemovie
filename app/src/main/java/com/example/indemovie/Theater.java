package com.example.indemovie;

public class Theater {

    private String THEATER_NM;
    private String ADDR;
    private String HOMEPAGE;
    private String LOC; //위치는 NGeoPoint 타입이다! (지도상에서 이용하기 편리하게!!)
    private String TEL;
    private String TIME;

    public String getTHEATER_NM() {
        return THEATER_NM;
    }

    public void setTHEATER_NM(String THEATER_NM) {
        this.THEATER_NM = THEATER_NM;
    }

    public String getADDR() {
        return ADDR;
    }

    public void setADDR(String ADDR) {
        this.ADDR = ADDR;
    }

    public String getHOMEPAGE() {
        return HOMEPAGE;
    }

    public void setHOMEPAGE(String HOMEPAGE) {
        this.HOMEPAGE = HOMEPAGE;
    }

    public String getLOC() {
        return LOC;
    }

    public void setLOC(String LOC) {
        this.LOC = LOC;
    }

    public String getTEL() {
        return TEL;
    }

    public void setTEL(String TEL) {
        this.TEL = TEL;
    }

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }
}
