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
    private static final String NOMBRE_ARCHIVO = "persistenciaGoPartyF.txt";

    private ArrayList<Evento> eventos;
    private ArrayList<Establecimiento> establecimientos;




    public ManejadorPersistencia ()
    {
        cargarInfor();
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

                eventos = new ArrayList<Evento>();
                establecimientos = new ArrayList<Establecimiento>();
                poblarInfo();

            }
        }
        catch (Exception e)
        {
            eventos = new ArrayList<Evento>();
            establecimientos = new ArrayList<Establecimiento>();
            poblarInfo();
            e.printStackTrace();
        }
    }

    public ArrayList<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(ArrayList<Evento> eventos) {
        this.eventos = eventos;
    }

    public ArrayList<Establecimiento> getEstablecimientos() {
        return establecimientos;
    }

    public void setEstablecimientos(ArrayList<Establecimiento> establecimientos) {
        this.establecimientos = establecimientos;
    }


    public Establecimiento darEtablecimientoNombre(String nombre)
    {
        for (Establecimiento a: establecimientos  )
        {
            if(a.getNombre().equals(nombre))
                return a;
        }
        return null;
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

        nombre = "Bogota Beer Company";
        descripcion = "Un lugar perfecto para tener una buena comida acompañada de una buena cerveza y disfrutar con los amigos";
        direccion = "Cra 7 No. 32 - 16";
        tiposMusica = new ArrayList<TipoMusica>();
        tiposMusica.add(TipoMusica.ROCK);
        tiposBebida = new ArrayList<TipoBebida>();
        tiposBebida.add(TipoBebida.CERVEZA_ARTESANAL);
        tiposBebida.add(TipoBebida.CERVEZA_NACIONAL);
        tiposBebida.add(TipoBebida.TEQUILA);
        tiposBebida.add(TipoBebida.WHISKY);
        tiposBebida.add(TipoBebida.RON);
        tiposBebida.add(TipoBebida.VODKA);
        cover = 0;
        url = "http://www.http://bogotabeercompany.com/";
        establecimientos.add(new Establecimiento(nombre,descripcion,direccion,tiposMusica,tiposBebida,cover,url));

        nombre = "BAUM";
        descripcion = "Uno de los mejores lugares techno en América Latina, genial para salidas grupales";
        direccion = "Calle 33 No. 6 - 24";
        tiposMusica = new ArrayList<TipoMusica>();
        tiposMusica.add(TipoMusica.ELECTRONICA);
        tiposMusica.add(TipoMusica.HOUSE);
        tiposBebida = new ArrayList<TipoBebida>();
        tiposBebida.add(TipoBebida.CERVEZA_IMPORTADA);
        tiposBebida.add(TipoBebida.CERVEZA_NACIONAL);
        tiposBebida.add(TipoBebida.AGUARDIENTE);
        tiposBebida.add(TipoBebida.WHISKY);
        tiposBebida.add(TipoBebida.RON);
        tiposBebida.add(TipoBebida.VODKA);
        tiposBebida.add(TipoBebida.TEQUILA);
        cover = 10000;
        url = "no disponible";
        establecimientos.add(new Establecimiento(nombre,descripcion,direccion,tiposMusica,tiposBebida,cover,url));

        nombre = "Trementina Café Galería Bar";
        descripcion = "Lugar para relajarse y charlar, Volumen de musica apropiado para platica, decoracion muy artistica.";
        direccion = "Cra 24 No. 37 - 42";
        tiposMusica = new ArrayList<TipoMusica>();
        tiposMusica.add(TipoMusica.HOUSE);
        tiposMusica.add(TipoMusica.ROCK);
        tiposMusica.add(TipoMusica.POP);
        tiposBebida = new ArrayList<TipoBebida>();
        tiposBebida.add(TipoBebida.CERVEZA_IMPORTADA);
        tiposBebida.add(TipoBebida.CERVEZA_NACIONAL);
        tiposBebida.add(TipoBebida.AGUARDIENTE);
        tiposBebida.add(TipoBebida.WHISKY);
        tiposBebida.add(TipoBebida.RON);
        tiposBebida.add(TipoBebida.VODKA);
        tiposBebida.add(TipoBebida.COCKTAIL_CON_LICOR);
        tiposBebida.add(TipoBebida.COCKTAIL_SIN_LICOR);
        cover = 0;
        url = "https://www.facebook.com/pages/Trementina/208710725816605";
        establecimientos.add(new Establecimiento(nombre,descripcion,direccion,tiposMusica,tiposBebida,cover,url));

        nombre = "Latora 4 Brazos";
        descripcion = "Buen lugar para ir de fiesta, con buena actitud, ambiente underground.";
        direccion = "Cra 5 No. 17 - 76";
        tiposMusica = new ArrayList<TipoMusica>();
        tiposMusica.add(TipoMusica.SALSA);
        tiposMusica.add(TipoMusica.MERENGUE);
        tiposMusica.add(TipoMusica.ELECTRONICA);
        tiposMusica.add(TipoMusica.REGGAETON);
        tiposMusica.add(TipoMusica.POP);
        tiposBebida = new ArrayList<TipoBebida>();
        tiposBebida.add(TipoBebida.CERVEZA_NACIONAL);
        tiposBebida.add(TipoBebida.AGUARDIENTE);
        tiposBebida.add(TipoBebida.WHISKY);
        tiposBebida.add(TipoBebida.RON);
        tiposBebida.add(TipoBebida.VODKA);
        tiposBebida.add(TipoBebida.TEQUILA);
        cover = 0;
        url = "https://www.facebook.com/latorabar";
        establecimientos.add(new Establecimiento(nombre,descripcion,direccion,tiposMusica,tiposBebida,cover,url));

        nombre = "Radio Transistor";
        descripcion = "Caracterizado por su buena sleccion de misica Indie";
        direccion = "Calle 40B No. 40- 34";
        tiposMusica = new ArrayList<TipoMusica>();
        tiposMusica.add(TipoMusica.ROCK);
        tiposBebida = new ArrayList<TipoBebida>();
        tiposBebida.add(TipoBebida.CERVEZA_IMPORTADA);
        tiposBebida.add(TipoBebida.CERVEZA_NACIONAL);
        tiposBebida.add(TipoBebida.WHISKY);
        tiposBebida.add(TipoBebida.VODKA);
        cover = 0;
        url = "https://www.facebook.com/RadioTransistorBar";
        establecimientos.add(new Establecimiento(nombre,descripcion,direccion,tiposMusica,tiposBebida,cover,url));

        nombre = "Theatron";
        descripcion = "Multiples ambientes, todos con su diferente tipo de misica, Barra libre, Gay Friendly";
        direccion = "Calle 58 No. 10 - 32";
        tiposMusica = new ArrayList<TipoMusica>();
        tiposMusica.add(TipoMusica.SALSA);
        tiposMusica.add(TipoMusica.MERENGUE);
        tiposMusica.add(TipoMusica.ELECTRONICA);
        tiposMusica.add(TipoMusica.ROCK);
        tiposMusica.add(TipoMusica.RANCHERA);
        tiposMusica.add(TipoMusica.REGGAETON);
        tiposMusica.add(TipoMusica.SALSA);
        tiposMusica.add(TipoMusica.VALLENATO);
        tiposMusica.add(TipoMusica.BACHATA);
        tiposMusica.add(TipoMusica.HOUSE);
        tiposBebida = new ArrayList<TipoBebida>();
        tiposBebida.add(TipoBebida.AGUARDIENTE);
        tiposBebida.add(TipoBebida.WHISKY);
        tiposBebida.add(TipoBebida.RON);
        tiposBebida.add(TipoBebida.VODKA);
        tiposBebida.add(TipoBebida.TEQUILA);
        tiposBebida.add(TipoBebida.BRANDY);
        tiposBebida.add(TipoBebida.GINEBRA);
        cover = 30000;
        url = "http://www.portaltheatron.co/";
        establecimientos.add(new Establecimiento(nombre,descripcion,direccion,tiposMusica,tiposBebida,cover,url));

        nombre = "El Candelario";
        descripcion = "Con ambiente underground musica de todo tipo";
        direccion = "Cra 5 No. 13 - 14";
        tiposMusica = new ArrayList<TipoMusica>();
        tiposMusica.add(TipoMusica.CROSS_OVER);;
        tiposBebida = new ArrayList<TipoBebida>();
        tiposBebida.add(TipoBebida.CERVEZA_NACIONAL);
        tiposBebida.add(TipoBebida.AGUARDIENTE);
        tiposBebida.add(TipoBebida.WHISKY);
        tiposBebida.add(TipoBebida.RON);
        tiposBebida.add(TipoBebida.VODKA);
        cover = 10000;
        url = "https://www.facebook.com/pages/El-Candelario-Bar/410566985642922";
        establecimientos.add(new Establecimiento(nombre,descripcion,direccion,tiposMusica,tiposBebida,cover,url));

        nombre = "La Villa";
        descripcion = " ";
        direccion = "Cra 14 No. 83 - 56";
        tiposMusica = new ArrayList<TipoMusica>();
        tiposMusica.add(TipoMusica.SALSA);
        tiposMusica.add(TipoMusica.MERENGUE);
        tiposMusica.add(TipoMusica.ROCK);
        tiposMusica.add(TipoMusica.ELECTRONICA);
        tiposMusica.add(TipoMusica.HOUSE);
        tiposBebida = new ArrayList<TipoBebida>();
        tiposBebida.add(TipoBebida.CERVEZA_IMPORTADA);
        tiposBebida.add(TipoBebida.CERVEZA_NACIONAL);
        tiposBebida.add(TipoBebida.AGUARDIENTE);
        tiposBebida.add(TipoBebida.WHISKY);
        tiposBebida.add(TipoBebida.RON);
        tiposBebida.add(TipoBebida.VODKA);
        tiposBebida.add(TipoBebida.TEQUILA);
        cover = 20000;
        url = "http://www.lavillabogota.com/";
        establecimientos.add(new Establecimiento(nombre,descripcion,direccion,tiposMusica,tiposBebida,cover,url));

        nombre = "Céntrico";
        descripcion = "Lugar excelente para disfrutar de una buena comida y tambien baile.";
        direccion = "Cra 7 No. 32 - 16";
        tiposMusica = new ArrayList<TipoMusica>();
        tiposMusica.add(TipoMusica.CROSS_OVER);
        tiposBebida = new ArrayList<TipoBebida>();
        tiposBebida.add(TipoBebida.CERVEZA_IMPORTADA);
        tiposBebida.add(TipoBebida.CERVEZA_NACIONAL);
        tiposBebida.add(TipoBebida.COCKTAIL_CON_LICOR);
        tiposBebida.add(TipoBebida.COCKTAIL_SIN_LICOR);
        tiposBebida.add(TipoBebida.RON);
        tiposBebida.add(TipoBebida.VODKA);
        tiposBebida.add(TipoBebida.WHISKY);
        cover = 0;
        url = "http://www.centrico.co/";
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

        nombre = "Joshua cafe pub restaurante";
        descripcion = "Pub estilo europeo, buen lugar para buena platica y cantar clasicos de Rock";
        direccion = "Cra 5 No. 17 - 76";
        tiposMusica = new ArrayList<TipoMusica>();
        tiposMusica.add(TipoMusica.ROCK);
        tiposBebida = new ArrayList<TipoBebida>();
        tiposBebida.add(TipoBebida.CERVEZA_IMPORTADA);
        tiposBebida.add(TipoBebida.CERVEZA_NACIONAL);
        tiposBebida.add(TipoBebida.WHISKY);
        tiposBebida.add(TipoBebida.RON);
        tiposBebida.add(TipoBebida.VODKA);
        cover = 0;
        url = "http://joshuacafebar.com/web15/";
        establecimientos.add(new Establecimiento(nombre,descripcion,direccion,tiposMusica,tiposBebida,cover,url));

        nombre = "Palos De Moguer";
        descripcion = "Cerveceria lugar para platicar y disfrutar de excelente comida rapida.";
        direccion = "Gran estacion, Salitre park";
        tiposMusica = new ArrayList<TipoMusica>();
        tiposMusica.add(TipoMusica.CROSS_OVER);
        tiposBebida = new ArrayList<TipoBebida>();
        tiposBebida.add(TipoBebida.CERVEZA_IMPORTADA);
        tiposBebida.add(TipoBebida.CERVEZA_NACIONAL);
        tiposBebida.add(TipoBebida.WHISKY);
        tiposBebida.add(TipoBebida.RON);
        tiposBebida.add(TipoBebida.VODKA);
        cover = 0;
        url = "https://www.facebook.com/palosdemoguerpaginaoficial/";
        establecimientos.add(new Establecimiento(nombre,descripcion,direccion,tiposMusica,tiposBebida,cover,url));

        nombre = "Hotel V";
        descripcion = "Lugar elegante para salir en grupo o en pareja a bailar. ";
        direccion = "Calle 84";
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
        cover = 0;
        url = "http://www.quiebracanto.com/";
        establecimientos.add(new Establecimiento(nombre,descripcion,direccion,tiposMusica,tiposBebida,cover,url));

        nombre = "Armando Records";
        descripcion = "Excelente lugar para disfrutar de musica electronica, bueno para ir en grupos.";
        direccion = "Calle 85 No. 14-46";
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
