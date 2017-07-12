
package guiqian.xingzuo.widget;

import java.io.Serializable;

public class BannerModel implements Serializable
{

    private final static long serialVersionUID = 1L;
    public long BannerId;
    public String BannerType = "";
    public String ImgUrl = "";
    public String LinkUrl = "";
    public String Title = "";
    public String SubTitle = "";
    public String Desc = "";

    public int resourceId = 0;

}
