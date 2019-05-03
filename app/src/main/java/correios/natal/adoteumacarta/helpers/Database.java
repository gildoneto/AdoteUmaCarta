package correios.natal.adoteumacarta.helpers;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "adoteumacarta.db";
    private static final String TABLE_NAME = "doador_tbl";


    public Database(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_NAME +" ("
                +"_id INTEGER PRIMARY KEY autoincrement,"
                +"nome varchar(45) NOT NULL ,"
                +"presente varchar(45) NOT NULL,"
                +"status varchar(45) NOT NULL"
                + ");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
