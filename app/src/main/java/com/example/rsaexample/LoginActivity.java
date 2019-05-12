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
                            "MIGeMA0GCSqGSIb3DQEBAQUAA4GMADCBiAKBgHzSWYD/t9ZvkevmuqtlxRvCxcck\n" +
                                    "Ln3TT/p6MYsPWdgSnQiwu2Z+KTAZoAa8XL7GNf02uvMXKMODax+sIqUieXqj0eui\n" +
                                    "Iy+AmXmeIVp/jY9MAzAGzPHjATFDGf0KOYcxOg13p9ERQ7x16bP53dlJmA+4F+Rh\n" +
                                    "sdV7Saa5Z0Rn+T13AgMBAAE=";
                    String Private =
                            "MIICWwIBAAKBgHzSWYD/t9ZvkevmuqtlxRvCxcckLn3TT/p6MYsPWdgSnQiwu2Z+" +
                                    "KTAZoAa8XL7GNf02uvMXKMODax+sIqUieXqj0euiIy+AmXmeIVp/jY9MAzAGzPHj" +
                                    "ATFDGf0KOYcxOg13p9ERQ7x16bP53dlJmA+4F+RhsdV7Saa5Z0Rn+T13AgMBAAEC" +
                                    "gYAY2HHyaHQ6Xcjo8OtVNJOwe+uMQRpz9zzjObFk4bBbfxD86tjShOhD58XfsVyw" +
                                    "qJmUhnFd880sZZtMGaJnxl3xkRZUpsvM1GGtBiBK+V+BbmcAdJK0b4exVQEVhW9G" +
                                    "SDqe+3O0r5Dt9NGSpz7/IeZsW7D84R1MExv9axYTiSTYGQJBAL+dBslQbQORWPpI" +
                                    "Mt/2tZWNHObigu4rr+JeqnTLxvJpP1dUJkC2pXKtT7ivpwmRtN4w+eHtnmxgilzH" +
                                    "hPq8ViMCQQCmw8O38Y7GTJvOd1hMZGFgu8N/ToR/1Lkd9iBk3MWIuRM97S+gia9D" +
                                    "dMNgpSvk0cavVHe71U3fmE6POb9nG46dAkBu0lLqfCmQtq/PkZJbFkVPiZ39q2Qu" +
                                    "e/Xt5vKPOoyGB3kq+PBm3TwzZymEl5L0ZeFF4O2lTdkitXNQ1dSDcAohAkA9i8Rg" +
                                    "z7KstrtN8LfSwb06kTFue5DlCKIlT5/W+a+hbQSP90h7jDT1xVMSCQNxGIamUJs7" +
                                    "5FYwLirYKyzbpcz1AkEAnJm0F4T6JtqBY8GwMZ7f3DiZ4Ts0NAZ2lpZ+uUZWl0X3" +
                                    "Qa4nF1wECFj1+rhI30Xb+ccbAa5QN5+uIR9sTYnDIw==" ;
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
