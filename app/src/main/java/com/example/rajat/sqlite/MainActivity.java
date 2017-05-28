package com.example.rajat.sqlite;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName,editSurname,editMarks,editId;
    Button btnAddData,btnv,btnup,btndel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb=new DatabaseHelper(this);
        editName= (EditText)findViewById(R.id.edit_name);
        editSurname= (EditText) findViewById(R.id.edit_surname);
        editMarks= (EditText) findViewById(R.id.edit_marks);
        editId= (EditText) findViewById(R.id.Id);
        btnAddData= (Button) findViewById(R.id.button);
        btnv= (Button) findViewById(R.id.button2);
        btnup= (Button) findViewById(R.id.button3);
        btndel= (Button) findViewById(R.id.button4);
        AddData();
        viewAll();
        updateData();
        del();

    }
    public void del()
    {
        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer rowsaffected=myDb.delete(editId.getText().toString());
                if(rowsaffected>0)
                {
                    Toast.makeText(MainActivity.this,"data deleted",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(MainActivity.this,"data not deleted",Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void AddData()
    {
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted=myDb.insertData(editName.getText().toString(),editSurname.getText().toString(),editMarks.getText().toString());
                if(isInserted)
                {
                    Toast.makeText(MainActivity.this,"data inserted",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(MainActivity.this,"data not inserted",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void updateData()
    {
        btnup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate=myDb.updateData(editId.getText().toString(),editName.getText().toString(),editSurname.getText().toString(),editMarks.getText().toString());
                if(isUpdate==true)
                {
                    Toast.makeText(MainActivity.this,"data is updated",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(MainActivity.this,"data is not updated",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void viewAll()
    {
        btnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=myDb.getAllData();
                if(res.getCount()==0)
                {
                    showMessage("Error","No data Found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext())
                {
                    buffer.append("Id :"+res.getString(0)+"\n");
                    buffer.append("Name :"+res.getString(1)+"\n");
                    buffer.append("Surname :"+res.getString(2)+"\n");
                    buffer.append("Marks :"+res.getString(3)+"\n\n");
                }
                showMessage("Data",buffer.toString());
            }
        });
    }
    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }
}
