package icesi.i2t.quizdos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import icesi.i2t.quizdos.model.Pregunta;
import icesi.i2t.quizdos.model.WEBUtilDomi;

public class Estadisticas extends AppCompatActivity {

    private Spinner spinner_preguntas;
    private ArrayAdapter<CharSequence> adapter;

    private int opcionA;
    private int opcionB;
    private int opcionC;
    private int opcionD;
    private int totalOpciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        spinner_preguntas = findViewById(R.id.spinner_preguntas);
        adapter = ArrayAdapter.createFromResource(this, R.array.preguntas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_preguntas.setAdapter(adapter);

        spinner_preguntas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String texto = parent.getItemAtPosition(position).toString();
                compareToEnviarBD(Cuestionario.PREGUNTA_UNO);
                Toast.makeText(parent.getContext(), texto, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        setupPieChart();
    }

    // https://github.com/PhilJay/MPAndroidChart
    // https://material.io/tools/color/#!/?view.left=0&view.right=0&primary.color=2196F3
    private void setupPieChart() {
        // Popultaing a list of PieEntries
        List<PieEntry> pieEntries = new ArrayList<>();
        //pieEntries.add(valor,nombre);
        pieEntries.add(new PieEntry(5 * 100 / 25, "0"));
        pieEntries.add(new PieEntry(20 * 100 / 25, "1"));
        PieDataSet dataSet = new PieDataSet(pieEntries, "Label");
        //dataSet.setColor();
        PieData data = new PieData(dataSet);
        // Get the chart
        PieChart chart = findViewById(R.id.chart);
        chart.setData(data);
        chart.animateY(1000);
        chart.invalidate();
    }

    public void extraerInfoByBD(int i, final RadioButton aux) {
        switch (i) {
            case 1:
                compareToEnviarBD(Cuestionario.PREGUNTA_UNO);
                break;
            case 2:
                compareToEnviarBD(Cuestionario.PREGUNTA_DOS);
                break;
            case 3:
                compareToEnviarBD(Cuestionario.PREGUNTA_TRES);
                break;
            case 4:
                compareToEnviarBD(Cuestionario.PREGUNTA_CUATRO);
                break;
            case 5:
                compareToEnviarBD(Cuestionario.PREGUNTA_CINCO);
                break;
            default:
                break;
        }
    }

    public void compareToEnviarBD(final String ruta) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String json = WEBUtilDomi.GETrequest(ruta);
                    Gson g = new Gson();
                    //Colecciones
                    Type type = new TypeToken<HashMap<String, String>>() {
                    }.getType();
                    //Para clases
                    HashMap<String, String> lista = g.fromJson(json, type);
                    Collection<String> opciones = lista.values();
                    Object[] objeto = opciones.toArray();
                    for (int i = 0; i < objeto.length; i++) {
                        String auxOpciones = objeto[i].toString();
                        Log.e(">>>", "Holi:" + auxOpciones);
                        if (auxOpciones == "O1") {
                            opcionA++;
                        } else if (auxOpciones == "O2") {
                            opcionB++;
                        } else if (auxOpciones == "O3") {
                            opcionC++;
                        } else if (auxOpciones == "O4") {
                            opcionD++;
                        }
                        totalOpciones++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
