package co.edu.unipiloto.laboratorio_javamail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtCorreoRemitente, txtContrasenaRemitente, txtCorreoDestino, txtAsunto, txtMensaje;
    private Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Incialización de Atributos
        this.txtCorreoRemitente = (EditText) findViewById(R.id.txt_correo_remitente);
        this.txtContrasenaRemitente = (EditText) findViewById(R.id.txt_contrasena_remitente);
        this.txtCorreoDestino = (EditText) findViewById(R.id.txt_correo_destino);
        this.txtAsunto = (EditText) findViewById(R.id.txt_asunto);
        this.txtMensaje = (EditText) findViewById(R.id.txt_mensaje);
        this.btnEnviar = (Button) findViewById(R.id.btn_enviar);

        //Acción Boton
        this.btnEnviar.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == this.btnEnviar.getId()) {
            if (!this.txtCorreoDestino.getText().toString().equals("") &&
                    !this.txtAsunto.getText().toString().equals("") &&
                    !this.txtMensaje.getText().toString().equals("")) {
                if (this.txtCorreoRemitente.getText().toString().equals("") &&
                        this.txtContrasenaRemitente.getText().toString().equals("")) {
                    new JavaMail(MainActivity.this).execute(this.txtCorreoDestino.getText().toString(),
                            this.txtAsunto.getText().toString(),
                            this.txtMensaje.getText().toString());
                }else{
                    new JavaMail(MainActivity.this).execute(this.txtCorreoDestino.getText().toString(),
                            this.txtAsunto.getText().toString(),
                            this.txtMensaje.getText().toString(),
                            this.txtCorreoRemitente.getText().toString(),
                            this.txtContrasenaRemitente.getText().toString());
                }
            } else {
                this.txtCorreoDestino.setError("Campo Requerido");
                this.txtAsunto.setError("Campo Requerido");
                this.txtMensaje.setError("Campo Requerido");
            }
        }


    }
}