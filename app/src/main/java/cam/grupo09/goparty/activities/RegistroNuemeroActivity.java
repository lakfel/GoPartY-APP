package cam.grupo09.goparty.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONObject;

import cam.grupo09.goparty.R;
import cam.grupo09.goparty.backGround.ConsultaWEB;
import cam.grupo09.goparty.backGround.WebListenerQuery;

public class RegistroNuemeroActivity extends AppCompatActivity implements WebListenerQuery{

    private EditText txtNumberReg;
    private EditText txtCorreoReg;
    private EditText txtNombreReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_nuemero);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtNumberReg = (EditText)findViewById(R.id.txtRegPhone);
        txtCorreoReg = (EditText)findViewById(R.id.txtRegCorreo);
        txtNombreReg = (EditText)findViewById(R.id.txtRegNombre);


    }


    public void primerRegistro(View view)
    {
        String phone =  txtNumberReg.getText().toString();
        String name = txtNombreReg.getText().toString();
        String correo = txtCorreoReg.getText().toString();
        if(phone.length()< 10 || !(correo.contains("@"))||name.length() == 0)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Oops, algo sucede con tus datos, no parecen estar bien.");
            builder.setTitle("Registro numérico");
            builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int wich) {
                    dialog.dismiss();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }
        else
        {
            SharedPreferences.Editor editor = MainActivity.sharedPreferences.edit();
            editor.putString(MainActivity.NUMBER_VALUE, phone);
            editor.putString(MainActivity.NAME_VALUE, name);
            editor.putString(MainActivity.EMAIL_VALUE, correo);
            editor.putBoolean(MainActivity.FIRST_TIME_VALUE,false);
            editor.commit();
            JSONObject usuario = new JSONObject();
            try
            {
                usuario.put("nombre", name);
                usuario.put("correo", correo);
                usuario.put("celular", phone);
                ConsultaWEB consulta = new ConsultaWEB(usuario,"https://gopartyserver.herokuapp.com/users/usuarios","POST",this,"registro");
                consulta.execute();
                startActivity(new Intent(this, MainActivity.class));
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void receive(JSONArray response, String query) {

    }
}
