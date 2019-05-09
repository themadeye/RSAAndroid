package com.example.rsaexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.Key;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";
    private EditText username;
    private EditText password;
    private Button btnLogin;
    private Button btnExit;
    private static String publicKey = "";
    private static String privateKey = "";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnExit = (Button)findViewById(R.id.btnExit);

        btnLogin.setOnClickListener(this);
        btnExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnLogin){
            if(username.getText().toString().equals("")){
                Toast.makeText(this, "Please key in valid Username", Toast.LENGTH_SHORT).show();
            }else{
                try {
//                    Map<String, Object> keyMap = RSA.initKey();
                    String Public =
                            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDH+wPrKYG1KVlzQUVtBghR8n9d" +
                                    "zcShSZo0+3KgyVdOea7Ei7vQ1U4wRn1zlI5rSqHDzFitblmqnB2anzVvdQxLQ3Uq" +
                                    "EBKBfMihnLgCSW8Xf7MCH+DSGHNvBg2xSNhcfEmnbLPLnbuz4ySn1UB0lH2eqxy5" +
                                    "0zstxhTY0binD9Y+rwIDAQAB";
                    String Private =
                            "MIICxjBABgkqhkiG9w0BBQ0wMzAbBgkqhkiG9w0BBQwwDgQIr5NQ/LYPG/UCAggA" +
                                    "MBQGCCqGSIb3DQMHBAiLh89iGSkmoASCAoBCpAo9/IzDE3yGhvWr9RgozE7revOo" +
                                    "V2OXmU+d0+WYAAx2GYVaUCbFVrmgiVmrbiTgLUMXAGIpvxQ2rzyIvRHW/RN3Gcky" +
                                    "qR/AwBatzixqrnoS4aD1/Ovjr4hwde4XHYbPEilZZuVAJFiznhy73qm/So4XghSY........." ;
                    byte[] publicBytes = Base64.getDecoder().decode(Public.getBytes());
                    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
                    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                    PublicKey pubKey = keyFactory.generatePublic(keySpec);

                    byte[] priBytes = Base64.getDecoder().decode(Private.getBytes());
                    X509EncodedKeySpec priSpec = new X509EncodedKeySpec(priBytes);
                    KeyFactory keyFact = KeyFactory.getInstance("RSA");
                    PublicKey priKey = keyFact.generatePublic(priSpec);

                    Map<String, Object> keyMap = new HashMap<String, Object>(2);

                    keyMap.put(PUBLIC_KEY, pubKey);
                    keyMap.put(PRIVATE_KEY, priKey);

                    publicKey = RSA.getPublicKey(keyMap);
                    privateKey = RSA.getPrivateKey(keyMap);

//                    publicKey = this.getString(R.string.pubkey);
//                    privateKey = this.getString(R.string.prikey);
                }catch (Exception e){
                    e.printStackTrace();
                }

                Intent intent = new Intent(this,EncryptActivity.class);
                intent.putExtra("pubkey",publicKey);
                intent.putExtra("prikey",privateKey);
                startActivity(intent);
            }
        }
    }
}
