package ballons.com.after.model;

/**
 * Created by nattan on 10/27/15.
 */
public class MotelRoomItem {
    private String mName;
    private String mPriceRange;
    private String mRating;
    private String mThumb;
    private String mDescription;

    public MotelRoomItem(){}

    public MotelRoomItem(String mName, String mPriceRange, String mRating, String mThumb, String mDescription) {
        this.mName = mName;
        this.mPriceRange = mPriceRange;
        this.mRating = mRating;
        this.mThumb = mThumb;
        this.mDescription = mDescription;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getPriceRange() {
        return mPriceRange;
    }

    public void setPriceRange(String mPriceRange) {
        this.mPriceRange = mPriceRange;
    }

    public String getRating() {
        return mRating;
    }

    public void setRating(String mRating) {
        this.mRating = mRating;
    }

    public String getThumb() {
        return mThumb;
    }

    public void setThumb(String mThumb) {
        this.mThumb = mThumb;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
