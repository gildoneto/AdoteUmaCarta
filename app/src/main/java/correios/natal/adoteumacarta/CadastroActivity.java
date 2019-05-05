package correios.natal.adoteumacarta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.annotation.Nullable;

public class CadastroActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextNome;
    private EditText editTextCell;
    private EditText editTextGift;
    private EditText editTextStatus;
    private Button buttonExcluir;
    private Button buttonSalvar;
    private Button buttonCancelar;

    private final Doador doador = new Doador(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        editTextNome = findViewById(R.id.editTextNome);
        editTextCell = findViewById(R.id.editTextCell);
        editTextGift = findViewById(R.id.editTextGift);
        editTextStatus = findViewById(R.id.editTextStatus);
        buttonExcluir = findViewById(R.id.btnExcluir);
        buttonCancelar = findViewById(R.id.btnCancelar);
        buttonSalvar = findViewById(R.id.btnSalvar);

        buttonExcluir.setOnClickListener(this);
        buttonSalvar.setOnClickListener(this);
        buttonCancelar.setOnClickListener(this);

        if (getIntent().getExtras() != null){
            setTitle(getString(R.string.titulo_editando));
            int id = getIntent().getExtras().getInt("consulta");
            doador.carregaDoadorPeloId(id);
            editTextNome.setText(doador.getNome());
            editTextCell.setText(doador.getCell());
            editTextGift.setText(doador.getGift());
            editTextStatus.setText(doador.getStatus());
        }else{
            setTitle(getString(R.string.titulo_incluindo));
        }

        buttonExcluir.setEnabled(true);
        if (doador.getId() == -1)
            buttonExcluir.setEnabled(false);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCancelar : {
                finish();
                break;
            }
            case R.id.btnExcluir : {
                doador.excluir();
                finish();
                break;
            }
            case R.id.btnSalvar :{
                boolean valido = true;
                doador.setNome(editTextNome.getText().toString().trim());
                doador.setCell(editTextCell.getText().toString().trim());
                doador.setGift(editTextGift.getText().toString().trim());
                doador.setStatus(editTextStatus.getText().toString().trim());

                if (doador.getNome().equals("")){
                    editTextNome.setError(getString(R.string.obrigatorio));
                    valido = false;
                }

                if (doador.getCell().equals("")){
                    editTextCell.setError(getString(R.string.obrigatorio));
                    valido = false;
                }

                if (doador.getGift().equals("")){
                    editTextGift.setError(getString(R.string.obrigatorio));
                    valido = false;
                }

                if (doador.getStatus().equals("")){
                    editTextStatus.setError(getString(R.string.obrigatorio));
                    valido = false;
                }

                if (valido){
                    doador.salvar();
                    finish();
                }
                break;
            }
        }
    }

}
