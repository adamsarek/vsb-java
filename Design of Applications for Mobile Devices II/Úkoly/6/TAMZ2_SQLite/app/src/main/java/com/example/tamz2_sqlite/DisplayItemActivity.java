package com.example.tamz2_sqlite;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DisplayItemActivity extends AppCompatActivity {

    private DBHelper mydb;
    TextView nameTextView;
    TextView costTextView;
    Spinner typeSpinner;
    int idToUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_record);

        nameTextView = findViewById(R.id.editTextName);
        costTextView = findViewById(R.id.editTextCost);
        typeSpinner = findViewById(R.id.spinnerType);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, DataItem.types);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(spinnerArrayAdapter);

        mydb = new DBHelper(this);
        Intent i = getIntent();
        if(i != null)
        {
            //ziskam ID, ktere se ma editovat/zobrazit/mazat - poslane z hlavni aktivity
            int value = i.getIntExtra("id", 0);
            idToUpdate = value;
            if (idToUpdate > 0)
            {
                //z db vytahnu zaznam pod hledanym ID a ulozim do idToUpdate
                Cursor rs = mydb.getData(idToUpdate);
                rs.moveToFirst();

                //z DB vytahnu jmeno zaznamu a další data
                String nameDB = rs.getString(rs.getColumnIndex(DBHelper.ITEM_COLUMN_NAME));
                int typeDB = rs.getInt(rs.getColumnIndex(DBHelper.ITEM_COLUMN_TYPE));
                int costDB = rs.getInt(rs.getColumnIndex(DBHelper.ITEM_COLUMN_COST));

                if (!rs.isClosed())
                {
                    rs.close();
                }
                Button b = findViewById(R.id.buttonSave);
                b.setVisibility(View.INVISIBLE);

                nameTextView.setText(nameDB);
                nameTextView.setEnabled(false);
                nameTextView.setFocusable(false);
                nameTextView.setClickable(false);

                costTextView.setText(String.valueOf(costDB));
                costTextView.setEnabled(false);
                costTextView.setFocusable(false);
                costTextView.setClickable(false);

                typeSpinner.setSelection(typeDB);
                typeSpinner.setEnabled(false);
                typeSpinner.setFocusable(false);
                typeSpinner.setClickable(false);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if(idToUpdate>0){
            getMenuInflater().inflate(R.menu.display_record_menu, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.Edit_Contact) {
            Button b = findViewById(R.id.buttonSave);
            b.setVisibility(View.VISIBLE);

            nameTextView.setEnabled(true);
            nameTextView.setFocusableInTouchMode(true);
            nameTextView.setClickable(true);

            costTextView.setEnabled(true);
            costTextView.setFocusableInTouchMode(true);
            costTextView.setClickable(true);

            typeSpinner.setEnabled(true);
            typeSpinner.setFocusableInTouchMode(true);
            typeSpinner.setClickable(true);
        }
        if (id == R.id.Delete_Contact)
        {
            //TODO 3: zavolat z mydb metodu na odstraneni zaznamu
            if(mydb.deleteItem(idToUpdate)){
                Toast.makeText(getApplicationContext(), "deleted", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "not deleted", Toast.LENGTH_SHORT).show();
            }
            finish();
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }

        return true;
    }

    public void saveButtonAction(View view)
    {

        if(idToUpdate > 0){
            //TODO 4: zavolat z mydb metodu na update zaznamu
            if(mydb.updateItem(idToUpdate, nameTextView.getText().toString(), typeSpinner.getSelectedItemPosition(), Integer.parseInt(costTextView.getText().toString()))){
                Toast.makeText(getApplicationContext(), "saved", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "not saved", Toast.LENGTH_SHORT).show();
            }
            finish();
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }
        else{
            //vlozeni zaznamu
            if(mydb.insertItem(nameTextView.getText().toString(), typeSpinner.getSelectedItemPosition(), Integer.parseInt(costTextView.getText().toString()))){
                Toast.makeText(getApplicationContext(), "saved", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "not saved", Toast.LENGTH_SHORT).show();
            }
            finish();
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }

    }

    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }
}