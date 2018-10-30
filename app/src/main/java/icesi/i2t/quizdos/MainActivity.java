package icesi.i2t.quizdos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn_cuestionario;
    private Button btn_estadisticas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_cuestionario = findViewById(R.id.btn_cuestionario);
        btn_estadisticas = findViewById(R.id.btn_estadisticas);

        btn_cuestionario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Cuestionario.class);
                startActivity(i);
                finish();
            }
        });

        btn_estadisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Estadisticas.class);
                startActivity(i);
                finish();
            }
        });

    }
}
