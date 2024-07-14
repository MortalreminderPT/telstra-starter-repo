package au.com.telstra.simcardactivator.dto;

import java.util.Objects;

public class SimCardActivateResp {
    Boolean success;

    public SimCardActivateResp() {
    }

    public SimCardActivateResp(Boolean success) {
        this.success = success;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimCardActivateResp that = (SimCardActivateResp) o;
        return Objects.equals(success, that.success);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(success);
    }

    @Override
    public String toString() {
        return "SimCardActivateResp{" +
                "success=" + success +
                '}';
    }
}