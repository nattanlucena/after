package ballons.com.after.model;

/**
 * Created by Nattan on 22/10/2015.
 */
public class NavDrawerItem {

    private String mTitle;
    private int mIcon;
    private String mCount = "0";
    // boolean to set visiblity of the counter
    private boolean isCounterVisible = false;

    public NavDrawerItem() {
    }

    public NavDrawerItem(int icon, String title) {
        mIcon = icon;
        mTitle = title;
    }

    public NavDrawerItem(int icon, String title, String count, boolean visible) {
        mIcon = icon;

        mTitle = title;
        mCount = count;
        isCounterVisible = visible;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public int getIcon() {
        return mIcon;
    }

    public void setIcon(int mIcon) {
        this.mIcon = mIcon;
    }

    public String getCount() {
        return mCount;
    }

    public void setCount(String mCount) {
        this.mCount = mCount;
    }

    public boolean isCounterVisible() {
        return isCounterVisible;
    }

    public void setIsCounterVisible(boolean isCounterVisible) {
        this.isCounterVisible = isCounterVisible;
    }

}
