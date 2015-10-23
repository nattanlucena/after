package ballons.com.after.model;

/**
 * Created by Nattan on 23/10/2015.
 */
public class FeedItem {

    private int mId;
    private String mName, mStatus, mImage, mProfilePic, mTimeStamp, mUrl;

    public FeedItem(){}

    public FeedItem(int mId, String mName, String mStatus, String mImage, String mProfilePic, String mTimeStamp, String mUrl) {
        this.mId = mId;
        this.mName = mName;
        this.mStatus = mStatus;
        this.mImage = mImage;
        this.mProfilePic = mProfilePic;
        this.mTimeStamp = mTimeStamp;
        this.mUrl = mUrl;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String mImage) {
        this.mImage = mImage;
    }

    public String getProfilePic() {
        return mProfilePic;
    }

    public void setProfilePic(String mProfilePic) {
        this.mProfilePic = mProfilePic;
    }

    public String getTimeStamp() {
        return mTimeStamp;
    }

    public void setTimeStamp(String mTimeStamp) {
        this.mTimeStamp = mTimeStamp;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}
