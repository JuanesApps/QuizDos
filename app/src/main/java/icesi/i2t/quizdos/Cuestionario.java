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
import com.google.gson.Gson;

import java.io.IOException;

import icesi.i2t.quizdos.model.Pregunta;
import icesi.i2t.quizdos.model.WEBUtilDomi;

public class Cuestionario extends AppCompatActivity {

    public static final String PREGUNTA_UNO = "https://quizdos-e269.firebaseio.com/PreguntaUno";
    public static final String PREGUNTA_DOS = "https://quizdos-e269.firebaseio.com/PreguntaDos";
    public static final String PREGUNTA_TRES = "https://quizdos-e269.firebaseio.com/PreguntaTres";
    public static final String PREGUNTA_CUATRO = "https://quizdos-e269.firebaseio.com/PreguntaCuatro";
    public static final String PREGUNTA_CINCO = "https://quizdos-e269.firebaseio.com/PreguntaCinco";

    private FirebaseDatabase db;
    private FirebaseAuth auth;

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

        //db = FirebaseDatabase.getInstance(); //Falta inicializar
        //auth = FirebaseAuth.getInstance();

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
                    btn_enviar.setText("Enviar");
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
        p1 = new Pregunta("E1", "O1", "O2", "O3", "O4");
        p2 = new Pregunta("E2", "O1", "O2", "O3", "O4");
        p3 = new Pregunta("E3", "O1", "O2", "O3", "O4");
        p4 = new Pregunta("E4", "O1", "O2", "O3", "O4");
        p5 = new Pregunta("E5", "O1", "O2", "O3", "O4");
    }

    private void setTextPreguntasToSwitch(int i, Pregunta p) {
        tv_pregunta.setText("Pregunta " + i + ":");
        tv_enunciado.setText(p.getEnunciado() + "");
        radio_uno.setText(p.getOpcionUno() + "");
        radio_dos.setText(p.getOpcionDos() + "");
        radio_tres.setText(p.getOpcionTres() + "");
        radio_cuatro.setText(p.getOpcionCuatro() + "");
    }

    private void cargarPregunta(int i) {
        inicializarPreguntas();
        switch (i) {
            case 1:
                setTextPreguntasToSwitch(i, p1);
                break;
            case 2:
                setTextPreguntasToSwitch(i, p2);
                break;
            case 3:
                setTextPreguntasToSwitch(i, p3);
                break;
            case 4:
                setTextPreguntasToSwitch(i, p4);
                break;
            case 5:
                setTextPreguntasToSwitch(i, p5);
                break;
            default:
                break;
        }
    }

    public void compareToEnviarBD(int i, final RadioButton aux, Pregunta p, final String ruta) {
        if (aux.getText().toString().equals(p.getOpcionUno()) || aux.getText().toString().equals(p.getOpcionDos()) || aux.getText().toString().equals(p.getOpcionTres()) || aux.getText().toString().equals(p.getOpcionCuatro())) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Gson g = new Gson();
                        WEBUtilDomi.JsonByPOSTrequest(ruta, g.toJson(aux.getText().toString()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public void enviarBaseDatos(int i, final RadioButton aux) {
        switch (i) {
            case 1:
                compareToEnviarBD(i, aux, p1, PREGUNTA_UNO);
                break;
            case 2:
                compareToEnviarBD(i, aux, p2, PREGUNTA_DOS);
                break;
            case 3:
                compareToEnviarBD(i, aux, p3, PREGUNTA_TRES);
                break;
            case 4:
                compareToEnviarBD(i, aux, p4, PREGUNTA_CUATRO);
                break;
            case 5:
                compareToEnviarBD(i, aux, p5, PREGUNTA_CINCO);
                break;
            default:
                break;
        }
    }
}
