package tk.munavvar.sibkalogbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by MunavvarHushen on 18-08-2017.
 */

public class LogDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "sibkalog.db";
    private static final int DATABASE_VERSION = 1;
    public static final String LOG_TABLE_NAME = "sibkalog";
    public static final String LOG_COLUMN_ID = "_id";
    public static final String LOG_COLUMN_LOGDATA = "logdata";
    public static final String LOG_COLUMN_LOGDATE = "logdate";

    public LogDbHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + LOG_TABLE_NAME + "(" +
                LOG_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                LOG_COLUMN_LOGDATA + " TEXT, " +
                LOG_COLUMN_LOGDATE + " TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + LOG_TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public boolean insertLog(String data) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
        String formattedDate = df.format(c.getTime());
        contentValues.put(LOG_COLUMN_LOGDATA, data);
        contentValues.put(LOG_COLUMN_LOGDATE, formattedDate);
        db.insert(LOG_TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean updateLog(Integer id, String data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LOG_COLUMN_LOGDATA, data);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
        String formattedDate = df.format(c.getTime());
        contentValues.put(LOG_COLUMN_LOGDATE, formattedDate);
        db.update(LOG_TABLE_NAME, contentValues, LOG_COLUMN_ID + " = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Cursor getLog(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM " + LOG_TABLE_NAME + " WHERE " +
                LOG_COLUMN_ID + "=?", new String[] { Integer.toString(id) } );
        return res;
    }
    public Cursor getAllLog() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM " + LOG_TABLE_NAME +" ORDER BY _id DESC", null );
        return res;
    }
    public Integer deleteLog(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(LOG_TABLE_NAME,
                LOG_COLUMN_ID + " = ? ",
                new String[] { Integer.toString(id) });
    }



}
