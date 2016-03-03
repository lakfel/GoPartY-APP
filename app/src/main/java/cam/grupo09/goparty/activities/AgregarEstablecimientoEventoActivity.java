package cam.grupo09.goparty.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cam.grupo09.goparty.Enumerables.TipoBebida;
import cam.grupo09.goparty.Enumerables.TipoMusica;
import cam.grupo09.goparty.R;
import cam.grupo09.goparty.mundo.Establecimiento;
import cam.grupo09.goparty.mundo.GoPartY;
import cam.grupo09.goparty.mundo.OpcionPropuesta;

public class AgregarEstablecimientoEventoActivity extends AppCompatActivity {

    private EditText txtNombre;
    private EditText txtDescripcion;
    private Spinner spTipoMusica;
    private Spinner spTipoBebidas;
    private ListView lstResultados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        lstResultados = (ListView)findViewById(R.id.lstResultadoBusqueda);
        lstResultados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view,
                                    int position2, long id) {
                final int position = position2;
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                if(lstResultados.getSelectedItem() != null)
                                {
                                    GoPartY.getInstance().getEventoActual().getEstablecimientosPropuestos()
                                            .add(new OpcionPropuesta<Establecimiento>((Establecimiento) lstResultados.getItemAtPosition(position)));
                                }
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setMessage("Quiere agregar el establecimiento " + lstResultados.getItemAtPosition(position).toString() + "?")
                        .setPositiveButton("Yes", dialogClickListener)
                            .setNegativeButton("No", dialogClickListener).show();

            }

        });

    }

    public void realizarBusqueda(View view)
    {
        ArrayList<Establecimiento> busqueda = GoPartY.getInstance().darBusquedaFiltrada(
                txtNombre.getText().toString(),txtDescripcion.getText().toString(),
                spTipoMusica.getSelectedItem().toString(),spTipoBebidas.getSelectedItem().toString(),0);

        ArrayAdapter<Establecimiento> adapter = new ArrayAdapter<Establecimiento>(this, R.layout.lista_item, R.id.label,busqueda);
        lstResultados.setAdapter(adapter);
    }

}
