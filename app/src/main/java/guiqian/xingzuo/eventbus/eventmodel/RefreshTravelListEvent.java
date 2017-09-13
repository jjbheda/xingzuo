package guiqian.xingzuo.eventbus.eventmodel;

/**
 * Created by hexiaofei on 16/9/27.
 */
public class RefreshTravelListEvent {

    private boolean flag = false;

    public RefreshTravelListEvent(boolean isRefresh) {
        flag = isRefresh;
    }

    public boolean isFlag() {
        return flag;
    }
}
