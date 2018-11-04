package icesi.i2t.quizdos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class Estadisticas extends AppCompatActivity {

    private Spinner spinner_preguntas;
    private ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        spinner_preguntas = findViewById(R.id.spinner_preguntas);
        adapter = ArrayAdapter.createFromResource(this,R.array.preguntas,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_preguntas.setAdapter(adapter);

        spinner_preguntas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String texto = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(),texto, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setupPieChart(){
        // Popultaing a list of PieEntries
        List<PieEntry> pieEntries = new ArrayList<>();
        //pieEntries.add(valor,nombre);
        PieDataSet dataSet = new PieDataSet(pieEntries, "Label");
        //dataSet.setColor();
        PieData data = new PieData(dataSet);
        // Get the chart
        PieChart chart = findViewById(R.id.chart);
        chart.setData(data);
        chart.animateY(1000);
        chart.invalidate();
    }
}
