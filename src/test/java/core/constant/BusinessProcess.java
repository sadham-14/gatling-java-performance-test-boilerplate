package core.constant;

public enum BusinessProcess {
    BP1("BP1: Add Product"),
    BP2("BP2: Update Product"),
    BP3("BP3: Get All Products");

    private final String businessProcessFullName;

    BusinessProcess(String businessProcessFullName) {
        this.businessProcessFullName = businessProcessFullName;
    }

    public String getBusinessProcessFullName() {
        return businessProcessFullName;
    }
}
