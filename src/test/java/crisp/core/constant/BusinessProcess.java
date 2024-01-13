package crisp.core.constant;

public enum BusinessProcess {
    BP1("BP1: Application Environment Creation Request Submission"),
    BP2("BP2: Application Environment Creation Request Approval"),
    BP3("BP3: View all Service Requests");

    private final String businessProcessFullName;

    BusinessProcess(String businessProcessFullName) {
        this.businessProcessFullName = businessProcessFullName;
    }

    public String getBusinessProcessFullName() {
        return businessProcessFullName;
    }
}
