package com.example.tamz2_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "DBTAMZ2.db";
    public static final String ITEM_COLUMN_ID = "id";
    public static final String ITEM_COLUMN_NAME = "name";
    public static final String ITEM_COLUMN_TYPE = "type";
    public static final String ITEM_COLUMN_COST = "cost";

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE items " + "(id INTEGER PRIMARY KEY, name TEXT, type INTEGER, cost INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS items");
        onCreate(db);
    }

    public boolean insertItem(String name, int type, int cost)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("type", type);
        contentValues.put("cost", cost);
        long insertedId = db.insert("items", null, contentValues);
        if (insertedId == -1) return false;
        return true;
    }

    public boolean deleteItem (int id)
    {
        //TODO 3: doplnit kod pro odstraneni zaznamu
        SQLiteDatabase db = this.getWritableDatabase();
        long insertedId = db.delete("items", "id = ?", new String[] { String.valueOf(id) });
        if (insertedId == -1) return false;
        return true;
    }

    //Cursor representuje vracena data
    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from items where id=" + id + "", null);
        return res;
    }

    public boolean updateItem (Integer id, String name, int type, int cost)
    {
        //TODO 4: doplnit kod pro update zaznamu
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("type", type);
        contentValues.put("cost", cost);
        long insertedId = db.update("items", contentValues, "id = ?", new String[] { String.valueOf(id) });
        if (insertedId == -1) return false;
        return true;
    }

    public ArrayList<DataItem> getItemList()
    {
        ArrayList<DataItem> arrayList = new ArrayList<DataItem>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from items", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            //TODO 6: doplnit kod i pro další sloupce tabulky (například ITEM_COLUMN_COST)
            DataItem item = new DataItem(
                res.getInt(res.getColumnIndex(ITEM_COLUMN_ID)),
                res.getString(res.getColumnIndex(ITEM_COLUMN_NAME)),
                res.getInt(res.getColumnIndex(ITEM_COLUMN_TYPE)),
                res.getInt(res.getColumnIndex(ITEM_COLUMN_COST))
            );
            arrayList.add(item);
            res.moveToNext();
        }

        return arrayList;
    }

    public int removeAll()
    {
        //TODO 5: doplnit kod pro odstraneni vsech zaznamu
        int nRecordDeleted = 0;
        for(DataItem item : getItemList()) {
            deleteItem(item.getId());
            nRecordDeleted++;
        }
        return nRecordDeleted;
    }


}
