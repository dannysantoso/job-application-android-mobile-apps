package com.example.jobedu.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jobedu.R;

import java.util.Random;

import static android.text.TextUtils.isEmpty;

public class register extends AppCompatActivity {

    EditText username, password, phone;
    Button login, register;



    SmsManager sms;
    int permission;
    String Code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        phone = findViewById(R.id.etPhone);
        login = findViewById(R.id.btnLogin);
        register = findViewById(R.id.btnRegister);

        sms = SmsManager.getDefault();
        permission = ContextCompat.checkSelfPermission(register.this, Manifest.permission.SEND_SMS);

        if(permission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(register.this, new String[]{Manifest.permission.SEND_SMS},1);
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEmpty(username.getText().toString())){
                    username.setError("Username Can't Empty");
                }else if(isEmpty(password.getText().toString())){
                    password.setError("Password Can't Empty");
                }else if(passwordValidasi() == false){
                    password.setError("Error Password Format");
                }else if(isEmpty(phone.getText().toString())){
                    phone.setError("Phone Can't Empty");
                }else if(phoneNumeric() == false){
                    phone.setError("Phone Number Must Be Numeric");
                }else {
                    Random random = new Random();
                    Code = Integer.toString(random.nextInt(999999-0+1)+0);
                    sms.sendTextMessage(phone.getText().toString(), null, String.valueOf(Code), null, null);
                    Intent intent = new Intent(register.this, konfirmasi.class);
                    intent.putExtra("username",username.getText().toString());
                    intent.putExtra("password",password.getText().toString());
                    intent.putExtra("phone",phone.getText().toString());
                    intent.putExtra("code",String.valueOf(Code));
                    startActivity(intent);
                }


            }
        });

    }


    public boolean passwordValidasi(){
        String passwordValidasi = password.getText().toString();
        if(passwordValidasi.matches("([A-Za-z]+[0-9]|[0-9]+[A-Za-z])[A-Za-z0-9]*")){
            return true;
        }else{
            return false;
        }
    }

    public boolean phoneNumeric(){
        String phone_validasi = phone.getText().toString();
        if(phone_validasi.matches("[0-9]+")){
            return true;
        }else{
            return false;
        }
    }


}
