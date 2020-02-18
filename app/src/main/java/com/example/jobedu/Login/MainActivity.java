package com.example.jobedu.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jobedu.Database.DatabaseHelper;
import com.example.jobedu.EmployerMenu.EmployerHomeMenu;
import com.example.jobedu.MemberMenu.JOBTYPE;
import com.example.jobedu.R;

public class MainActivity extends AppCompatActivity {

    String memberid, employerid;
    EditText username, password;
    Button login, register;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);

        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        login = findViewById(R.id.btnLogin);
        register = findViewById(R.id.btnRegister);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (db.loginMember(username.getText().toString(), password.getText().toString()) == true) {
                    Cursor csr = db.getMemberID(username.getText().toString(), password.getText().toString());
                    csr.moveToNext();
                    memberid = csr.getString(0);
                    Intent intent = new Intent(MainActivity.this, JOBTYPE.class);
                    intent.putExtra("MemberID",memberid);
                    startActivity(intent);
                }else if(db.loginEmployer(username.getText().toString(), password.getText().toString()) == true) {
                    Cursor csr2 = db.getEmployerID(username.getText().toString(), password.getText().toString());
                    csr2.moveToNext();
                    employerid = csr2.getString(0);
                    Intent intent = new Intent(MainActivity.this, EmployerHomeMenu.class);
                    intent.putExtra("EmployerID",employerid);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "LOGIN FAILED", Toast.LENGTH_SHORT).show();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, register.class);
                startActivity(intent);
            }
        });



    }
}
