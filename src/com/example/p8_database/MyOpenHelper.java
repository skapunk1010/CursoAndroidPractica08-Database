package com.example.p8_database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
	
public class MyOpenHelper extends SQLiteOpenHelper {
	private static final String DB_NAME = "Practica08";
	
	public MyOpenHelper(Context context) {
		super(context, DB_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE Alumno("+
					"idAlumno INTEGER AUTO_INCREMENT PRIMARY KEY,"+
					"codigo VARCHAR(20) NOT NULL,"+
					"nombre TEXT NOT NULL,"+
					"carrera TEXT NOT NULL,"+
					"email VARCHAR(50))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS Alumno");
		onCreate(db);
	}

}