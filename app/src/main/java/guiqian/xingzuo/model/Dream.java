package guiqian.xingzuo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jiangjingbo on 2017/7/10.
 */

public class Dream  {

    @SerializedName("result")
    @Expose
    private List<DreamResult> result = null;
    @SerializedName("error_code")
    @Expose
    private Integer errorCode;
    @SerializedName("reason")
    @Expose
    private String reason;

    public List<DreamResult> getResult() {
        return result;
    }

    public void setResult(List<DreamResult> result) {
        this.result = result;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
