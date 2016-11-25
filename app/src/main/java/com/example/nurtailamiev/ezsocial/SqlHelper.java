package com.example.nurtailamiev.ezsocial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.nurtailamiev.ezsocial.User;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by nurtailamiev on 11/13/16.
 */

public class SqlHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "ezSocialDB";
        public static final String DATABASE_TABLE = "userData";
        private static final int DATABASE_VERSION = 1;
        public static final String COLUMN1 = "s_id";
        public static final String COLUMN2 = "first_name";
        public static final String COLUMN3 = "last_name";
        public static final String COLUMN4 = "email";
        public static final String COLUMN5 = "password";


        String SCRIPT_CREATE_TABLE1 = "CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE + "("
                + COLUMN1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN2 + " TEXT,"
                + COLUMN3 + " TEXT,"
                + COLUMN4 + " TEXT,"
                + COLUMN5 + " TEXT"
                + ");";


        public SqlHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            db.execSQL(SCRIPT_CREATE_TABLE1);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            db.execSQL("DROP TABLE IF EXISTS" + DATABASE_TABLE);

            onCreate(db);
        }
        public void addUser(User s) {
            SQLiteDatabase db = this.getWritableDatabase();
            onCreate(db);
            ContentValues values = new ContentValues();
            values.put(COLUMN2, s.getFName());
            values.put(COLUMN3, s.getLName());
            values.put(COLUMN4, s.getEmail());
            values.put(COLUMN5, s.getPassword());
            db.insert(DATABASE_TABLE, null, values);
            db.close();
        }

        public List<User> getAllItems(String email) {
            List<User> itemList = new ArrayList<User>();

            // Select All Query
            String selectQuery = "SELECT  * FROM " + DATABASE_TABLE + " where email LIKE '" + email + "'";

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    User s = new User();

                    s.setId(cursor.getInt(0));
                    s.setFName(cursor.getString(1));
                    s.setLName(cursor.getString(2));
                    s.setEmail(cursor.getString(3));
                    s.setPassword(cursor.getString(4));

                    // Adding contact to list
                    itemList.add(s);
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
            // return contact list
            return itemList;
        }


}
