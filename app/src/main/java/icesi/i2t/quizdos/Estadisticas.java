package icesi.i2t.quizdos;

import android.graphics.Color;
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

    private String itemSeleccionado;

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

        itemSeleccionado = "";

        opcionA = 0;
        opcionB = 0;
        opcionC = 0;
        opcionD = 0;
        totalOpciones = 0;

        spinner_preguntas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemSeleccionado = parent.getItemAtPosition(position).toString();
                //Toast.makeText(parent.getContext(), texto, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        switchPieChart(itemSeleccionado);
    }

    private void switchPieChart(String spinner){
        switch (spinner) {
            case "Pregunta 1":
                extraerInfoByDB(Cuestionario.PREGUNTA_UNO);
                setupPieChart(spinner);
                break;
            case "Pregunta 2":
                extraerInfoByDB(Cuestionario.PREGUNTA_DOS);
                setupPieChart(spinner);
                break;
            case "Pregunta 3":
                extraerInfoByDB(Cuestionario.PREGUNTA_TRES);
                setupPieChart(spinner);
                break;
            case "Pregunta 4":
                extraerInfoByDB(Cuestionario.PREGUNTA_CUATRO);
                setupPieChart(spinner);
                break;
            case "Pregunta 5":
                extraerInfoByDB(Cuestionario.PREGUNTA_CINCO);
                setupPieChart(spinner);
                break;
            default:
                break;
        }
    }

    // https://github.com/PhilJay/MPAndroidChart
    // https://material.io/tools/color/#!/?view.left=0&view.right=0&primary.color=2196F3
    private void setupPieChart(String spinner) {

        // Popultaing a list of PieEntries
        List<PieEntry> pieEntries = new ArrayList<>();
        //pieEntries.add(valor,nombre);
        pieEntries.add(new PieEntry(opcionA * 100 / totalOpciones, "A"));
        pieEntries.add(new PieEntry(opcionB * 100 / totalOpciones, "B"));
        pieEntries.add(new PieEntry(opcionC * 100 / totalOpciones, "C"));
        pieEntries.add(new PieEntry(opcionD * 100 / totalOpciones, "D"));

        PieDataSet dataSet = new PieDataSet(pieEntries, spinner);
        dataSet.setColor(Color.GREEN);
        PieData data = new PieData(dataSet);
        // Get the chart
        PieChart chart = findViewById(R.id.chart);
        chart.setData(data);
        chart.animateY(1000);
        chart.invalidate();
    }

    public void extraerInfoByDB(final String ruta) {
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
                        //Log.e(">>>", "Holi:" + auxOpciones);
                        if (auxOpciones.equals("O1")) {
                            opcionA++;
                        } else if (auxOpciones.equals("O2")) {
                            opcionB++;
                        } else if (auxOpciones.equals("O3")) {
                            opcionC++;
                        } else if (auxOpciones.equals("O4")) {
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
