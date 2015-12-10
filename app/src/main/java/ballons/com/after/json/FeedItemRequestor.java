package ballons.com.after.json;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;

import org.json.JSONObject;

import java.sql.Time;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import ballons.com.after.utils.UrlEndpoints;

/**
 * Created by nattanlucena on 12/11/15.
 */
public class FeedItemRequestor {

    public static JSONObject sendRequestFeedItemList(RequestQueue requestQueue, String URL) {
        JSONObject response = null;
        RequestFuture<JSONObject> requestFuture = RequestFuture.newFuture();
        //recebe o objeto json com os dados para popular a lista do Feed
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, requestFuture, requestFuture);
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

        return response;
    }
}
