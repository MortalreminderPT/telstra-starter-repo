package au.com.telstra.simcardactivator.dto;

import java.util.Objects;

public class SimCardActivateResp {
    Boolean success;
    Long id;

    public SimCardActivateResp() {
    }

    public SimCardActivateResp(Boolean success) {
        this.success = success;
    }

    public SimCardActivateResp(Boolean success, Long id) {
        this.success = success;
        this.id = id;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
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
        SimCardActivateResp that = (SimCardActivateResp) o;
        return Objects.equals(success, that.success) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, id);
    }

    @Override
    public String toString() {
        return "SimCardActivateResp{" +
                "success=" + success +
                ", id=" + id +
                '}';
    }
}