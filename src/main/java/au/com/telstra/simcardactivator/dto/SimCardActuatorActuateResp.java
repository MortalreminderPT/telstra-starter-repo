package au.com.telstra.simcardactivator.dto;

import java.util.Objects;

public class SimCardActuatorActuateResp {
    Boolean success;

    public SimCardActuatorActuateResp() {
    }

    public SimCardActuatorActuateResp(Boolean success) {
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
        SimCardActuatorActuateResp that = (SimCardActuatorActuateResp) o;
        return Objects.equals(success, that.success);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(success);
    }

    @Override
    public String toString() {
        return "SimCardActuatorActuateResp{" +
                "success=" + success +
                '}';
    }
}