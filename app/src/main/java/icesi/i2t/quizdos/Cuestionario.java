package icesi.i2t.quizdos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import icesi.i2t.quizdos.model.Pregunta;

public class Cuestionario extends AppCompatActivity {

    public static final String RUTA = "";

    private FirebaseAuth auth;
    private FirebaseDatabase db;

    private TextView tv_pregunta;
    private TextView tv_enunciado;

    private RadioGroup radioGroup;
    private RadioButton radio_uno;
    private RadioButton radio_dos;
    private RadioButton radio_tres;
    private RadioButton radio_cuatro;

    private Button btn_enviar;

    private Pregunta p1;
    private Pregunta p2;
    private Pregunta p3;
    private Pregunta p4;
    private Pregunta p5;

    private int contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuestionario);

        tv_pregunta = findViewById(R.id.tv_pregunta);
        tv_enunciado = findViewById(R.id.tv_enunciado);

        radioGroup = findViewById(R.id.radio_group);
        radio_uno = findViewById(R.id.radio_uno);
        radio_dos = findViewById(R.id.radio_dos);
        radio_tres = findViewById(R.id.radio_tres);
        radio_cuatro = findViewById(R.id.radio_cuatro);

        btn_enviar = findViewById(R.id.btn_enviar);

        contador = 1;

        cargarPregunta(contador);

        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (radioGroup.getCheckedRadioButtonId() != -1) {
                    RadioButton aux = findViewById(radioGroup.getCheckedRadioButtonId());
                    enviarBaseDatos(contador, aux);
                }

                contador++;

                if (contador > 5) {
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    cargarPregunta(contador);
                }
            }
        });

    }

    private void inicializarPreguntas() {
        p1 = new Pregunta("bla", "", "", "", "");
        p2 = new Pregunta("bla", "", "", "", "");
        p3 = new Pregunta("bla", "", "", "", "");
        p4 = new Pregunta("bla", "", "", "", "");
        p5 = new Pregunta("bla", "", "", "", "");
    }

    private void cargarPregunta(int i) {
        inicializarPreguntas();
        switch (i) {
            case 1:
                tv_pregunta.setText("Pregunta " + i + ":");
                tv_enunciado.setText(p1.getEnunciado() + "");
                radio_uno.setText(p1.getOpcionUno() + "");
                radio_dos.setText(p1.getOpcionDos() + "");
                radio_tres.setText(p1.getOpcionTres() + "");
                radio_cuatro.setText(p1.getOpcionCuatro() + "");
                break;
            case 2:
                tv_pregunta.setText("Pregunta " + i + ":");
                tv_enunciado.setText(p2.getEnunciado() + "");
                radio_uno.setText(p2.getOpcionUno() + "");
                radio_dos.setText(p2.getOpcionDos() + "");
                radio_tres.setText(p2.getOpcionTres() + "");
                radio_cuatro.setText(p2.getOpcionCuatro() + "");
                break;
            case 3:
                tv_pregunta.setText("Pregunta " + i + ":");
                tv_enunciado.setText(p3.getEnunciado() + "");
                radio_uno.setText(p3.getOpcionUno() + "");
                radio_dos.setText(p3.getOpcionDos() + "");
                radio_tres.setText(p3.getOpcionTres() + "");
                radio_cuatro.setText(p3.getOpcionCuatro() + "");
                break;
            case 4:
                tv_pregunta.setText("Pregunta " + i + ":");
                tv_enunciado.setText(p4.getEnunciado() + "");
                radio_uno.setText(p4.getOpcionUno() + "");
                radio_dos.setText(p4.getOpcionDos() + "");
                radio_tres.setText(p4.getOpcionTres() + "");
                radio_cuatro.setText(p4.getOpcionCuatro() + "");
                break;
            case 5:
                tv_pregunta.setText("Pregunta " + i + ":");
                tv_enunciado.setText(p5.getEnunciado() + "");
                radio_uno.setText(p5.getOpcionUno() + "");
                radio_dos.setText(p5.getOpcionDos() + "");
                radio_tres.setText(p5.getOpcionTres() + "");
                radio_cuatro.setText(p5.getOpcionCuatro() + "");
                break;
            default:
                break;
        }
    }

    public void enviarBaseDatos(int i, RadioButton aux) {
        switch (i) {
            case 1:
                if (aux.getText().toString().equals(p1.getOpcionUno())) {

                } else if (aux.getText().toString().equals(p1.getOpcionDos())) {

                } else if (aux.getText().toString().equals(p1.getOpcionTres())) {

                } else if (aux.getText().toString().equals(p1.getOpcionCuatro())) {

                }
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            default:
                break;
        }
    }
}
