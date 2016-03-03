package cam.grupo09.goparty.persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import cam.grupo09.goparty.mundo.Establecimiento;
import cam.grupo09.goparty.mundo.Evento;
import cam.grupo09.goparty.Enumerables.TipoBebida;
import cam.grupo09.goparty.Enumerables.TipoMusica;

/**
 * Created by Felipe on 02/03/2016.
 */
public class ManejadorPersistencia
{

    // A futuro se va a manejar con SQLite. Por ahora se hará con objetos serializables.
    private static final String NOMBRE_ARCHIVO = "persistenciaGoParty.data";

    private ArrayList<Evento> eventos;
    private ArrayList<Establecimiento> establecimientos;




    public ManejadorPersistencia ()
    {

    }

    public void cargarInfor()
    {
        try
        {
            File archivo = new File(NOMBRE_ARCHIVO);
            if(archivo.exists())
            {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
                eventos = (ArrayList<Evento>)ois.readObject();
                establecimientos = (ArrayList<Establecimiento>)ois.readObject();
                ois.close();
            }
            else
            {
                archivo.createNewFile();
                eventos = new ArrayList<Evento>();
                establecimientos = new ArrayList<Establecimiento>();
                poblarInfo();

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public void poblarInfo()
    {
        //Establecimientos
        String nombre = "La Cerveceria";
        String descripcion = "Un ligar enfocado más a la charla y la bebida que al baile. Perfecto para pasar tiempo con los amigos y descansar";
        String direccion = "Carrera 12A # 83 - 37";
        ArrayList<TipoMusica> tiposMusica = new ArrayList<TipoMusica>();
        tiposMusica.add(TipoMusica.ELECTRONICA);
        tiposMusica.add(TipoMusica.SALSA);
        tiposMusica.add(TipoMusica.REGGAETON);
        tiposMusica.add(TipoMusica.ROCK);
        tiposMusica.add(TipoMusica.VALLENATO);
        tiposMusica.add(TipoMusica.RANCHERA);
        ArrayList<TipoBebida> tiposBebida = new ArrayList<TipoBebida>();
        tiposBebida.add(TipoBebida.CERVEZA_ARTESANAL);
        tiposBebida.add(TipoBebida.CERVEZA_IMPORTADA);
        tiposBebida.add(TipoBebida.CERVEZA_NACIONAL);
        tiposBebida.add(TipoBebida.AGUARDIENTE);
        double cover = 15000;
        String url = "http://www.casadelacerveza.com.co/";
        establecimientos.add(new Establecimiento(nombre,descripcion,direccion,tiposMusica,tiposBebida,cover,url));

        nombre = "La Casa quiebra canto";
        descripcion = "Un ligar enfocado más a la charla y la bebida que al baile. Perfecto para pasar tiempo con los amigos y descansar";
        direccion = "Cra 5 No. 17 - 76";
        tiposMusica = new ArrayList<TipoMusica>();
        tiposMusica.add(TipoMusica.SALSA);
        tiposMusica.add(TipoMusica.MERENGUE);
        tiposBebida = new ArrayList<TipoBebida>();
        tiposBebida.add(TipoBebida.CERVEZA_IMPORTADA);
        tiposBebida.add(TipoBebida.CERVEZA_NACIONAL);
        tiposBebida.add(TipoBebida.AGUARDIENTE);
        tiposBebida.add(TipoBebida.WHISKY);
        tiposBebida.add(TipoBebida.RON);
        tiposBebida.add(TipoBebida.VODKA);
        cover = 15000;
        url = "http://www.quiebracanto.com/";
        establecimientos.add(new Establecimiento(nombre,descripcion,direccion,tiposMusica,tiposBebida,cover,url));

        nombre = "La Casa quiebra canto";
        descripcion = "Un ligar enfocado más a la charla y la bebida que al baile. Perfecto para pasar tiempo con los amigos y descansar";
        direccion = "Cra 5 No. 17 - 76";
        tiposMusica = new ArrayList<TipoMusica>();
        tiposMusica.add(TipoMusica.SALSA);
        tiposMusica.add(TipoMusica.MERENGUE);
        tiposBebida = new ArrayList<TipoBebida>();
        tiposBebida.add(TipoBebida.CERVEZA_IMPORTADA);
        tiposBebida.add(TipoBebida.CERVEZA_NACIONAL);
        tiposBebida.add(TipoBebida.AGUARDIENTE);
        tiposBebida.add(TipoBebida.WHISKY);
        tiposBebida.add(TipoBebida.RON);
        tiposBebida.add(TipoBebida.VODKA);
        cover = 15000;
        url = "http://www.quiebracanto.com/";
        establecimientos.add(new Establecimiento(nombre,descripcion,direccion,tiposMusica,tiposBebida,cover,url));

        nombre = "La Casa quiebra canto";
        descripcion = "Un ligar enfocado más a la charla y la bebida que al baile. Perfecto para pasar tiempo con los amigos y descansar";
        direccion = "Cra 5 No. 17 - 76";
        tiposMusica = new ArrayList<TipoMusica>();
        tiposMusica.add(TipoMusica.SALSA);
        tiposMusica.add(TipoMusica.MERENGUE);
        tiposBebida = new ArrayList<TipoBebida>();
        tiposBebida.add(TipoBebida.CERVEZA_IMPORTADA);
        tiposBebida.add(TipoBebida.CERVEZA_NACIONAL);
        tiposBebida.add(TipoBebida.AGUARDIENTE);
        tiposBebida.add(TipoBebida.WHISKY);
        tiposBebida.add(TipoBebida.RON);
        tiposBebida.add(TipoBebida.VODKA);
        cover = 15000;
        url = "http://www.quiebracanto.com/";
        establecimientos.add(new Establecimiento(nombre,descripcion,direccion,tiposMusica,tiposBebida,cover,url));

        nombre = "La Casa quiebra canto";
        descripcion = "Un ligar enfocado más a la charla y la bebida que al baile. Perfecto para pasar tiempo con los amigos y descansar";
        direccion = "Cra 5 No. 17 - 76";
        tiposMusica = new ArrayList<TipoMusica>();
        tiposMusica.add(TipoMusica.SALSA);
        tiposMusica.add(TipoMusica.MERENGUE);
        tiposBebida = new ArrayList<TipoBebida>();
        tiposBebida.add(TipoBebida.CERVEZA_IMPORTADA);
        tiposBebida.add(TipoBebida.CERVEZA_NACIONAL);
        tiposBebida.add(TipoBebida.AGUARDIENTE);
        tiposBebida.add(TipoBebida.WHISKY);
        tiposBebida.add(TipoBebida.RON);
        tiposBebida.add(TipoBebida.VODKA);
        cover = 15000;
        url = "http://www.quiebracanto.com/";
        establecimientos.add(new Establecimiento(nombre,descripcion,direccion,tiposMusica,tiposBebida,cover,url));

        nombre = "La Casa quiebra canto";
        descripcion = "Un ligar enfocado más a la charla y la bebida que al baile. Perfecto para pasar tiempo con los amigos y descansar";
        direccion = "Cra 5 No. 17 - 76";
        tiposMusica = new ArrayList<TipoMusica>();
        tiposMusica.add(TipoMusica.SALSA);
        tiposMusica.add(TipoMusica.MERENGUE);
        tiposBebida = new ArrayList<TipoBebida>();
        tiposBebida.add(TipoBebida.CERVEZA_IMPORTADA);
        tiposBebida.add(TipoBebida.CERVEZA_NACIONAL);
        tiposBebida.add(TipoBebida.AGUARDIENTE);
        tiposBebida.add(TipoBebida.WHISKY);
        tiposBebida.add(TipoBebida.RON);
        tiposBebida.add(TipoBebida.VODKA);
        cover = 15000;
        url = "http://www.quiebracanto.com/";
        establecimientos.add(new Establecimiento(nombre,descripcion,direccion,tiposMusica,tiposBebida,cover,url));

        nombre = "La Casa quiebra canto";
        descripcion = "Un ligar enfocado más a la charla y la bebida que al baile. Perfecto para pasar tiempo con los amigos y descansar";
        direccion = "Cra 5 No. 17 - 76";
        tiposMusica = new ArrayList<TipoMusica>();
        tiposMusica.add(TipoMusica.SALSA);
        tiposMusica.add(TipoMusica.MERENGUE);
        tiposBebida = new ArrayList<TipoBebida>();
        tiposBebida.add(TipoBebida.CERVEZA_IMPORTADA);
        tiposBebida.add(TipoBebida.CERVEZA_NACIONAL);
        tiposBebida.add(TipoBebida.AGUARDIENTE);
        tiposBebida.add(TipoBebida.WHISKY);
        tiposBebida.add(TipoBebida.RON);
        tiposBebida.add(TipoBebida.VODKA);
        cover = 15000;
        url = "http://www.quiebracanto.com/";
        establecimientos.add(new Establecimiento(nombre,descripcion,direccion,tiposMusica,tiposBebida,cover,url));

        nombre = "La Casa quiebra canto";
        descripcion = "Un ligar enfocado más a la charla y la bebida que al baile. Perfecto para pasar tiempo con los amigos y descansar";
        direccion = "Cra 5 No. 17 - 76";
        tiposMusica = new ArrayList<TipoMusica>();
        tiposMusica.add(TipoMusica.SALSA);
        tiposMusica.add(TipoMusica.MERENGUE);
        tiposBebida = new ArrayList<TipoBebida>();
        tiposBebida.add(TipoBebida.CERVEZA_IMPORTADA);
        tiposBebida.add(TipoBebida.CERVEZA_NACIONAL);
        tiposBebida.add(TipoBebida.AGUARDIENTE);
        tiposBebida.add(TipoBebida.WHISKY);
        tiposBebida.add(TipoBebida.RON);
        tiposBebida.add(TipoBebida.VODKA);
        cover = 15000;
        url = "http://www.quiebracanto.com/";
        establecimientos.add(new Establecimiento(nombre,descripcion,direccion,tiposMusica,tiposBebida,cover,url));

        nombre = "La Casa quiebra canto";
        descripcion = "Un ligar enfocado más a la charla y la bebida que al baile. Perfecto para pasar tiempo con los amigos y descansar";
        direccion = "Cra 5 No. 17 - 76";
        tiposMusica = new ArrayList<TipoMusica>();
        tiposMusica.add(TipoMusica.SALSA);
        tiposMusica.add(TipoMusica.MERENGUE);
        tiposBebida = new ArrayList<TipoBebida>();
        tiposBebida.add(TipoBebida.CERVEZA_IMPORTADA);
        tiposBebida.add(TipoBebida.CERVEZA_NACIONAL);
        tiposBebida.add(TipoBebida.AGUARDIENTE);
        tiposBebida.add(TipoBebida.WHISKY);
        tiposBebida.add(TipoBebida.RON);
        tiposBebida.add(TipoBebida.VODKA);
        cover = 15000;
        url = "http://www.quiebracanto.com/";
        establecimientos.add(new Establecimiento(nombre,descripcion,direccion,tiposMusica,tiposBebida,cover,url));

        nombre = "La Casa quiebra canto";
        descripcion = "Un ligar enfocado más a la charla y la bebida que al baile. Perfecto para pasar tiempo con los amigos y descansar";
        direccion = "Cra 5 No. 17 - 76";
        tiposMusica = new ArrayList<TipoMusica>();
        tiposMusica.add(TipoMusica.SALSA);
        tiposMusica.add(TipoMusica.MERENGUE);
        tiposBebida = new ArrayList<TipoBebida>();
        tiposBebida.add(TipoBebida.CERVEZA_IMPORTADA);
        tiposBebida.add(TipoBebida.CERVEZA_NACIONAL);
        tiposBebida.add(TipoBebida.AGUARDIENTE);
        tiposBebida.add(TipoBebida.WHISKY);
        tiposBebida.add(TipoBebida.RON);
        tiposBebida.add(TipoBebida.VODKA);
        cover = 15000;
        url = "http://www.quiebracanto.com/";
        establecimientos.add(new Establecimiento(nombre,descripcion,direccion,tiposMusica,tiposBebida,cover,url));

        nombre = "La Casa quiebra canto";
        descripcion = "Un ligar enfocado más a la charla y la bebida que al baile. Perfecto para pasar tiempo con los amigos y descansar";
        direccion = "Cra 5 No. 17 - 76";
        tiposMusica = new ArrayList<TipoMusica>();
        tiposMusica.add(TipoMusica.SALSA);
        tiposMusica.add(TipoMusica.MERENGUE);
        tiposBebida = new ArrayList<TipoBebida>();
        tiposBebida.add(TipoBebida.CERVEZA_IMPORTADA);
        tiposBebida.add(TipoBebida.CERVEZA_NACIONAL);
        tiposBebida.add(TipoBebida.AGUARDIENTE);
        tiposBebida.add(TipoBebida.WHISKY);
        tiposBebida.add(TipoBebida.RON);
        tiposBebida.add(TipoBebida.VODKA);
        cover = 15000;
        url = "http://www.quiebracanto.com/";
        establecimientos.add(new Establecimiento(nombre,descripcion,direccion,tiposMusica,tiposBebida,cover,url));

        nombre = "La Casa quiebra canto";
        descripcion = "Un ligar enfocado más a la charla y la bebida que al baile. Perfecto para pasar tiempo con los amigos y descansar";
        direccion = "Cra 5 No. 17 - 76";
        tiposMusica = new ArrayList<TipoMusica>();
        tiposMusica.add(TipoMusica.SALSA);
        tiposMusica.add(TipoMusica.MERENGUE);
        tiposBebida = new ArrayList<TipoBebida>();
        tiposBebida.add(TipoBebida.CERVEZA_IMPORTADA);
        tiposBebida.add(TipoBebida.CERVEZA_NACIONAL);
        tiposBebida.add(TipoBebida.AGUARDIENTE);
        tiposBebida.add(TipoBebida.WHISKY);
        tiposBebida.add(TipoBebida.RON);
        tiposBebida.add(TipoBebida.VODKA);
        cover = 15000;
        url = "http://www.quiebracanto.com/";
        establecimientos.add(new Establecimiento(nombre,descripcion,direccion,tiposMusica,tiposBebida,cover,url));

        nombre = "La Casa quiebra canto";
        descripcion = "Un ligar enfocado más a la charla y la bebida que al baile. Perfecto para pasar tiempo con los amigos y descansar";
        direccion = "Cra 5 No. 17 - 76";
        tiposMusica = new ArrayList<TipoMusica>();
        tiposMusica.add(TipoMusica.SALSA);
        tiposMusica.add(TipoMusica.MERENGUE);
        tiposBebida = new ArrayList<TipoBebida>();
        tiposBebida.add(TipoBebida.CERVEZA_IMPORTADA);
        tiposBebida.add(TipoBebida.CERVEZA_NACIONAL);
        tiposBebida.add(TipoBebida.AGUARDIENTE);
        tiposBebida.add(TipoBebida.WHISKY);
        tiposBebida.add(TipoBebida.RON);
        tiposBebida.add(TipoBebida.VODKA);
        cover = 15000;
        url = "http://www.quiebracanto.com/";
        establecimientos.add(new Establecimiento(nombre,descripcion,direccion,tiposMusica,tiposBebida,cover,url));

        nombre = "La Casa quiebra canto";
        descripcion = "Un ligar enfocado más a la charla y la bebida que al baile. Perfecto para pasar tiempo con los amigos y descansar";
        direccion = "Cra 5 No. 17 - 76";
        tiposMusica = new ArrayList<TipoMusica>();
        tiposMusica.add(TipoMusica.SALSA);
        tiposMusica.add(TipoMusica.MERENGUE);
        tiposBebida = new ArrayList<TipoBebida>();
        tiposBebida.add(TipoBebida.CERVEZA_IMPORTADA);
        tiposBebida.add(TipoBebida.CERVEZA_NACIONAL);
        tiposBebida.add(TipoBebida.AGUARDIENTE);
        tiposBebida.add(TipoBebida.WHISKY);
        tiposBebida.add(TipoBebida.RON);
        tiposBebida.add(TipoBebida.VODKA);
        cover = 15000;
        url = "http://www.quiebracanto.com/";
        establecimientos.add(new Establecimiento(nombre,descripcion,direccion,tiposMusica,tiposBebida,cover,url));

        nombre = "La Casa quiebra canto";
        descripcion = "Un ligar enfocado más a la charla y la bebida que al baile. Perfecto para pasar tiempo con los amigos y descansar";
        direccion = "Cra 5 No. 17 - 76";
        tiposMusica = new ArrayList<TipoMusica>();
        tiposMusica.add(TipoMusica.SALSA);
        tiposMusica.add(TipoMusica.MERENGUE);
        tiposBebida = new ArrayList<TipoBebida>();
        tiposBebida.add(TipoBebida.CERVEZA_IMPORTADA);
        tiposBebida.add(TipoBebida.CERVEZA_NACIONAL);
        tiposBebida.add(TipoBebida.AGUARDIENTE);
        tiposBebida.add(TipoBebida.WHISKY);
        tiposBebida.add(TipoBebida.RON);
        tiposBebida.add(TipoBebida.VODKA);
        cover = 15000;
        url = "http://www.quiebracanto.com/";
        establecimientos.add(new Establecimiento(nombre,descripcion,direccion,tiposMusica,tiposBebida,cover,url));

        nombre = "La Casa quiebra canto";
        descripcion = "Un ligar enfocado más a la charla y la bebida que al baile. Perfecto para pasar tiempo con los amigos y descansar";
        direccion = "Cra 5 No. 17 - 76";
        tiposMusica = new ArrayList<TipoMusica>();
        tiposMusica.add(TipoMusica.SALSA);
        tiposMusica.add(TipoMusica.MERENGUE);
        tiposBebida = new ArrayList<TipoBebida>();
        tiposBebida.add(TipoBebida.CERVEZA_IMPORTADA);
        tiposBebida.add(TipoBebida.CERVEZA_NACIONAL);
        tiposBebida.add(TipoBebida.AGUARDIENTE);
        tiposBebida.add(TipoBebida.WHISKY);
        tiposBebida.add(TipoBebida.RON);
        tiposBebida.add(TipoBebida.VODKA);
        cover = 15000;
        url = "http://www.quiebracanto.com/";
        establecimientos.add(new Establecimiento(nombre,descripcion,direccion,tiposMusica,tiposBebida,cover,url));


    }


}
