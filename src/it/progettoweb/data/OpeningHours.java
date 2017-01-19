package it.progettoweb.data;

/**
 * Class which stores opening hours data
 * @author Luca, Riccardo, Mario
 */
public class OpeningHours {
    private String mon;
    private String tue;
    private String wed;
    private String thu;
    private String fri;
    private String sat;
    private String sun;

    /**
     * @return the monday opening
     */
    public String getMon() {
        return mon;
    }

    /**
     * @param mon the monday opening to set
     */
    public void setMon(String mon) {
        this.mon = mon;
    }

    /**
     * @return the tuesday opening
     */
    public String getTue() {
        return tue;
    }

    /**
     * @param tue the tuesday opening to set
     */
    public void setTue(String tue) {
        this.tue = tue;
    }

    /**
     * @return the wednesday opening
     */
    public String getWed() {
        return wed;
    }

    /**
     * @param wed the wednesday opening to set
     */
    public void setWed(String wed) {
        this.wed = wed;
    }

    /**
     * @return the thursday opening
     */
    public String getThu() {
        return thu;
    }

    /**
     * @param thu the thursday opening to set
     */
    public void setThu(String thu) {
        this.thu = thu;
    }

    /**
     * @return the friday opening
     */
    public String getFri() {
        return fri;
    }

    /**
     * @param fri the friday opening to set
     */
    public void setFri(String fri) {
        this.fri = fri;
    }

    /**
     * @return the saturday opening
     */
    public String getSat() {
        return sat;
    }

    /**
     * @param sat the saturday opening to set
     */
    public void setSat(String sat) {
        this.sat = sat;
    }

    /**
     * @return the sunday opening
     */
    public String getSun() {
        return sun;
    }

    /**
     * @param sun the sunday opening to set
     */
    public void setSun(String sun) {
        this.sun = sun;
    }
}
