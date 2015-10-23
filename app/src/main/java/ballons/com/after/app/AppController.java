package ballons.com.after.app;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import ballons.com.after.volley.LruBitmapCache;

/**
 * Created by Nattan on 23/10/2015.
 *
 * This is a singleton class which initializes global instances of required classes.
 * All the objects related to volley are initialized here.
 */
public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    LruBitmapCache mLruBitmapCache;

    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public ImageLoader getImageLoader(){
        getRequestQueue();
        if( mImageLoader == null){
            getLruBitmapCache();
            mImageLoader = new ImageLoader(this.mRequestQueue, mLruBitmapCache);
        }

        return mImageLoader;
    }

    public LruBitmapCache getLruBitmapCache(){
        if( mLruBitmapCache == null)
            mLruBitmapCache = new LruBitmapCache();

        return mLruBitmapCache;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag){
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req){
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPedingRequests(Object tag){
        if( mRequestQueue != null)
                mRequestQueue.cancelAll(tag);
    }
}
