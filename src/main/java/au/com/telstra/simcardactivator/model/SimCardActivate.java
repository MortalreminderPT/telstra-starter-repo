package au.com.telstra.simcardactivator.model;

import java.util.Objects;

public class SimCardActivate {
    String iccid;
    String customerEmail;

    public SimCardActivate() {
    }

    public SimCardActivate(String iccid, String customerEmail) {
        this.iccid = iccid;
        this.customerEmail = customerEmail;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimCardActivate that = (SimCardActivate) o;
        return Objects.equals(iccid, that.iccid) && Objects.equals(customerEmail, that.customerEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iccid, customerEmail);
    }

    @Override
    public String toString() {
        return "SimCardActivate{" +
                "iccid='" + iccid + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                '}';
    }
}
