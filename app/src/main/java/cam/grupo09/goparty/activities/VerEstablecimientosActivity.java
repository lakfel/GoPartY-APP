package cam.grupo09.goparty.activities;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cam.grupo09.goparty.R;
import cam.grupo09.goparty.mundo.GoPartY;
import cam.grupo09.goparty.persistencia.ManejadorPersistencia;
import cam.grupo09.goparty.persistenciaORMModelos.Establecimiento;
import cam.grupo09.goparty.persistenciaORMModelos.EstablecimientoBebida;
import cam.grupo09.goparty.persistenciaORMModelos.EstablecimientoMusica;
import cam.grupo09.goparty.persistenciaORMModelos.TipoBebida;
import cam.grupo09.goparty.persistenciaORMModelos.TipoMusica;

/**
 * Created by mike on 3/3/2016.
 */
public class VerEstablecimientosActivity extends AppCompatActivity {

    private ListView lvMusica;
    private ListView lvBebidas;
    private TextView lblNombre;
    private TextView lblCover;
    private TextView lblDescripcion;
    private TextView lblDireccion;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_establecimiento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvMusica = (ListView) findViewById(R.id.list_Musica_establecimiento);
        lvBebidas=(ListView) findViewById(R.id.list_Bebidas_establecimiento);
        lblNombre=(TextView) findViewById(R.id.lbl_nombre_establecimiento);
        lblCover=(TextView) findViewById(R.id.lbl_cover_establecimiento);
        lblDescripcion=(TextView) findViewById(R.id.lbl_descripcion_establecimiento);

        lblDireccion=(TextView) findViewById(R.id.lbl_direccion_establecimiento);

        Intent info=getIntent();
        Long id =info.getLongExtra("establecimiento",(long)0.0);
        Establecimiento est= Establecimiento.findById(Establecimiento.class, id);


        lblNombre.setText(est.getNombre());
        lblCover.setText(String.valueOf(est.getCostoCover()));
        lblDescripcion.setText(est.getDescripcion());
        lblDireccion.setText(est.getDireccion());

        List<EstablecimientoMusica> tiposM = EstablecimientoMusica.find(EstablecimientoMusica.class, "id_Establecimiento = ?", "" + est.getIdEstablecimiento());
        List<EstablecimientoBebida> tiposB = EstablecimientoMusica.find(EstablecimientoBebida.class, "id_Establecimiento = ?", "" + est.getIdEstablecimiento());

        List<TipoMusica> tiposMu = new ArrayList<TipoMusica>();
        List<TipoBebida> tiposBe = new ArrayList<TipoBebida>();

        for(int i = 0; i < tiposM.size(); i++)
        {
            List<TipoMusica> tiposMu2 = TipoMusica.find(TipoMusica.class, "tipo = ?", tiposM.get(i).getIdMusica());
            if(tiposMu2!=null)tiposMu.addAll(tiposMu2);
        }

        for(int i = 0; i < tiposB.size(); i++)
        {
            List<TipoBebida> tiposBe2 = TipoMusica.find(TipoBebida.class, "tipo = ?", tiposB.get(i).getIdBebida());
            if(tiposBe2!=null)tiposBe.addAll(tiposBe2);
        }

        ArrayAdapter adapterMusica = new ArrayAdapter<TipoMusica>(this, R.layout.lista_item,R.id.label,tiposMu);
        lvMusica.setAdapter(adapterMusica);
        ArrayAdapter adapterBebida = new ArrayAdapter<TipoBebida>(this,R.layout.lista_item,R.id.label,tiposBe);
        lvBebidas.setAdapter(adapterBebida);

    }

}
