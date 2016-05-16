package cam.grupo09.goparty.backGround;

/**
 * Created by Felipe on 17/04/2016.
 */
import android.os.AsyncTask;
import android.util.Log;


import org.json.JSONArray;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class ConsultaWEB extends AsyncTask<Void, Void, JSONArray>
{

    private JSONArray objetoJSONResp;
    private JSONObject objetoJSONEnv;
    private String urlPage;
    private String tipo;
    private WebListenerQuery ansewr;
    private String qquery;

    public ConsultaWEB(JSONObject objetoJSONEnv, String urlPage, String tipo, WebListenerQuery ansewr,String qquery)
    {
        Log.i("CONSULTANDO ", "Tipo " + tipo + " URL : " + urlPage);
        this.objetoJSONEnv = objetoJSONEnv;
        this.urlPage = urlPage;
        this.tipo = tipo;
        this.ansewr = ansewr;
        this.qquery = qquery;
    }

    @Override
    protected void onPreExecute()
    {


    }
    @Override
    protected JSONArray doInBackground(Void... b)
    {
        realizarConsulta();
        if(tipo.equalsIgnoreCase("GET"))
        ansewr.receive(objetoJSONResp,qquery);
        return objetoJSONResp;
    }
    @Override
    protected void onProgressUpdate(Void... p)
    {

    }

    public void realizarConsulta()
    {
        Log.i("CONSULTANDO   " ,  "Empezando");
        try
        {
            String jSonResponse = null;
            String jSonDta= null;

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            URL url;
            url = new URL(urlPage);

            try {
                urlConnection.disconnect();
            }
            catch (Exception a)
            {
                a.printStackTrace();
            }
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setUseCaches(false);
            urlConnection.setReadTimeout(10000);
            urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            urlConnection.setRequestProperty("Accept", "application/json; charset=UTF-8");
            urlConnection.setRequestProperty("Connection", "close");
            urlConnection.setRequestMethod(tipo);


            Log.i("CONSULTANDO   ", urlPage + "--------------  Configurado -- " + tipo);


            if(tipo.equalsIgnoreCase("POST"))
            {
                Log.i("CONSULTANDO   ", "POST");
                //urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

                urlConnection.connect();
                jSonDta = objetoJSONEnv.toString();
                Log.i("CONSULTANDO   ", jSonDta);
                OutputStream out = urlConnection.getOutputStream();
                if(out!= null)
                {
                    Log.i("CONSULTANDO   ", "FLUJO ABIERTO");
                    DataOutputStream bf = new DataOutputStream(out);
                    bf.write(jSonDta.getBytes());
                    int code = urlConnection.getResponseCode();
                    Log.i("CODIGO RESPE ", code + "");
                    Log.i("CODIGO RESPE ", urlConnection.getResponseMessage() + "");
                    bf.flush();
                    bf.close();


                }
                /*Writer writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(),"UTF-8"));
                writer.write(objetoJSONEnv.toString(1));
                writer.flush();
                writer.close();*/
            } else
            {
                //urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);
                urlConnection.connect();

                Log.i("CONSULTANDO ", "CODE " + urlConnection.getResponseCode());
                Log.i("CONSULTANDO ", "CODE " + urlConnection.getResponseMessage());

                Log.i("CONSULTANDO   ", "GET");

                InputStream inputStream =urlConnection.getInputStream();
                Log.i("CONSULTANDO   ", "GET 2");

                StringBuffer buffer = new StringBuffer();
                if (inputStream != null)
                {
                    Log.i("CONSULTANDO   " ,  "Buffer Creado");
                    reader = new BufferedReader(new InputStreamReader(inputStream));
                    String inputLine;
                    while ((inputLine = reader.readLine()) != null)
                    {
                        Log.i("CONSULTANDO   " ,  "LEYENDO    ");
                        buffer.append(inputLine + "\n");
                    }
                    if (buffer.length() != 0)
                    {
                        jSonResponse = buffer.toString();
                        Log.i("LEIDOOO   " , jSonResponse);
                        objetoJSONResp = new JSONArray(jSonResponse);

                    }
                    reader.close();
                }
                else
                    Log.i("CONTULTANDO ", "Respuesta vacia");
                Log.i("CONSULTANDO ", "CODE " +urlConnection.getResponseCode());
                Log.i("CONSULTANDO ", "CODE " +urlConnection.getResponseMessage());

            }



        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        Log.i("CONSULTANDO", "SALIENDOOOOO");

    }

    @Override
    protected void onPostExecute(JSONArray jsonArray) {
        super.onPostExecute(jsonArray);
        ansewr.receive(objetoJSONResp,qquery);
    }
}
