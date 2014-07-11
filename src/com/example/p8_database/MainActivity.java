package com.example.p8_database;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	private SQLiteDatabase db;
	private MyOpenHelper helper;
	
	private Button btnAlta;
	private Button btnBaja;
	private Button btnConsulta;
	private Button btnModificar;
	private Button btnLimpiar;
	
	private EditText editCodigo;
	private EditText editNombre;
	private EditText editCarrera;
	private EditText editEmail;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		

		
	}
	
	@Override
	public void onClick(View view) {
		switch(view.getId())
		{
			case R.id.btnAlta:
				alta();
				break;
			case R.id.btnBaja:
				baja();
				break;
			case R.id.btnConsulta:
				consulta();
				break;
			case R.id.btnModificacion:
				modificar();
				break;
			case R.id.btnLimpiar:
				limpiar();
				break;
		}
	}
}
