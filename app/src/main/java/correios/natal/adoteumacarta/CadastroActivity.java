package correios.natal.adoteumacarta;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.annotation.Nullable;
import android.widget.Toast;

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
                dialogDelete();
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

    private AlertDialog alerta;

    private void dialogDelete(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dlg_delete_tittle);
        builder.setMessage(R.string.dlg_excluir);
        builder.setPositiveButton(R.string.dlg_positivo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                doador.excluir();
                Toast.makeText(getApplicationContext(), R.string.dlg_toast_delete, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        builder.setNegativeButton(R.string.dlg_negativo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), R.string.dlg_toast_cancel, Toast.LENGTH_SHORT).show();
            }
        });
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();

    }

}
