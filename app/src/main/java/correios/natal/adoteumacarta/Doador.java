package correios.natal.adoteumacarta;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

import correios.natal.adoteumacarta.helpers.DBHelper;

public class Doador {

    private int id;
    private String nome;
    private String cell;
    private String gift;
    private String status;
    private boolean excluir;
    private Context context;

    public Doador(Context context){
        this.context = context;
        id = -1;
    }

    public int getId() {return id; }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public boolean isExcluir() {
        return excluir;
    }

    public void setExcluir(boolean excluir) {
        this.excluir = excluir;
    }


    public boolean excluir(){
        DBHelper dbHelper = null;
        SQLiteDatabase sqLiteDatabase = null;
        try{
            dbHelper = new DBHelper(context);
            sqLiteDatabase = dbHelper.getWritableDatabase();
            sqLiteDatabase.beginTransaction();

            sqLiteDatabase.delete("doador","_id = ?",new String[]{String.valueOf(id)});

            excluir = true;

            sqLiteDatabase.setTransactionSuccessful();
            sqLiteDatabase.endTransaction();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            sqLiteDatabase.endTransaction();
            return false;
        }finally {
            if (sqLiteDatabase != null)
                sqLiteDatabase.close();
            if (dbHelper != null)
                dbHelper.close();
        }
    }

    public boolean salvar(){
        DBHelper dbHelper = null;
        SQLiteDatabase sqLiteDatabase = null;
        try{
            dbHelper = new DBHelper(context);
            sqLiteDatabase = dbHelper.getWritableDatabase();
            String sql = "";
            if (id == -1){
                sql = "INSERT INTO doador (nome,cell,gift,status) VALUES (?,?,?,?)";
            }else{
                sql = "UPDATE doador SET nome = ?, cell = ?, gift = ?, status = ? WHERE _id = ?";
            }
            sqLiteDatabase.beginTransaction();
            SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(sql);
            sqLiteStatement.clearBindings();
            sqLiteStatement.bindString(1,nome);
            sqLiteStatement.bindString(2,cell);
            sqLiteStatement.bindString(3,gift);
            sqLiteStatement.bindString(4,status);
            if (id != -1)
                sqLiteStatement.bindString(5,String.valueOf(id));
            sqLiteStatement.executeInsert();
            sqLiteDatabase.setTransactionSuccessful();
            sqLiteDatabase.endTransaction();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            sqLiteDatabase.endTransaction();
            return false;
        }finally {
            if (sqLiteDatabase != null)
                sqLiteDatabase.close();
            if (dbHelper != null)
                dbHelper.close();
        }
    }

    public ArrayList<Doador> getDoadores(){
        DBHelper dbHelper = null;
        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;
        ArrayList<Doador> doadores = new ArrayList<>();
        try{
            dbHelper = new DBHelper(context);
            sqLiteDatabase = dbHelper.getReadableDatabase();
            cursor = sqLiteDatabase.query("doador",null,null,null,null,null,null);
            while (cursor.moveToNext()) {
                Doador doador = new Doador(context);
                doador.id = cursor.getInt(cursor.getColumnIndex("_id"));
                doador.nome = cursor.getString(cursor.getColumnIndex("nome"));
                doador.cell = cursor.getString(cursor.getColumnIndex("cell"));
                doador.gift = (cursor.getString(cursor.getColumnIndex("gift")));
                doador.status = (cursor.getString(cursor.getColumnIndex("status")));
                doadores.add(doador);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if ((cursor != null) &&(cursor.isClosed()))
                cursor.close();
            if (sqLiteDatabase != null)
                sqLiteDatabase.close();
            if (dbHelper != null)
                dbHelper.close();
        }
        return doadores;
    }

    public void carregaDoadorPeloId(int id){
        DBHelper dbHelper = null;
        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;
        try{
            dbHelper = new DBHelper(context);
            sqLiteDatabase = dbHelper.getReadableDatabase();
            cursor = sqLiteDatabase.query("doador",null,"_id = ?",new String[]{String.valueOf(id)},null,null,null);
            excluir = true;
            while (cursor.moveToNext()){
                this.id = cursor.getInt(cursor.getColumnIndex("_id"));
                nome = cursor.getString(cursor.getColumnIndex("nome"));
                cell = cursor.getString(cursor.getColumnIndex("cell"));
                gift = (cursor.getString(cursor.getColumnIndex("gift")));
                status = (cursor.getString(cursor.getColumnIndex("status")));
                excluir = false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if ((cursor != null) && (!cursor.isClosed()))
                cursor.close();
            if (sqLiteDatabase != null)
                sqLiteDatabase.close();
            if (dbHelper != null)
                dbHelper.close();
        }
    }


}
