package com.example.enrique.organizadorcomposicion.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "projectsManager";

    // Table Name
    private static final String TABLE_PROJECTS = "projects";

    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_DETAILS = "details";
    private static final String KEY_CONTENT = "content";

    // Table Create Statements
    // statement table projects
    private static final String CREATE_TABLE_PROJECTS = "CREATE TABLE "
            + TABLE_PROJECTS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_DETAILS
            + " TEXT," + KEY_CONTENT + " TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    ////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_PROJECTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_PROJECTS);

        // create new tables
        onCreate(db);
    }
    ////////////////////////////////////////////////////////////////////////////////////
    // CREATING project
    public long createProject(clsDataProjects projects) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DETAILS, projects.getDetails());
        values.put(KEY_CONTENT, projects.getContent());

        long project_id = db.insert(TABLE_PROJECTS, null, values);

        return project_id;
    }
    // GETTING all PROJECTS only details
    public ArrayList<clsDataProjects> getDetailsList(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + KEY_ID + "," + KEY_DETAILS + " FROM " + TABLE_PROJECTS;
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<clsDataProjects> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                clsDataProjects projects = new clsDataProjects();
                projects.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                projects.setDetails(cursor.getString(cursor.getColumnIndex(KEY_DETAILS)));
                list.add(projects);
            } while (cursor.moveToNext());

        }
        return list;
    }
    // GETTING single PROJECTS all columns
    public clsDataProjects getFullProject(long project_id){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_PROJECTS + " WHERE " + KEY_ID + " = " + project_id;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null)cursor.moveToFirst();

        clsDataProjects projects = new clsDataProjects();
        projects.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
        projects.setDetails(cursor.getString(cursor.getColumnIndex(KEY_DETAILS)));
        projects.setContent(cursor.getString(cursor.getColumnIndex(KEY_CONTENT)));

        return projects;
    }
    // UPDATE single PROJECTS details, content
    public  int updateProject(clsDataProjects projects) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DETAILS, projects.getDetails());
        values.put(KEY_CONTENT, projects.getContent());

        return db.update(TABLE_PROJECTS, values, KEY_ID + " = ?", new String[] {String.valueOf(projects.getId())});
    }
    // DELETE single PROJECTS
    public void deleteProject(long project_id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PROJECTS, KEY_ID + " = ?", new String[]{String.valueOf(project_id)});
    }

    ////////////////////////////////////////////////////////////////////////////////////
    // CLOSE
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}