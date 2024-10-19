package api.ClubAssoc;

public class ClubAssoc {

    private String caName;

    public ClubAssoc(String caName) {
        this.caName = caName;
    }

    public String getCaName() {
        return caName;
    }

    public void setCaName(String caName) {
        this.caName = caName;
    }

    @Override
    public String toString() {
        return "ClubAssoc [caName=" + caName + "]";
    }

    
}
