package au.com.telstra.simcardactivator.model;

import java.util.Objects;

public class SimCard {
    Long id;
    String iccid;
    String customerEmail;
    boolean active;

    public SimCard() {
    }

    public SimCard(String iccid, String customerEmail, boolean active) {
        this.iccid = iccid;
        this.customerEmail = customerEmail;
        this.active = active;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimCard simCard = (SimCard) o;
        return active == simCard.active && Objects.equals(id, simCard.id) && Objects.equals(iccid, simCard.iccid) && Objects.equals(customerEmail, simCard.customerEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, iccid, customerEmail, active);
    }

    @Override
    public String toString() {
        return "SimCard{" +
                "id=" + id +
                ", iccid='" + iccid + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", active=" + active +
                '}';
    }
}
