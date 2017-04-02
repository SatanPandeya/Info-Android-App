package com.info.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.info.Model.InfoModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wolfmatrix on 4/1/17.
 */

public class InfoDBHelper extends SQLiteOpenHelper {
    private static final String TAG = "InfoDBHelper";
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

    // add content

    public void addInfo(InfoModel infoModel) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("KEY_ID", KEY_ID);
            contentValues.put("FIRST_NAME", FIRST_NAME);
            contentValues.put("LAST_NAME", LAST_NAME);

            sqLiteDatabase.insertOrThrow(INFO_TABLE, null, contentValues);
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(TAG, "addInfo: Error while trying to add or update user");
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    public long addOrUpdateInfo(InfoModel infoModel) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long infoId = -1;
        sqLiteDatabase.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("FIRST_NAME", infoModel.getFName());
            contentValues.put("LAST_NAME", infoModel.getLName());

            // try to update information if already exists
            int rows = sqLiteDatabase.update(INFO_TABLE, contentValues, FIRST_NAME + "= ?", new String[]{infoModel.getFName()});

            // check is update succed

            if (rows == 1) {
                // get primary key of info
                String queryInfo = String.format("SELECT %s FROM %s WHERE %s = ?", KEY_ID, FIRST_NAME, LAST_NAME);
                Cursor cursor = sqLiteDatabase.rawQuery(queryInfo, new String[]{String.valueOf(infoModel.getFName())});
                try {
                    if (cursor.moveToFirst()) {
                        infoId = cursor.getInt(0);
                        sqLiteDatabase.setTransactionSuccessful();
                    }
                } finally {
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                }
            } else {
                // if info with this id doesn't really exist
                infoId = sqLiteDatabase.insertOrThrow(INFO_TABLE, null, contentValues);
                sqLiteDatabase.setTransactionSuccessful();
            }
        } catch (Exception e) {
            Log.e(TAG, "updateInfo: Error while trying to add or update user");
        } finally {
            sqLiteDatabase.endTransaction();
        }
        return infoId;
    }

//    public List<InfoModel> getAllInfo(){
//        List<InfoModel> infoModelLists = new ArrayList<>();
//        String queryList = String.format("SELECT %s FROM %s WHERE %s = ?", KEY_ID, FIRST_NAME, LAST_NAME);
//        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
//        Cursor cursor  = sqLiteDatabase.rawQuery(queryList, null);
//        try {
//            if (cursor.moveToFirst()){
//                do {
//                    InfoModel infoModel = new InfoModel();
//                    infoModel.setFName(cursor.getString(0));
//                    infoModel.setLName(cursor.getString(1));
//                    infoModelLists.add(infoModel);
//                } while (cursor.moveToNext());
//            }
//        } catch (Exception e){
//            Log.e(TAG, "getAllInfo: Error while trying to get posts from database");
//        }finally {
//            if (cursor != null && !cursor.isClosed()){
//                cursor.close();
//            }
//        }
//        return infoModelLists;
//    }

    // Getting All Contacts
    public List<InfoModel> getAllInfo() {
        List<InfoModel> infoModelList = new ArrayList<InfoModel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + INFO_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                InfoModel infoModel = new InfoModel();
                infoModel.setFName(cursor.getString(1));
                infoModel.setLName(cursor.getString(2));
                // Adding contact to list
                infoModelList.add(infoModel);
            } while (cursor.moveToNext());
        }

        // return contact list
        return infoModelList;
    }

    public int updateInfo(InfoModel infoModel) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("FIRST_NAME", FIRST_NAME);
        contentValues.put("LAST_NAME", LAST_NAME);
        return sqLiteDatabase.update(INFO_TABLE, contentValues, FIRST_NAME + " =?", new String[]{infoModel.getFName(), infoModel.getLName()});
    }

    public void deleteInfo() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try {
            sqLiteDatabase.delete(INFO_TABLE, null, null);
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(TAG, "deleteInfo: Error while trying to delete all posts and users");
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

//    // add info content to database
//    public void addToDoList(InfoModel infoModel) {
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("FIRST_NAME", infoModel.getFName());
//        contentValues.put("LAST_NAME", infoModel.getLName());
//
//        // insert into table
//        sqLiteDatabase.insert(INFO_TABLE, null, contentValues);
//        // terminate database connection
//        sqLiteDatabase.close();
//    }
//
//    // retrieve info content of database
//    public InfoModel getToDoContent(String firstName) {
//        SQLiteDatabase sqLiteDataBase = this.getReadableDatabase();
//        Cursor cursor = sqLiteDataBase.query(INFO_TABLE, new String[]{KEY_ID, FIRST_NAME, LAST_NAME},
//                KEY_ID + "=?", new String[]{firstName}, null, null, null);
//
//        if (cursor != null)
//            cursor.moveToFirst();
//
//        return new InfoModel(
//                cursor.getString(0), cursor.getString(1)
//        );
//    }
//
//    // update info content of database
//    public int updateToDoList(InfoModel infoModel) {
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("FIRST_NAME", infoModel.getFName());
//        contentValues.put("LAST_NAME", infoModel.getLName());
//        return sqLiteDatabase.update(INFO_TABLE, contentValues, KEY_ID + " =? ", new String[]{infoModel.getFName(), infoModel.getLName()});
//    }
//
//    // delete info content of database
//    public void deleteToDoListContent(InfoModel infoModel){
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        sqLiteDatabase.delete(INFO_TABLE, KEY_ID + " =? ", new String[]{infoModel.getFName()});
//    }
}
