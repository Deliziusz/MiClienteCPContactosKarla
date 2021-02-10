package zombie.deliziusz.miclientecpcontactoskarla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class MainActivity extends AppCompatActivity {
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.lv);
        Cursor cursor = getContentResolver().query(
                ContactosContractProvider.CONTENT_URI_CONTACTOS
                ,
                ContactosContractProvider.PROJECTION_CONTACTOS,
                null, null, null);

        SimpleCursorAdapter adp = new SimpleCursorAdapter(
                this, android.R.layout.simple_list_item_2, cursor,
                new String[]{
                        ContactosContractProvider.FIELD_USUARIO,
                        ContactosContractProvider.FIELD_EMAIL
                },
                new int[]{android.R.id.text1, android.R.id.text2},
                SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE
        );

        lv.setAdapter(adp);
    }
    public void abrir(View view){
        Intent intent = new Intent(this, edicion.class);
        startActivity(intent);
    }

}