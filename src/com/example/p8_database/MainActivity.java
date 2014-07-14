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
		
		btnAlta = (Button) findViewById(R.id.btnAlta);
		btnBaja = (Button) findViewById(R.id.btnBaja);
		btnConsulta = (Button) findViewById(R.id.btnConsulta);
		btnModificar = (Button) findViewById(R.id.btnModificacion);
		btnLimpiar = (Button) findViewById(R.id.btnLimpiar);
		
		btnAlta.setOnClickListener(this);
		btnBaja.setOnClickListener(this);
		btnConsulta.setOnClickListener(this);
		btnModificar.setOnClickListener(this);
		btnLimpiar.setOnClickListener(this);
		
		editCodigo = (EditText) findViewById(R.id.editTextCodigo);
		editNombre = (EditText) findViewById(R.id.editTextNombre);
		editCarrera = (EditText) findViewById(R.id.editTextCarrera);
		editEmail = (EditText) findViewById(R.id.editTextEmail);
		
		
	}
	public void limpiar(){
		editCodigo.setText("");
		editNombre.setText("");
		editCarrera.setText("");
		editEmail.setText("");
	}
	
	public void alta(){
		//Acceso a la base datos
		helper = new MyOpenHelper(this);
		db = helper.getWritableDatabase();
		
		//Rescatar los datos
		ContentValues content = new ContentValues();
		content.put("nombre", editNombre.getText().toString());
		content.put("codigo", editCodigo.getText().toString());
		content.put("carrera", editCarrera.getText().toString());
		content.put("email", editEmail.getText().toString());
		
		//Limpiar campos
		limpiar();
		
		//Hacer insercion en la Base de Datos
		db.insert("Alumno", null, content);
		db.close();
		
		Toast.makeText(getApplicationContext(), "Alta exitosa!", Toast.LENGTH_SHORT).show();	
	}
	
	public void baja(){
		//Acceso a la Base de Datos
		helper = new MyOpenHelper(this);
		db = helper.getWritableDatabase();
		
		//Rescatamos datos
		String codigo = editCodigo.getText().toString();
		limpiar();
		
		//Ejecutamos consulta
		db.delete("Alumno", "codigo = ?", new String[]{codigo});
		db.close();
		
		Toast.makeText(getApplicationContext(), "Baja exitosa!", Toast.LENGTH_SHORT).show();
	}
	
	public void modificar(){
		//Acceso a la base datos
		MyOpenHelper helper = new MyOpenHelper(this);
		SQLiteDatabase db = helper.getWritableDatabase();
		
		//Rescatar los datos
		ContentValues content = new ContentValues();
		String codigo = editCodigo.getText().toString();
		content.put("codigo", codigo);
		content.put("nombre", editNombre.getText().toString());
		content.put("carrera", editCarrera.getText().toString());
		content.put("email", editEmail.getText().toString());
		
		//Limpiar campos
		limpiar();
		
		//Hacer insercion en la Base de Datos
		int x = db.update("Alumno", content, "codigo = ?", new String[]{codigo});
		db.close();
		if(x > 0){			
			Toast.makeText(getApplicationContext(), x+" registros modificados! ", Toast.LENGTH_SHORT).show();
		}else {
			Toast.makeText(getApplicationContext(), "No existe la persona! "+x, Toast.LENGTH_SHORT).show();
		}
		
	}
	
	public void consulta(){
		//Acceso a la base datos
		helper = new MyOpenHelper(this);
		db = helper.getWritableDatabase();
		
		String codigo = editCodigo.getText().toString();
		
		Cursor cursor = db.rawQuery("SELECT * FROM Alumno WHERE codigo = ? ", new String[]{codigo});
		
		if(cursor.moveToFirst()){
			editNombre.setText(cursor.getString(2));
			editCarrera.setText(cursor.getString(3));
			editEmail.setText(cursor.getString(4));
			Toast.makeText(getApplicationContext(), "Registro con Id:"+cursor.getString(0), Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(getApplicationContext(), "No hay coincidencias!", Toast.LENGTH_SHORT).show();
		}
		db.close();
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
