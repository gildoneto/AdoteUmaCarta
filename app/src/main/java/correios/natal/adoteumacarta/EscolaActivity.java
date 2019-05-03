package correios.natal.adoteumacarta;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import correios.natal.adoteumacarta.helpers.DBHelper;

public class EscolaActivity extends AppCompatActivity {

    protected Cursor mCursor;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escola);

    }

    @Override
    protected void onResume() {
        super.onResume();

        DBHelper db = new DBHelper(getBaseContext());
        SQLiteDatabase banco = db.getReadableDatabase();

        mCursor = banco.rawQuery("SELECT nome, diretor, _id FROM escola", null);

        String[] from = {
                "nome",
                "diretor",
                "_id"
        };

        int[] to = {
                R.id.txvNomeEscola,
                R.id.txvNomeDiretor,
                R.id.txvId
        };

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(), R.layout.lista_escola, mCursor, from, to, 0);

        ListView ltvEscolas = findViewById(R.id.ltvEscolas);

        ltvEscolas.setAdapter(adapter);

    }

    @Override
    protected void onPause() {
        super.onPause();

        mCursor.close();
    }
}