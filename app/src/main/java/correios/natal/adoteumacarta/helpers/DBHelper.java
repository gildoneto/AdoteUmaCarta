package correios.natal.adoteumacarta.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "adoteumacarta.db";
    private static final int VERSAO_BANCO = 1;
    public String LOG = "SQLITE";

    public DBHelper( Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlTblEscola = "CREATE TABLE IF NOT EXISTS escola(_id INTEGER PRIMARY KEY, nome VARCHAR(255), diretor VARCHAR(100));";
        String sqlTblCarta = "CREATE TABLE IF NOT EXISTS carta(_id INTEGER PRIMARY KEY, aluno VARCHAR(255), presente VARCHAR(100));";

        String sqlInsEscola = "INSERT INTO escola VALUES"
                        + "(1001, 'ESCOLA LED ZEPPELIN', 'Robert Plant'),"
                        + "(1002, 'ESCOLA PINK FLOYD', 'Roger Waters'),"
                        + "(1003, 'ESCOLA BLACK SABBATH', 'Ozzy Osbourne'),"
                        + "(1004, 'ESCOLA AC DC', 'Bon Scott'),"
                        + "(1005, 'ESCOLA RED HOT CHILI PEPPERS', 'Anthony Kieds'),"
                        + "(1006, 'ESCOLA ALICE IN CHAINS', 'Layne Staley'),"
                        + "(1007, 'ESCOLA FOO FIGHTERS', 'Dave Grohl'),"
                        + "(1008, 'ESCOLA PEARL JAM', 'Eddie Vedder'),"
                        + "(1009, 'ESCOLA SILVERCHAIR', 'Daniel Johns');";


        String sqlInsCarta = "INSERT INTO carta VALUES"
                + "(5001, 'Ediwaldo Neto', 'Chopp Heineken'),"
                + "(5002, 'Paulo Brito', 'Whisky Blue Label'),"
                + "(5003, 'Gildo Neto', 'Notebook'),"
                + "(5004, 'Rodrigo Barbosa', 'iPad'),"
                + "(5005, 'Daiene Melo', 'Pendrive 1TB'),"
                + "(5006, 'Heloisa Galvao', 'iPhone'),"
                + "(5007, 'Matheus Rhumenig', 'Playstation 5'),"
                + "(5008, 'Pedro Nardelli', 'Relógio'),"
                + "(5009, 'Victor Pio', 'Bolsa Notebook');";

       // Log.i(LOG,"antes de sql" + sqlInsEscola.toString());
        db.execSQL(sqlTblEscola);
        db.execSQL(sqlTblCarta);

        db.execSQL(sqlInsEscola);
        db.execSQL(sqlInsCarta);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
