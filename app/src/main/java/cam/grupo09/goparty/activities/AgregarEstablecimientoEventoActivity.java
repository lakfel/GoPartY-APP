package cam.grupo09.goparty.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import cam.grupo09.goparty.Enumerables.TipoBebida;
import cam.grupo09.goparty.Enumerables.TipoMusica;
import cam.grupo09.goparty.R;
import cam.grupo09.goparty.mundo.Establecimiento;
import cam.grupo09.goparty.mundo.Evento;
import cam.grupo09.goparty.mundo.GoPartY;
import cam.grupo09.goparty.mundo.OpcionPropuesta;

public class AgregarEstablecimientoEventoActivity extends AppCompatActivity {

    private EditText txtNombre;
    private EditText txtDescripcion;
    private Spinner spTipoMusica;
    private Spinner spTipoBebidas;
    private ListView lstResultados;
    private int posSeleccionada;
    private ListView nn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        posSeleccionada = -1;
        setContentView(R.layout.activity_agregar_establecimiento_evento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtDescripcion = (EditText) findViewById(R.id.txtDescripcion);
        spTipoBebidas = (Spinner) findViewById(R.id.spTipoBebida);
        spTipoMusica = (Spinner) findViewById(R.id.spTipoMusica);
        TipoBebida[] values = TipoBebida.values();
        List<String> tbs = new ArrayList<String>();
        for (TipoBebida a : values) {
            tbs.add(a.toString());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tbs);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTipoBebidas.setAdapter(dataAdapter);

        TipoMusica[] values1 = TipoMusica.values();
        List<String> tbs1 = new ArrayList<String>();
        for (TipoMusica a : values1) {
            tbs1.add(a.toString());
        }
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tbs1);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTipoMusica.setAdapter(dataAdapter1);

        lstResultados = (ListView) findViewById(R.id.lstResultadoBusqueda);
        lstResultados.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lstResultados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view,
                                    int position2, long id) {

                //Todo desplegar la pantalla de información.
                posSeleccionada = position2;


                Log.d("Guardando numero", "Numero : " + posSeleccionada);
            }


        });

    }

    public void visitarEstablecimiento(View view) {
        if (posSeleccionada >= 0) {
            Intent n = new Intent(this, VerEstablecimientosActivity.class);
            n.putExtra("establecimiento", lstResultados.getItemAtPosition(posSeleccionada).toString());
            startActivity(n);
        }
    }

    public void agregarEstablecimientoAlEvento(View view) {
        if (posSeleccionada >= 0) {
            Establecimiento est = (Establecimiento) lstResultados.getItemAtPosition(posSeleccionada);
            Evento ev = GoPartY.getInstance().getEventoActual();
            if (ev != null) {
                if (!ev.estaEstablecimento(est.getNombre())) {
                    Log.d("Agregando ", est.getNombre());
                    ev.getEstablecimientosPropuestos().add(new OpcionPropuesta<Establecimiento>(est));

                    showDialog("Nuevo establecimiento", "Se agregó el establecimiento " + est.getNombre());
                } else {
                    showDialog("Nuevo establecimiento", "El establecimiento " + est.getNombre() + "ya se había propuesto en este evento");
                }
            }
        }
    }


    private void showDialog(String title, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(title);
        alertDialog.setCancelable(false);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        AlertDialog dialog = alertDialog.create();
        dialog.show();

    }

    public void realizarBusqueda(View view) {
        ArrayList<Establecimiento> busqueda = GoPartY.getInstance().darBusquedaFiltrada(
                txtNombre.getText().toString(), txtDescripcion.getText().toString(),
                spTipoMusica.getSelectedItem().toString(), spTipoBebidas.getSelectedItem().toString(), 0);

        ArrayAdapter<Establecimiento> adapter = new ArrayAdapter<Establecimiento>(this, R.layout.lista_item, R.id.label, busqueda);
        lstResultados.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GoPartY.getInstance().guardar();

    }
}
