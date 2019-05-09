package com.example.rsaexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

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

//    public byte[] RSAEncrypt(final String plain) throws NoSuchAlgorithmException, NoSuchPaddingException,
//            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
//        kpg = KeyPairGenerator.getInstance("RSA");
//        kpg.initialize(2048);
//        kp = kpg.genKeyPair();
//        publicKey = kp.getPublic();
//        privateKey = kp.getPrivate();
//
//        cipher = Cipher.getInstance("RSA");
//        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//        encryptedBytes = cipher.doFinal(plain.getBytes());
//        System.out.println("EEncrypted?????" + new String(org.apache.commons.codec.binary.Hex.encodeHex(encryptedBytes)));
//        return encryptedBytes;
//    }
//
//    public String RSADecrypt(final byte[] encryptedBytes) throws NoSuchAlgorithmException, NoSuchPaddingException,
//            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
//
//        cipher1 = Cipher.getInstance("RSA");
//        cipher1.init(Cipher.DECRYPT_MODE, privateKey);
//        decryptedBytes = cipher1.doFinal(encryptedBytes);
//        decrypted = new String(decryptedBytes);
//        System.out.println("DDecrypted?????" + decrypted);
//        return decrypted;
//    }

    @Override
    public void onClick(View view) {

        Intent intent = getIntent();
//        String publicKey = intent.getStringExtra("pubkey");
//        String privateKey = intent.getStringExtra("prikey");

//The KEY must be 512 bit, otherwise it will produce wrong result
        String publicKey =
                "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJXqnyCHQMk7tb+XM/ATDxvZqFk0M+5W" +
                        "5YS7wrel33O+1wCX1lRZJUpQZyQ9OPFRz5uJdsWWLZJ8sz1i8DEl7+cCAwEAAQ==";
        String privateKey =
                "MIIBPAIBAAJBAJXqnyCHQMk7tb+XM/ATDxvZqFk0M+5W5YS7wrel33O+1wCX1lRZ" +
                        "JUpQZyQ9OPFRz5uJdsWWLZJ8sz1i8DEl7+cCAwEAAQJAQpikMbYDoKEmvsJzbw14" +
                        "Y73P3DilbRYrBUjHWf+UgPytXL4JfYFwuqUIBWZX1VHSye7ALyOdXn2fOM8muJND" +
                        "wQIhAPs7SG2MMG5/QMLUayawWpqD/Rqj6PAS+cQVNGZxfb2hAiEAmMMOOyzIGYhd" +
                        "qL1mV55TzxUyGqzOcrkgGYu4CxwQ8IcCIQD6m1JOZI9TSgDx2C7isvxOMedikaql" +
                        "AGjBnl1c600AwQIhAJC+WTCmqN7Qf/YPp/YjVcPkkHoH/QFk+c5avcMBD+fnAiEA" +
                        "8PASedLLOjYydhGhF+SVoMFSbF/mr/8Sp4yoaF13+90=" ;

        if (view == encryptButton){

            String userStr = userdataEdit.getText().toString();
            if(userStr.equals("")){
                Toast.makeText(getApplicationContext(),"Please enter message", Toast.LENGTH_SHORT).show();
                return;
            }
            byte[] userData = userStr.getBytes();
            try {

                encodeData = RSA.encryptByPublicKey(userData, publicKey);
//                String encodeStr = new BigInteger(1, encodeData).toString(16);
                String encodeStr = new String(org.apache.commons.codec.binary.Hex.encodeHex(encodeData));
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
