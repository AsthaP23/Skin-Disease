package com.example.tryskin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MyLoginActivity extends AppCompatActivity {
    TextView tv1;
    Button b1;
    EditText mobile,password;
    TestAdapter adapter;
    String smobile,spassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        tv1=(TextView) findViewById(R.id.register);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MyLoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });

        b1=(Button)findViewById(R.id.btn_login);
        mobile=(EditText)findViewById(R.id.txt_mobile);
        password=(EditText)findViewById(R.id.txt_password);

        tv1=(TextView) findViewById(R.id.register);
        try {

            adapter=new TestAdapter(this);
            adapter.createDatabase();
            adapter.open();



            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    smobile = mobile.getText().toString().trim();
                    spassword= password.getText().toString().trim();

                    if(TextUtils.isEmpty(smobile))
                    {
                        mobile.setError("Mobile is Required.");
                        return;
                    }

                    if(TextUtils.isEmpty(spassword))
                    {
                        password.setError("Password is Required.");
                        return;
                    }

                    int i = adapter.checkUserLogin(smobile,spassword);
                    if (i == 1) {


                        userlogin();
                        return;

                    }

                    if (smobile.equalsIgnoreCase("9860202746")&&(spassword.equalsIgnoreCase("9197"))){
                        Intent intent = new Intent(getApplicationContext(), MyLoginActivity.class);
                        startActivity(intent);

                        Toast.makeText(MyLoginActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                        return;

                    }

                    else{
                        Toast.makeText(MyLoginActivity.this, "Invalid Mobile Or Password", Toast.LENGTH_SHORT).show();
                        return;

                    }


                }
            });

        }catch (Exception e){}


    }

    private void userlogin() {
        final ProgressDialog dialog =
                new ProgressDialog(MyLoginActivity.this);
        dialog.setIcon(R.drawable.login);
        dialog.setTitle("Login");
        dialog.setMessage("Please wait User Login is Processing...");
        dialog.show();

        final Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                dialog.cancel();
                String mpass = null;

                Intent i = new Intent(MyLoginActivity.this, CNNAlgorithm.class);
                i.putExtra("Key",smobile);
                startActivity(i);

                Toast.makeText(getApplicationContext(), "Your Login is Successfull..." , Toast.LENGTH_SHORT).show();

                mobile.setText("");
                password.setText("");


            }
        };
        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 6000);



    }
}