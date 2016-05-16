package cam.grupo09.goparty.backGround;

import org.json.JSONArray;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by Felipe on 18/04/2016.
 */
public class ActualizarBD implements Runnable, WebListenerQuery
{
    private final static int INTERVAL = 1000*60*2;
    private Handler handler;

    public ActualizarBD()
    {
        this.handler =  new Handler()
        {
            @Override
            public void close()
            {

            }

            @Override
            public void flush()
            {

            }

            @Override
            public void publish(LogRecord record) {

            }
        };
    }

    public void run()
    {
        try
        {
            //Establecimientos
            ConsultaWEB consultaWEB = new ConsultaWEB(null,"https://gopartyserver.herokuapp.com/goparty/establecimientos","GET",this,"establecimiento");
            consultaWEB.execute();


        }
        catch (Exception e)
        {

        }
    }

    @Override
    public void receive(JSONArray response, String query)
    {
        if(query.equalsIgnoreCase("establecimiento"))
        {

        }
        else if(query.equalsIgnoreCase("eventos"))
        {

        }
        else if(query.equalsIgnoreCase("tiposmusica"))
        {

        }
        else if(query.equalsIgnoreCase("tiposbebida"))
        {

        }

    }
}
