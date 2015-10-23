package com.bsystemslimited.flexpay;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class RegisterActivity extends Activity {

    //Initializing
    private static String URL = "http://197.159.128.38/FlexPayWebService/FlexPay.svc/SignIn?";

    private InternetChecker inChecker = new InternetChecker(this);
    private MobileDataConnectionDetector mobileData;
    private WiFiDataConnectionDetector wifiData;

    private Boolean isMobileDataAvailable = false;
    private Boolean isWifiAvailable = false;

    //UI Controls
    private ProgressDialog dialog;
    private EditText txtFirstName,txtLastName;
    private EditText txtPassword,txtConfPassword;
    private EditText txtPhone;
    private EditText txtSeqQuesOne,txtSeqQuesTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Setting Up UI Controls
        txtFirstName = (EditText)findViewById(R.id.etForename);
        txtLastName = (EditText)findViewById(R.id.etSurname);
        txtPassword = (EditText)findViewById(R.id.rPassword);
        txtConfPassword = (EditText)findViewById(R.id.rConPassword);
        txtPhone = (EditText)findViewById(R.id.etPhone);
        txtSeqQuesOne = (EditText)findViewById(R.id.etSQ1);
        txtSeqQuesTwo = (EditText)findViewById(R.id.etSQ2);

        Spinner spinner = (Spinner) findViewById(R.id.spinQuestion1);
                // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.arrQuestions, android.R.layout.simple_spinner_item);
                    // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        Spinner spinner2 = (Spinner) findViewById(R.id.spinQuestion2);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.arrQuestions, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter2);

        Button bNext =(Button) findViewById(R.id.bNext);


        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(RegisterActivity.this, VerificationActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
