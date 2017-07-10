package guiqian.xingzuo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jiangjingbo on 2017/7/9.
 */
public class Destination {

    @SerializedName("result1")
    @Expose
    private Result result1;
    @SerializedName("error_code")
    @Expose
    private Integer errorCode;
    @SerializedName("reason")
    @Expose
    private String reason;

    public Result getResult() {
        return result1;
    }

    public void setResult(Result result) {
        this.result1 = result;
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