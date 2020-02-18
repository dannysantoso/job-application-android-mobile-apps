package com.example.jobedu.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jobedu.Database.DatabaseHelper;
import com.example.jobedu.R;

public class konfirmasi extends AppCompatActivity {
    EditText konfirmasi;
    Button submit;
    String username, password, phone, code;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi);
        db = new DatabaseHelper(this);

        username = getIntent().getStringExtra("username");
        password = getIntent().getStringExtra("password");
        phone = getIntent().getStringExtra("phone");
        code = getIntent().getStringExtra("code");

        konfirmasi = findViewById(R.id.konfirmasi);
        submit = findViewById(R.id.submit);



        Toast.makeText(konfirmasi.this, code, Toast.LENGTH_SHORT).show();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (konfirmasi.getText().toString().equals(code)){
                    boolean result = db.insertMember(username, password, phone);
                    if (result) {
                        Toast.makeText(konfirmasi.this, "Insertion Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(konfirmasi.this, MainActivity.class);
                        startActivity(intent);
                    }
                }else{
                    Toast.makeText(konfirmasi.this, "Wrong Code", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
