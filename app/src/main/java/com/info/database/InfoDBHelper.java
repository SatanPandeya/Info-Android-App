package com.info.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.info.Model.InfoModel;

/**
 * Created by wolfmatrix on 4/1/17.
 */

public class InfoDBHelper extends SQLiteOpenHelper {

    // database version
    private static final int DATABASE_VERSION = 1;
    // database name
    private static final String DATABASE_NAME = "Info.db";
    // table name
    private static final String INFO_TABLE = "info";

    // item container
    private static final String KEY_ID = "ID";
    private static final String FIRST_NAME = "FIRST_NAME";
    private static final String LAST_NAME = "LAST_NAME";

    public InfoDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_INFO_TABLE = "CREATE TABLE " + INFO_TABLE
                + "("
                + KEY_ID + "INTEGER PRIMARY KEY,"
                + FIRST_NAME + " TEXT, "
                + LAST_NAME + " TEXT "
                + ")";

        sqLiteDatabase.execSQL(CREATE_INFO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + INFO_TABLE);
        onCreate(sqLiteDatabase);
    }

    // add info content to database
    public void addToDoList(InfoModel infoModel) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("FIRST_NAME", infoModel.getFName());
        contentValues.put("LAST_NAME", infoModel.getLName());

        // insert into table
        sqLiteDatabase.insert(INFO_TABLE, null, contentValues);
        // terminate database connection
        sqLiteDatabase.close();
    }

    // retrieve info content of database
    public InfoModel getToDoContent(int id) {
        SQLiteDatabase sqLiteDataBase = this.getReadableDatabase();
        Cursor cursor = sqLiteDataBase.query(INFO_TABLE, new String[]{KEY_ID, FIRST_NAME, LAST_NAME},
                KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        return new InfoModel(
                cursor.getString(0), cursor.getString(1)
        );
    }

    // update info content of database
    public int updateToDoList(InfoModel infoModel) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("FIRST_NAME", infoModel.getFName());
        contentValues.put("LAST_NAME", infoModel.getLName());
        return sqLiteDatabase.update(INFO_TABLE, contentValues, KEY_ID + " =? ", new String[]{infoModel.getFName(), infoModel.getLName()});
    }

    // delete info content of database
    public void deleteToDoListContent(InfoModel infoModel){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(INFO_TABLE, KEY_ID + " =? ", new String[]{infoModel.getFName()});
    }
}
