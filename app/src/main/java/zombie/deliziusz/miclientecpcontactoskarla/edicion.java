package zombie.deliziusz.miclientecpcontactoskarla;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class edicion extends AppCompatActivity {
    private EditText id,usuario,email,tele;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicion);

        id=(EditText) findViewById(R.id.txtID);
        usuario=(EditText) findViewById(R.id.txtNombre);
        email=(EditText) findViewById(R.id.txtEmail);
        tele=(EditText) findViewById(R.id.txtTel);
    }

    public void regresar(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    //Metodo para insertar un nuevo registro

    public void insertar(View view){

        if(usuario.getText().toString().isEmpty() || email.getText().toString().isEmpty() ||
                tele.getText().toString().isEmpty()){
            Toast.makeText(this, "Faltan campos", Toast.LENGTH_SHORT ).show();
        }else {

            String usi = usuario.getText().toString();
            String ema = email.getText().toString();
            String tel = tele.getText().toString();

            ContentValues contentValues = new ContentValues();

            contentValues.put(ContactosContractProvider.PROJECTION_CONTACTOS[1],
                    usi );
            contentValues.put(ContactosContractProvider.PROJECTION_CONTACTOS[2],
                    ema );
            contentValues.put(ContactosContractProvider.PROJECTION_CONTACTOS[3],
                    tel );


            Uri miuri =  getContentResolver().insert(
                    ContactosContractProvider.CONTENT_URI_CONTACTOS,
                    contentValues
            );

            Log.d("MIURI", miuri.toString());
        }
        limpiar();


    }

    //metodo para eliminar un registro
    public void eliminar(View view){
        if(id.getText().toString().isEmpty()){
            Toast.makeText(this, "falta el id a eliminar", Toast.LENGTH_SHORT).show();
        }else{
            String elimina=id.getText().toString();
            getContentResolver().delete(ContactosContractProvider.CONTENT_URI_CONTACTOS, elimina, null);
        }

        limpiar();

    }
    //metodo para buscar
    public void buscar(View view){
        if(id.getText().toString().isEmpty()){
            Toast.makeText(this, "falta el id a buscar", Toast.LENGTH_SHORT).show();
        }else{
            String busca=id.getText().toString();
            Cursor cursor = getContentResolver().query(
                    Uri.parse(ContactosContractProvider.CONTENT_URI_CONTACTOS.toString() + "/"+busca)
                    ,
                    ContactosContractProvider.PROJECTION_CONTACTOS,
                    null, null, null);
            cursor.moveToFirst();
            if (cursor != null) {
                id.setText(cursor.getString(0));
                usuario.setText(cursor.getString(1));
                email.setText(cursor.getString(2));
                tele.setText(cursor.getString(3));

            } else {
                usuario.setText("No hay nada");
            }
        }
    }
    //Metodo para Editar un contacto
    public void editar(View view){
        if(usuario.getText().toString().isEmpty() || email.getText().toString().isEmpty() ||
                tele.getText().toString().isEmpty()){
            Toast.makeText(this, "Faltan campos", Toast.LENGTH_SHORT ).show();
        }else {

            String usi = usuario.getText().toString();
            String ema = email.getText().toString();
            String tel = tele.getText().toString();
            String idd= id.getText().toString();

            ContentValues contentValues = new ContentValues();

            contentValues.put(ContactosContractProvider.PROJECTION_CONTACTOS[1],
                    usi );
            contentValues.put(ContactosContractProvider.PROJECTION_CONTACTOS[2],
                    ema );
            contentValues.put(ContactosContractProvider.PROJECTION_CONTACTOS[3],
                    tel );

            int miuri =  getContentResolver().update(
                    ContactosContractProvider.CONTENT_URI_CONTACTOS,
                    contentValues,idd,null
            );
        }
        limpiar();
    }
    public void limpiar(){
        id.setText("");
        usuario.setText("");
        email.setText("");
        tele.setText("");
    }
}
