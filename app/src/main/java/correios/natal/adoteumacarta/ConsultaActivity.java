package correios.natal.adoteumacarta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ConsultaActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

    private ListView listViewDoadores;
    private Button buttonFechar;
    private DoadorAdapter doadorAdapter;
    private ArrayList<Doador> doadores;
    private Doador doadorEdicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        buttonFechar = findViewById(R.id.buttonFechar);
        buttonFechar.setOnClickListener(this);

        listViewDoadores = findViewById(R.id.listViewDoadores);
        listViewDoadores.setOnItemClickListener(this);

        doadores = new Doador(this).getDoadores();
        doadorAdapter = new DoadorAdapter(this,doadores);
        listViewDoadores.setAdapter(doadorAdapter);
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Doador doador = doadores.get(position);
        Intent intent = new Intent(this,CadastroActivity.class);
        intent.putExtra("consulta",doador.getId());
        doadorEdicao = doador;
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (doadorEdicao != null){
            doadorEdicao.carregaDoadorPeloId(doadorEdicao.getId());
            if (doadorEdicao.isExcluir())
                doadores.remove(doadorEdicao);
            doadorAdapter.notifyDataSetChanged();
        }
    }
}
