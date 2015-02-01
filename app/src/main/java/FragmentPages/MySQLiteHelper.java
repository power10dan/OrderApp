package FragmentPages;

import android.database.sqlite.SQLiteOpenHelper;
import java.util.LinkedList;
import java.util.List;
import android.util.Log;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by sean on 1/31/2015.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {
    // Teas table name
    private static final String TABLE_TEAS = "teas";

    // Teas table column names
    private static final String KEY_FLAVOR = "flavor";
    private static final String KEY_SIZE = "size";
    private static final String KEY_TYPE = "tapiocaType";
    private static final String KEY_ID = "id";

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "TeaDB";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create tea data table
        String CREATE_TEA_TABLE = "CREATE TABLE teas ( " + "flavor TEXT, "
                + "size INTEGER, " + "tapiocaType TEXT, " + "id INT )";

        // create tea table
        db.execSQL(CREATE_TEA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older teas table if exists
        db.execSQL("DROP TABLE IF EXISTS teas");

        // create fresh teas table
        this.onCreate(db);
    }

    public void addTeaData(TeaData tea) {
        // for logging
        Log.d("addTea", tea.toString());

        SQLiteDatabase db = this.getWritableDatabase();

        // create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_FLAVOR, tea.getFlavor());
        values.put(KEY_SIZE, tea.getSize());
        values.put(KEY_TYPE, tea.getTapiocaType());
        values.put(KEY_ID, tea.getId());

        // insert
        db.insert(TABLE_TEAS, null, values);

        db.close();
    }

    public List<TeaData> getAllTeas() {
        List<TeaData> teas = new LinkedList<TeaData>();

        // build the query
        String query = "SELECT * FROM " + TABLE_TEAS;

        // get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // go through each row and add to list
        TeaData tea = null;
        if (cursor.moveToFirst()) {
            do {
                tea = new TeaData();
                tea.setFlavor(cursor.getString(0));
                tea.setSize(Integer.parseInt(cursor.getString(1)));
                tea.setTapiocaType(cursor.getString(2));
                tea.setId(Integer.parseInt(cursor.getString(3)));

                // add tea to teas
                teas.add(tea);
            } while (cursor.moveToNext());

        }
        Log.d("getAllTeas()", teas.toString());
        return teas;
    }

    // delete single tea
    public void deleteTea(TeaData t) {
        SQLiteDatabase db = this.getWritableDatabase();

        // delete tea
        db.delete(TABLE_TEAS, KEY_ID+" = ?", new String[] {String.valueOf(t.getId())});
        Log.d("deleteTea()", t.toString());
        db.close();
    }

    public void deleteAllTeas() {
        SQLiteDatabase db = this.getWritableDatabase();

        onUpgrade(db, 1, 1);
    }
}