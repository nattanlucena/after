package ballons.com.after.json;

import ballons.com.after.app.AppController;
import ballons.com.after.utils.UrlEndpoints;

/**
 * Created by nattanlucena on 12/11/15.
 */
public class EndPoints {

    //url to get all the items of FeedList in HomeFragment
    public static String getRequestUrl(int limit) {

        return UrlEndpoints.URL_FEED_LIST
                + UrlEndpoints.URL_CHAR_QUESTION
                + UrlEndpoints.URL_PARAM_API_KEY + AppController.API_KEY_FEED_LIST
                + UrlEndpoints.URL_CHAR_AMEPERSAND
                + UrlEndpoints.URL_PARAM_LIMIT + limit;
    }

}
