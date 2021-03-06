package correios.natal.adoteumacarta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button btnescola = findViewById(R.id.btn_escola);
        Button btncarta = findViewById(R.id.btn_carta);
        Button btncadastro = findViewById(R.id.btn_cadastro);
        Button btnconsulta = findViewById(R.id.btn_consulta);

        btnescola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), EscolaActivity.class));
            }
        });

        btncarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), CartaActivity.class));
            }
        });

        btncadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), CadastroActivity.class));
            }
        });

        btnconsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), ConsultaActivity.class));
            }
        });


    }


}