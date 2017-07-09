package guiqian.xingzuo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jiangjingbo on 2017/7/9.
 */

public class Result {

    @SerializedName("resultcode")
    @Expose
    private String resultcode;
    @SerializedName("error_code")
    @Expose
    private String errorCode;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("datetime")
    @Expose
    private String datetime;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("all")
    @Expose
    private String all;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("health")
    @Expose
    private String health;
    @SerializedName("love")
    @Expose
    private String love;
    @SerializedName("money")
    @Expose
    private String money;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("QFriend")
    @Expose
    private String qFriend;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("work")
    @Expose
    private String work;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getLove() {
        return love;
    }

    public void setLove(String love) {
        this.love = love;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getQFriend() {
        return qFriend;
    }

    public void setQFriend(String qFriend) {
        this.qFriend = qFriend;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

}
