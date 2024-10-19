package api.broker;

public class Broker {
    private int brokerID;
    private String brokerName;
    private int policyName;

    public Broker(int brokerID, String brokerName, int policyName) {
        this.brokerID = brokerID;
        this.brokerName = brokerName;
        this.policyName = policyName;
    }

    public int getBrokerID() {
        return brokerID;
    }

    public void setBrokerID(int brokerID) {
        this.brokerID = brokerID;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public int getPolicyName() {
        return policyName;
    }

    public void setPolicyName(int policyName) {
        this.policyName = policyName;
    }

    @Override
    public String toString() {
        return "Broker [brokerID=" + brokerID + ", brokerName=" + brokerName + ", policyName=" + policyName + "]";
    }

    
}
