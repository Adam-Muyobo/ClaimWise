package api.reinsurer;

public class Reinsurer {

    private int reinsurerID;
    private String reinsurerName;
    private String reinsurerLocation;

    public Reinsurer(int reinsurerID, String reinsurerName, String reinsurerLocation) {
        this.reinsurerID = reinsurerID;
        this.reinsurerName = reinsurerName;
        this.reinsurerLocation = reinsurerLocation;
    }

    public int getReinsurerID() {
        return reinsurerID;
    }

    public void setReinsurerID(int reinsurerID) {
        this.reinsurerID = reinsurerID;
    }

    public String getReinsurerName() {
        return reinsurerName;
    }

    public void setReinsurerName(String reinsurerName) {
        this.reinsurerName = reinsurerName;
    }

    public String getReinsurerLocation() {
        return reinsurerLocation;
    }

    public void setReinsurerLocation(String reinsurerLocation) {
        this.reinsurerLocation = reinsurerLocation;
    }
}
