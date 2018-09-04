package com.example.niklasjahning.howtojava;

import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseAdapter {

    private MyDatabaseHelper helper;
    private SQLiteDatabase db;

    // Datenbanksetup
    public static final String DB_NAME = "AppDatabase";
    public static final int DB_VERSION = 1;

    // Relationenmodell
    // 3 Spalten in der Tabelle my-example-table
    // _id, first-example, second-example
    public static final String TABLE_EXAMPLE = "my-example-table";
    public static final String KEY_ID = "_id";
    public static final String KEY_FIRST_EXAMPLE = "first-example";
    public static final String KEY_SECOND_EXAMPLE = "second-example";

    public MyDatabaseAdapter(Context context) {
        helper = new MyDatabaseHelper(context, DB_NAME, null, DB_VERSION);
    }

    // Öffnen der Datenbankverbindung
    public void open() {
        db = helper.getWritableDatabase();
    }

    // Schließen der Datenbankverbindung
    public void close() {
        db.close();
        helper.close();
    }

    // Datenmanipulation: Methoden

    // Beispielmethode: Objekt in my-example-table einfügen
    public long insertMyObject(Object myExampleObject) {
        // Datensammlung für den einzufügenden Datensatz erstellen (ContentValues)
        // nutzt Schlüssel-Wert-Mechanismus
        // es werden die Konstanten v. o. genutzt, um Fehler zu vermeiden
        ContentValues v = new ContentValues();
        v.put(KEY_FIRST_EXAMPLE, myExampleObject.toString());
        v.put(KEY_SECOND_EXAMPLE, myExampleObject.toString()); // exemparisch einfach toString()
        long newInsertId = db.insert(TABLE_EXAMPLE, null, v);
        return newInsertId;
    }

    // Beispielmethode: alle Einträge aus my-example-table holen
    public Cursor getAllMyObjects() {
        String[] allColumns = new String[] { KEY_ID, KEY_FIRST_EXAMPLE, KEY_SECOND_EXAMPLE };
        Cursor results = db.query(TABLE_EXAMPLE, allColumns, null, null, null, null, null);
        return results;
    }


    // Beispielmethode: Ein myObject-Tupel löschen
    public void removeMyObject(long id) {
        String toDelete = KEY_ID + "=?";
        String[] deleteArgs = new String[] { String.valueOf(id) };
        db.delete(TABLE_EXAMPLE, toDelete, deleteArgs);
    }




    // Interne Ableitung der Hilfsklasse SQLiteOpenHelper zur Erstellung der Tabellen
    private class MyDatabaseHelper extends SQLiteOpenHelper {

        // Hier wird über das SQL Statement das Datenmodell festgelegt
        private static final String CREATE_DB = "create table " + TABLE_EXAMPLE + " (" + KEY_ID + " integer primary key autoincrement, " + KEY_FIRST_EXAMPLE + " text not null, " + KEY_SECOND_EXAMPLE + " text not null);";

        public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_DB);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Upgrage bei Versionsänderung: Wie hat sich das Datenmodell verändert? Immer individuell je nach Datenbankversion!
        }
    }
}
