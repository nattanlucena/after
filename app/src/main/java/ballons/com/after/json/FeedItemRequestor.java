package ballons.com.after.json;

import android.util.Log;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import ballons.com.after.app.AppController;
import ballons.com.after.utils.UrlEndpoints;

/**
 * Created by nattanlucena on 12/11/15.
 */
public class FeedItemRequestor {

    public static JSONObject sendRequestFeedItemList(RequestQueue requestQueue, String URL_FEED) {

        Cache mCache = AppController.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = mCache.get(URL_FEED);
        Log.d("entry", "" + entry);
        JSONObject response = null;

        if (entry != null) {
            try {
                String data = new String(entry.data, "UTF-8");

                try {
                    JSONObject feedData = new JSONObject(data);
                    if (feedData.length() == 0)
                        Log.d("length: ", "json vazio");

                    response = feedData;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            RequestFuture<JSONObject> requestFuture = RequestFuture.newFuture();

            //recebe o objeto json com os dados para popular a lista do Feed
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL_FEED, null, requestFuture, requestFuture);

            //adiciona o objeto recebido na fila
            requestQueue.add(jsonObjectRequest);

            try {
                response = requestFuture.get(3000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }

        return response;
    }
}
