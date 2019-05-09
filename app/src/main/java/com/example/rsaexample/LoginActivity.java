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
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
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
                            "MIICXAIBAAKBgQCKRSJjMPnvgwPPwq+2Oje8KGV6KOBxxz2Y/s24WNzecHFLXKLA" +
                                    "hAL/FphjIF8epQW1xcorxzAgEt+qmmF1QEA1VOAHLuS+0rlaAarJ8xZNVaXdVRAd" +
                                    "ZMIaln0gA3trbpln2iiCDj7yR/mqTNqCGoL2axXQrI8cTAK8DVyPiMzCtQIDAQAB" +
                                    "AoGARH2QVg/5jgGYzr4CKcLWvtZHxeYfn1xxD6sPngQui+Soygq995ysm0zG+Tsc" +
                                    "wuI9XNf3mA3XsduHfUtxgRHte4MGhhDnUcpLYNxp3dZeuZINVoKPZjhCTquCF0R1" +
                                    "cIPSyExlG9JHrkmznwkXLOBg6jAyIQ7kpzTpaqz+HX8njYECQQC8KAdt9yX3pJFc" +
                                    "6Gf9GY26s6xjENxixY5kD6xPEgBb0ogSiAxTQMHV863DHkzy7ZcV3ayUJmKab0+k" +
                                    "76i7WJYhAkEAvCBMF/o/nTUDIIfZ/YWTwuugLT6canp6aGiOfSJJopLrvcYrJoFd" +
                                    "4YXZvWThIkUg/nTEC36HcrhnWY3ianYyFQJBALFkm/yVMvqT8WjCxKffW7xWgYS0" +
                                    "9NM6ptC35iB2PFhV2Wx/T/994kIxB/YZrzqpvfQjlv3mYrhvkYtTWKWpE4ECQB8i" +
                                    "/VUunx7G6miU85iJZkZpt04lwb/B28ayH3tIlIVq6ce0J+osmTw9aid1rel9JZPY" +
                                    "AaahX4u2R6zD1gim3W0CQEN1jlMFTZWm21xCOkzUt8uzvbk3YpMUJ1ZgQ+mYpMwP" +
                                    "uqY1Ogg6+uwSxAHk/k4Mw3JWcVpvuIEARXWkFQbD84I=" ;
                    byte[] publicBytes = Base64.getDecoder().decode(Public.getBytes());
                    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
                    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                    PublicKey pubKey = keyFactory.generatePublic(keySpec);

                    byte[] priBytes = Base64.getDecoder().decode(Private.getBytes());
//                    X509EncodedKeySpec priSpec = new X509EncodedKeySpec(priBytes);
                    PKCS8EncodedKeySpec priSpec = new PKCS8EncodedKeySpec(priBytes);
                    KeyFactory keyFact = KeyFactory.getInstance("RSA");
                    PrivateKey priKey = keyFact.generatePrivate(priSpec);

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
