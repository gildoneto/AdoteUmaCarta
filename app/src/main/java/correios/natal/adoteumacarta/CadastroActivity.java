package correios.natal.adoteumacarta;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.support.annotation.Nullable;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import correios.natal.adoteumacarta.helpers.DBHelper;

public class CadastroActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private EditText editTextNome;
    private EditText editTextCell;
    private EditText editTextGift;
    private EditText editTextStatus;
    private Button buttonExcluir;
    private Button buttonSalvar;
    private Button buttonCancelar;
    private Spinner spinnerGift;
    protected Cursor mCursor;

    private final Doador doador = new Doador(this);



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        editTextNome = findViewById(R.id.editTextNome);
        editTextCell = findViewById(R.id.editTextCell);
        spinnerGift = findViewById(R.id.spinnerGift);
        //editTextGift = findViewById(R.id.editTextGift);
        editTextStatus = findViewById(R.id.editTextStatus);
        buttonExcluir = findViewById(R.id.btnExcluir);
        buttonCancelar = findViewById(R.id.btnCancelar);
        buttonSalvar = findViewById(R.id.btnSalvar);

        // Loading spinner data from database
        loadSpinnerData();

        buttonExcluir.setOnClickListener(this);
        buttonSalvar.setOnClickListener(this);
        buttonCancelar.setOnClickListener(this);


        if (getIntent().getExtras() != null) {
            setTitle(getString(R.string.titulo_editando));
            int id = getIntent().getExtras().getInt("consulta");
            doador.carregaDoadorPeloId(id);
            editTextNome.setText(doador.getNome());
            editTextCell.setText(doador.getCell());
            editTextGift.setText(doador.getGift());
            editTextStatus.setText(doador.getStatus());
        } else {
            setTitle(getString(R.string.titulo_incluindo));
        }

        buttonExcluir.setEnabled(true);
        if (doador.getId() == -1)
            buttonExcluir.setEnabled(false);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCancelar: {
                finish();
                break;
            }
            case R.id.btnExcluir: {
                dialogDelete();
                break;
            }
            case R.id.btnSalvar: {
                boolean valido = true;
                doador.setNome(editTextNome.getText().toString().trim());
                doador.setCell(editTextCell.getText().toString().trim());
                doador.setGift(editTextGift.getText().toString().trim());
                doador.setStatus(editTextStatus.getText().toString().trim());

                if (doador.getNome().equals("")) {
                    editTextNome.setError(getString(R.string.obrigatorio));
                    valido = false;
                }

                if (doador.getCell().equals("")) {
                    editTextCell.setError(getString(R.string.obrigatorio));
                    valido = false;
                }

                if (doador.getGift().equals("")) {
                    editTextGift.setError(getString(R.string.obrigatorio));
                    valido = false;
                }

                if (doador.getStatus().equals("")) {
                    editTextStatus.setError(getString(R.string.obrigatorio));
                    valido = false;
                }

                if (valido) {
                    doador.salvar();
                    finish();
                }
                break;
            }
        }
    }

    private AlertDialog alerta;

    private void dialogDelete() {
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

    @Override
    protected void onResume() {
        super.onResume();


        }


    private void loadSpinnerData() {
        DBHelper db = new DBHelper(getApplicationContext());
        List<String> labels = db.getAllPresentes();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, labels);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerGift.setAdapter(dataAdapter);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
