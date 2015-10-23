package com.bsystemslimited.flexpay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button signUp =(Button) findViewById(R.id.bSignUp);
        Button logIn =(Button) findViewById(R.id.bLogin);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(MenuActivity.this, RegisterActivity.class);
                startActivity(myIntent);

            }
        });

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(MenuActivity.this, LoginActivity.class);
                startActivity(myIntent);

               // startActivity(new Intent("com.bsystemslimited.flexpay.LOGIN"));

            }
        });


    }


}
