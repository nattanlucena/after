package ballons.com.after.model;

/**
 * Created by Nattan on 23/10/2015.
 */
public class User {
    private String mName;
    private String mEmail;
    private String mPassword;

    public User(){}

    public User(String name, String email, String password){
        this.mName = name;
        this.mEmail = email;
        this.mPassword = password;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "mName='" + mName + '\'' +
                ", mEmail='" + mEmail + '\'' +
                ", mPassword='" + mPassword + '\'' +
                '}';
    }
}
