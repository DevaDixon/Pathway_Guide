package edu.ccbcmd.pathwayguide;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DatabaseWrapper {

    protected static SQLiteDatabase db;

    public static final int NOT_A_CLASS = -1;
    public static final int NOT_COMPLETED = 0;
    public static final int IN_PROGRESS = 1;
    public static final int COMPLETED = 2;

    // returns the classes (represented as class IDs) required for a subpathway as a string array
    // if the subpathway doesn't exist, it returns a string array of length 0
    // for a list of valid subpathway names, look in res/raw/pathwayvalues.txt
    public static String[] getSubPathwayClasses(String subpathway) {
        Cursor c = db.query(true, "subpathways", new String[] {"classes"}, "name = ?", new String[] {subpathway}, null, null, null, null);
        if (c.getCount() == 0) return new String[0];
        else {
            c.moveToNext();
            String classStr = c.getString(c.getColumnIndex("classes"));
            c.close();
            return classStr.split(" ");
        }
    }

    // returns possible program electives for a specified subpathway
    // it's structured so that the first index is a discrete group of possible electives
    // and the second index is a particular member of that group (an id of a possible elective)
    // there may be fewer groups than PRELEC placeholders. just use the last group for all following placeholders in that case
    // if the subpathway is invalid, it returns a 2d array with no length and nothing in it
    // for a list of valid subpathway names, look in res/raw/pathwayvalues.txt
    public static String[][] getProgramElectives(String subpathway) {
        Cursor c = db.query(true, "subpathways", new String[] {"prelec"}, "name = ?", new String[] {subpathway}, null, null, null, null);
        if (c.getCount() == 0) return new String[0][0];
        else {
            c.moveToNext();
            String[] elecGroups = c.getString(c.getColumnIndex("prelec")).split(" ");
            String[][] electives = new String[elecGroups.length][0];
            for (int i=0; i<elecGroups.length; i++) electives[i] = elecGroups[i].split(",");
            return electives;
        }
    }

    // returns gen ed courses for a specified gen ed letter (one of M, S, B, A, L, E, I, W)
    // M: math, S: social sciences, B: biological/physical sciences, A: arts/humanities
    // L: lab science, E: english, I: IT, W: wellness/health
    // if the gen ed id is invalid, it returns an array of length 0
    public static String[] getGenEdClasses(String genedId) {
        Cursor c = db.query(true, "classes", new String[] {"id"}, "gened LIKE '%" + genedId + "%'", null, null, null, null, null);
        Log.w("gen ed count", ((Integer)c.getCount()).toString());
        if (c.getCount() == 0) return new String[0];
        else {
            String[] result = new String[c.getCount()];
            for (int i=0;c.moveToNext();i++) result[i] = c.getString(c.getColumnIndex("id"));
            return result;
        }
    }

    // returns the printed name of the subpathway (like Nursing A.S.N.)
    // if the subpathway name is invalid, returns an empty string
    // for a list of valid subpathway names, look in res/raw/pathwayvalues.txt
    public static String getSubPathwayName(String subpathway) {
        Cursor c = db.query(true, "subpathways", new String[] {"degree"}, "name = ?", new String[] {subpathway}, null, null, null, null);
        if (c.getCount() == 0) return "";
        else {
            c.moveToNext();
            String name = subpathway + " " + c.getString(c.getColumnIndex("degree"));
            c.close();
            return name;
        }
    }

    // returns the subpathways in a pathway as a string array
    // if the pathway doesn't exist, it returns a string array of length 0
    // for a list of valid pathway names, look in res/raw/pathwayvalues.txt
    public static String[] getSubPathways(String pathway) {
        Cursor c = db.query(true, "subpathways", new String[] {"name"}, "pathway = ?", new String[] {pathway}, null, null, null, null);
        String[] subpathways = new String[c.getCount()];
        for (int i=0; c.moveToNext(); i++) {
            subpathways[i] = c.getString(c.getColumnIndex("name"));
        }
        c.close();
        return subpathways;
    }

    // returns the status of a particular class as an int
    // if the class isn't valid, it returns NOT_A_CLASS (== -1)
    // for a list of valid class ids, look at res/raw/classvalues.txt
    public static int getClassStatus(String classID) {
        Cursor c = db.query(true, "classes", new String[] {"status"}, "id = ?", new String[] {classID}, null, null, null, null);
        if (c.getCount() == 0) return NOT_A_CLASS;
        c.moveToNext();
        int status = c.getInt(c.getColumnIndex("status"));
        c.close();
        return status;
    }

    // returns a list of prereqs for a class as a string array
    // if the class isn't valid, it returns a string array of length 0
    // the prereqs returned are valid class ids
    // for a list of all class ids, look at res/raw/classvalues.txt
    public static String[] getClassPrereqs(String classID) {
        Cursor c = db.query(true, "classes", new String[] {"prereqs"}, "id = ?", new String[] {classID}, null, null, null, null);
        if (c.getCount() == 0) return new String[0];
        c.moveToNext();
        String[] prereqs = c.getString(c.getColumnIndex("prereqs")).split(" ");
        c.close();
        return prereqs;
    }

    // returns all unique prereqs for all classes  THIS WORKS!  THANKS!
    //TODO: FIGURE OUT HOW TO USE IT...
    public static String[] getAllUniquePrereqs() {
        Cursor c = db.query(true, "classes", new String[] {"prereqs"}, null, null, null, null, null, null);
        HashSet<String> set = new HashSet<String>();
        while(c.moveToNext()) {
            String[] prs = c.getString(c.getColumnIndex("prereqs")).split(" ");
            for (String pr : prs) {
                if (!pr.equals(""))
                    set.add(pr);
            }
        }
        return (String[])set.toArray();
    }

    // returns all of the class's info as a string array
    // index 0: class ID    3: class prereqs (as single string)
    //       1: class name  4: class status (as int)
    //       2: class description
    // if the classID is invalid, it returns a string array of length 0
    // for a list of valid classIDs, see res/raw/classvalues.txt
    public static String[] getClassInfo(String classID) {
        Cursor c = db.query(true, "classes", null, "id = ?", new String[] {classID}, null, null, null, null);
        if (c.getCount() == 0) return new String[0];
        c.moveToNext();
        String[] info = new String[5];
        info[0] = c.getString(c.getColumnIndex("id"));
        info[1] = c.getString(c.getColumnIndex("name"));
        info[2] = c.getString(c.getColumnIndex("description"));
        info[3] = c.getString(c.getColumnIndex("prereqs"));
        info[4] = c.getString(c.getColumnIndex("status"));
        c.close();
        return info;
    }

    public static String[] getCoursesThatQualify(String classID){
        String genEdId;
        switch (classID){
            case "GENMATH":{
                genEdId = "M";
                break;
            }
            case "GENSOSC":{
                genEdId = "S";
                break;
            }
            case "GEBIOSC":{
                genEdId="B";
                break;
            }
            case "GENARTS":{
                genEdId = "A";
                break;
            }
            case "GENELAB":{
                genEdId = "L";
                break;
            }
            case "GENENGL":{
                genEdId = "E";
                break;
            }
            case "GENINFO":{
                genEdId ="I";
                break;
            }
            case "GENWELL": {
                genEdId = "W";
                break;
            }
            case "PRELECT": {
                Log.e("invalid method", "for PRELECT, use getProgramElectives instead");
            }
            default:{
                genEdId = "M";
                break;
            }
        }
        String [] classesThatQualify = getGenEdClasses(genEdId);

        return classesThatQualify;
    }

    // updates a class status in the database and returns its success
    // use the constants in DatabaseWrapper for the status (NOT_AVAILABLE, NOT_COMPLETED, IN_PROGRESS, COMPLETED)
    // if the status isn't valid (ie isn't one of the constants), it throws an IllegalArgumentException
    // if the classID is invalid, it returns false
    // for a list of valid classIDs, see res/raw/classvalues.txt
    public static boolean writeClassStatus(String classID, int status) {
        if (status < 0 || status > 2) throw new IllegalArgumentException("Not a valid class status");
        ContentValues cv = new ContentValues();
        cv.put("status", status);
        if (db.update("classes", cv, "id = ?", new String[] {classID}) == 0) return false;
        return true;
    }

}
