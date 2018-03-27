package com.example.ery.dinamicexpandablelistview;

/**
 * Created by Ery on 28.01.2018.
 */

public class DersProgramiProperties {

    String  DERS_ADI,DERS_KODU,BOL_AD,DERSLIK_GUN,DERS_SAAT,DERS_SAAT_BIT,DERSLIK_AD;

    public DersProgramiProperties() {
        super();
    }

    public DersProgramiProperties(String DERS_ADI, String DERS_KODU, String BOL_AD,
                                  String DERSLIK_GUN, String DERS_SAAT, String DERS_SAAT_BIT,
                                  String DERSLIK_AD) {
        this.DERS_ADI = DERS_ADI;
        this.DERS_KODU = DERS_KODU;
        this.BOL_AD = BOL_AD;
        this.DERSLIK_GUN = DERSLIK_GUN;
        this.DERS_SAAT = DERS_SAAT;
        this.DERS_SAAT_BIT = DERS_SAAT_BIT;
        this.DERSLIK_AD = DERSLIK_AD;
    }

    public String getDERS_ADI() {
        return DERS_ADI;
    }

    public void setDERS_ADI(String DERS_ADI) {
        this.DERS_ADI = DERS_ADI;
    }

    public String getDERS_KODU() {
        return DERS_KODU;
    }

    public void setDERS_KODU(String DERS_KODU) {
        this.DERS_KODU = DERS_KODU;
    }

    public String getBOL_AD() {
        return BOL_AD;
    }

    public void setBOL_AD(String BOL_AD) {
        this.BOL_AD = BOL_AD;
    }

    public String getDERSLIK_GUN() {
        return DERSLIK_GUN;
    }

    public void setDERSLIK_GUN(String DERSLIK_GUN) {
        this.DERSLIK_GUN = DERSLIK_GUN;
    }

    public String getDERS_SAAT() {
        return DERS_SAAT;
    }

    public void setDERS_SAAT(String DERS_SAAT) {
        this.DERS_SAAT = DERS_SAAT;
    }

    public String getDERS_SAAT_BIT() {
        return DERS_SAAT_BIT;
    }

    public void setDERS_SAAT_BIT(String DERS_SAAT_BIT) {
        this.DERS_SAAT_BIT = DERS_SAAT_BIT;
    }

    public String getDERSLIK_AD() {
        return DERSLIK_AD;
    }

    public void setDERSLIK_AD(String DERSLIK_AD) {
        this.DERSLIK_AD = DERSLIK_AD;
    }
}
