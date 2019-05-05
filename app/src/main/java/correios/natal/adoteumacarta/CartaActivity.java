package correios.natal.adoteumacarta;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import correios.natal.adoteumacarta.helpers.DBHelper;

public class CartaActivity extends AppCompatActivity {

   protected Cursor mCursor;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carta);


    }

    @Override
    protected void onResume() {
        super.onResume();

        DBHelper db = new DBHelper(getBaseContext());
        SQLiteDatabase banco = db.getReadableDatabase();

        mCursor = banco.rawQuery("SELECT aluno, presente, _id FROM carta", null);

        String[] from = {
                "aluno",
                "presente",
                "_id"
        };

        int[] to = {
                R.id.txvNomeAluno,
                R.id.txvPresente,
                R.id.txvIdAluno
        };

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(), R.layout.lista_carta, mCursor, from, to, 0);

        ListView ltvCartas = findViewById(R.id.ltvCartas);

        ltvCartas.setAdapter(adapter);

    }

    @Override
    protected void onPause() {
        super.onPause();

        mCursor.close();
    }
}