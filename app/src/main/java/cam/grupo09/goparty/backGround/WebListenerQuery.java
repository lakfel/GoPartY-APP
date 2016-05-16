package cam.grupo09.goparty.backGround;

import org.json.JSONArray;

/**
 * Created by Felipe on 18/04/2016.
 */
public interface WebListenerQuery
{

    public void receive(JSONArray response, String query);

}
