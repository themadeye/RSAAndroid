package com.example.rsaexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigInteger;

public class EncryptActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText userdataEdit;
    private EditText encryptedEdit;
    private EditText decryptedEdit;
    private Button encryptButton;
    private Button decryptButton;
    byte[] encodeData = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encryption);

        userdataEdit = (EditText)findViewById(R.id.userdataEdit);
        encryptedEdit = (EditText)findViewById(R.id.encryptedEdit);
        decryptedEdit = (EditText)findViewById(R.id.decryptedEdit);
        encryptButton = (Button)findViewById(R.id.encryptButton);
        decryptButton = (Button)findViewById(R.id.decryptButton);
        encryptButton.setOnClickListener(this);
        decryptButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent intent = getIntent();
        String publicKey = intent.getStringExtra("pubkey");
        String privateKey = intent.getStringExtra("prikey");
        if (view == encryptButton){

            String userStr = userdataEdit.getText().toString();
            if(userStr.equals("")){
                Toast.makeText(getApplicationContext(),"Please enter message", Toast.LENGTH_SHORT).show();
                return;
            }
            byte[] userData = userStr.getBytes();
            try {
                encodeData = RSA.encryptByPublicKey(userData, publicKey);
                String encodeStr = new BigInteger(1, encodeData).toString(16);
                encryptedEdit.setText(encodeStr);
            }catch (Exception e){
                e.printStackTrace();
            }

        }else if (view == decryptButton){

            if(encodeData == null){
                Toast.makeText(getApplicationContext(),"Please use public key to encrypt",Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                byte[] decodeData = RSA.decryptByPrivateKey(encodeData,privateKey);
                String decodeStr = new String(decodeData);
                decryptedEdit.setText(decodeStr);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
