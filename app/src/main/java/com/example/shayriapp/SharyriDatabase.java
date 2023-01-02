package com.example.shayriapp;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
public class SharyriDatabase extends SQLiteOpenHelper {
    static int valueOfQuotes;
    private static final String TAG = "MyDatabase";


    private static String DB_NAME = "shayaridb.db";
    private static String DB_PATH = "";
    private static final int DB_VERSION = 1;

    private SQLiteDatabase mDataBase;
    private boolean mNeedUpdate = false;

    private Context mContext;

    public SharyriDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        if (Build.VERSION.SDK_INT >= 17)
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        else
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        this.mContext = context;

        copyDataBase();

        this.getReadableDatabase();
    }


    private void copyDataBase() {
        if (!checkDataBase()) {
            this.getReadableDatabase();
            this.close();
            try {
                copyDBFile();
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }

    //    TODO copy file
    private void copyDBFile() throws IOException {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        OutputStream mOutput = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0)
            mOutput.write(mBuffer, 0, mLength);
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            mNeedUpdate = true;
    }


    //    TODO update database
    public void updateDataBase() throws IOException {
        if (mNeedUpdate) {
            File dbFile = new File(DB_PATH + DB_NAME);
            if (dbFile.exists())
                dbFile.delete();

            copyDataBase();

            mNeedUpdate = false;
        }
    }


    @Override
    public synchronized void close() {
        if (mDataBase != null)
            mDataBase.close();
        super.close();
    }








    public ArrayList<CategoryModel> readData() {
        ArrayList<CategoryModel> modalArrayList=new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String sql="select * from ShayariTable";
        Cursor c= db.rawQuery(sql,null);

        if(c.moveToFirst())
        {
            do{

                int id=c.getInt(0);
                String category=c.getString(1);

                CategoryModel modalArray=new CategoryModel(category,id);
                modalArrayList.add(modalArray);


            }while (c.moveToNext());
        }

        return modalArrayList;

    }

    public ArrayList<QuotesModel> shayariReadData() {
        ArrayList<QuotesModel> ShayariList=new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String sql="select * from QuotesTable where category_id="+valueOfQuotes;
        Cursor c= db.rawQuery(sql,null);

        if(c.moveToFirst())
        {
            do{

                int shayari_id=c.getInt(0);
                String shayari=c.getString(1);
                int category_id=c.getInt(2);
                int status=c.getInt(3);

                QuotesModel modalArray=new QuotesModel(shayari,shayari_id,category_id, status);
                ShayariList.add(modalArray);


            }while (c.moveToNext());
        }

        return ShayariList;

    }

 public ArrayList<QuotesModel> likeShayari() {
        ArrayList<QuotesModel> ShayariList=new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String sql="select * from QuotesTable where status =1";
        Cursor c= db.rawQuery(sql,null);

        if(c.moveToFirst())
        {
            do{
                int shayari_id=c.getInt(0);
                String shayari=c.getString(1);
                int category_id=c.getInt(2);
                int status=c.getInt(3);

                QuotesModel modalArray=new QuotesModel(shayari,shayari_id,category_id,status);
                ShayariList.add(modalArray);


            }while (c.moveToNext());
        }

        return ShayariList;

    }





}

