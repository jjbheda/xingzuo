package guiqian.xingzuo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jiangjingbo on 2017/8/28.
 */

public class PairModel {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("content1")
    @Expose
    private String content1;
    @SerializedName("content1")
    @Expose
    private String content2;
}
