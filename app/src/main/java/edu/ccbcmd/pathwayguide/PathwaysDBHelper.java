package edu.ccbcmd.pathwayguide;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Pattern;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.*;
import android.database.Cursor;
import android.content.ContentValues;
import android.util.Log;

/**
 * Created by dorothy on 7/3/16.
 */
public class PathwaysDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "pathways.db";
    public static final int DATABASE_VERSION = 2;
    private InputStream classes, pathways;

    public PathwaysDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        classes = context.getResources().openRawResource(R.raw.classvalues);
        pathways = context.getResources().openRawResource(R.raw.pathwayvalues);

    }

    public PathwaysDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        classes = context.getResources().openRawResource(R.raw.classvalues);
        pathways = context.getResources().openRawResource(R.raw.pathwayvalues);
    }

    public PathwaysDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                            int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
        classes = context.getResources().openRawResource(R.raw.classvalues);
        pathways = context.getResources().openRawResource(R.raw.pathwayvalues);
    }

    public void onCreate(SQLiteDatabase db) {
        Log.w("in", "oncreate");
        // create tables
        db.execSQL("create table classes (id text primary key, name text, description text, prereqs text, gened text, status int)");
        db.execSQL("create table subpathways  (name text, degree text, pathway text, classes text, prelec text)");

        // populate all the classes from file
            BufferedReader rd = new BufferedReader(new InputStreamReader(classes));
            try {
                String line = rd.readLine();
                while (line != null) {
                    String[] values = line.split("\\|", 0);
                    ContentValues cv = new ContentValues();

                    // indices 0: id 1: name 2: description 3: prereqs 4: gen ed

                    // now the rest of the data
                    cv.put("id", values[0]);
                    cv.put("name", values[1]);
                    cv.put("description", values[2]);
                    cv.put("prereqs", values[3]);
                    cv.put("status", DatabaseWrapper.NOT_COMPLETED);
                    cv.put("gened", values[4]);
                    db.insert("classes", null, cv);

                    line = rd.readLine();
                }

            // the pathways too
            rd = new BufferedReader(new InputStreamReader(pathways));
            line = rd.readLine();
            while (line != null) {
                String[] values = line.split("\\|", 0);
                ContentValues cv = new ContentValues();

                // indices 0: name of major 1: degree type 2: pathway name 3: class sequence 4: program electives
                Log.e("PDBH", ""+values.length);
                cv.put("name", values[0]);
                cv.put("degree", values[1]);
                cv.put("pathway", values[2]);
                cv.put("classes", values[3]);
                //cv.put("prelec", values[4]);
                db.insert("subpathways", null, cv);
                line = rd.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
    public void onOpen(SQLiteDatabase db) {}

}
